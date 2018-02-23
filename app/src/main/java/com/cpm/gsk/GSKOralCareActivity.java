package com.cpm.gsk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.cpm.alert_message.AlertMessage;
import com.cpm.alert_message.AlertMessageActivity;
import com.cpm.autoupdate.AutoupdateActivity;
import com.cpm.database.GskDatabase;

import com.cpm.gettersetter.FailureGetterSetter;
import com.cpm.gettersetter.UserInfoGetterSetter;

import com.cpm.mainmenu.MainMenuScreenActivity;


import com.cpm.web.ShowUpdateActivity;


import com.cpm.xmlhandler.UserInfoXmlHandler;
import com.cpm.xmlhandler.XMLHandlers;
import com.gsk.constants.Constant;

public class GSKOralCareActivity extends Activity implements OnClickListener {

    private EditText mUsername, mPassword;
    private TextView textView3;
    private Button mLogin;
    GskDatabase db;
    static Editor e2;
    int eventType;
    com.cpm.gettersetter.LoginGetterSetter lgs = null;
    private String username, password, p_username, p_password;
    private double latitude = 0.0, longitude = 0.0;
    private int versionCode;
    public UserInfoGetterSetter data;
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private Intent intent = null;
    static int count = 1;
    com.cpm.gettersetter.JCPGetterSetter jcpData;
    int length;
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;

    String path = "", p, s;

    String vidpath = "http://gskmtm.parinaam.in/videos/";

    ProgressBar progressBar;
    int videocount = 0;
    String videoarr[] = {"HypernovaMOA.mp4", "Rapidrelief.mp4", "BaseSensodyne.mp4", "SensodyneExpertToothbrushVideo.mp4",
            "SensodyneWhiteningPromo.mp4", "vodeogsk.mp4"};
    int vidflag = 0;
    private Data data1;
    String version;

    String Path = Environment.getExternalStorageDirectory().getPath() + "/GSKOralCare/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ContentResolver.setMasterSyncAutomatically(false);
        mUsername = (EditText) findViewById(R.id.login_useridtextbox);
        mPassword = (EditText) findViewById(R.id.login_passwordtextbox);
        mLogin = (Button) findViewById(R.id.login_submitbtn);
        textView3 = (TextView) findViewById(R.id.textView3);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        mUsername.setText("anu.aravind");
        mPassword.setText("cpm@5678");

        e2 = this.getSharedPreferences("user_detail", Context.MODE_WORLD_READABLE).edit();
        p_username = this.getSharedPreferences("user_detail", Context.MODE_WORLD_WRITEABLE).getString("username", "");
        p_password = this.getSharedPreferences("user_detail", Context.MODE_WORLD_WRITEABLE).getString("password", "");
        db = new GskDatabase(GSKOralCareActivity.this);
        db.openDB();
        db.CreateTable();
        db.CloseDB();
        mLogin.setOnClickListener(this);
        trimCache(this);
    }

    // clear cache
    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onClick(View v) {

        username = mUsername.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        switch (v.getId()) {
            case R.id.login_submitbtn:
                if (username.length() == 0) {
                    showToast("Please enter username");
                } else if (password.length() == 0) {
                    showToast("Please enter password");
                } else {
                    hideKeyboard();
                    p_username = this.getSharedPreferences("user_detail", Context.MODE_WORLD_WRITEABLE).getString("username", "");
                    p_password = this.getSharedPreferences("user_detail",
                            Context.MODE_WORLD_WRITEABLE).getString("password", "");
                    // If no preferences are stored
                    if (p_username == "" && p_password == "") {
                        if (CheckNetAvailability()) {
                            // if (true) {
                            new AuthenticateTask().execute();

                        } else {
                            showToast("No Network and first login");
                        }
                    }
                    // If preferences are stored
                    else {
                        if (username.equals(p_username)) {
                            if (CheckNetAvailability()) {

                                new AuthenticateTask().execute();
                            } else if (password.equals(p_password)) {
                                intent = new Intent(this, MainMenuScreenActivity.class);
                                startActivity(intent);
                                this.finish();

                                showToast("No Network and offline login");
                            } else {
                                showToast("Incorrect Password");
                            }
                        } else {
                            showToast("Incorrect Username");
                        }
                    }
                }

                break;

        }
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private class AuthenticateTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            dialog = new ProgressDialog(GSKOralCareActivity.this);
            dialog.setTitle("Login");
            dialog.setMessage("Authenticating....");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {


            try {
                String app_ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                String userauth_xml = "[DATA]" + "[USER_DATA][USER_ID]"
                        + username + "[/USER_ID]" + "[PASSWORD]" + password
                        + "[/PASSWORD]" + "[IN_TIME]" + getCurrentTime()
                        + "[/IN_TIME]" + "[LATITUDE]" + latitude
                        + "[/LATITUDE]" + "[LONGITUDE]" + longitude
                        + "[/LONGITUDE]" + "[APP_VERSION]" + app_ver
                        + "[/APP_VERSION]" + "[ATT_MODE]OnLine[/ATT_MODE]"
                        + "[NETWORK_STATUS]" + "LoginStatus"
                        + "[/NETWORK_STATUS]" + "[/USER_DATA][/DATA]";

                SoapObject request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_ATTENDENCE);
                request.addProperty("onXML", userauth_xml);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(
                        Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_ATTENDENCE, envelope);

                Object result = (Object) envelope.getResponse();
                String userinfo_xml = result.toString();

                if (result.toString().equals("Failure")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            GSKOralCareActivity.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            message.showMessage();
                        }
                    });

                } else if (result.toString().equals("False")) {
                    String str = "Invalid User";

                    final AlertMessageActivity message = new AlertMessageActivity(GSKOralCareActivity.this, str, "login_success", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            message.showMessage();
                        }
                    });
                } else if (result.toString().equals("Changed")) {
                    String str = "Invalid UserId or Password / Password has been changed.";
                    final AlertMessageActivity message = new AlertMessageActivity(GSKOralCareActivity.this, str, "login_success", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            message.showMessage();
                        }
                    });

                } else {

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    FailureGetterSetter failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);
                    if (failureGetterSetter.getStatus().equalsIgnoreCase(Constant.KEY_FAILURE)) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
//								message.showMessage();
                            }
                        });
                    } else {

                        try {
                            // For String source

                            xpp.setInput(new StringReader(result.toString()));
                            xpp.next();
                            eventType = xpp.getEventType();
                            lgs = XMLHandlers.loginXMLHandler(xpp, eventType);

                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // PUT IN PREFERENCES

                        e2 = GSKOralCareActivity.this.getSharedPreferences(
                                "user_detail", Context.MODE_WORLD_READABLE).edit();

                        e2.putString("version", lgs.getAPP_VERSION());
                        e2.putString("path", lgs.getAPP_PATH());
                        e2.putString("username", username);
                        e2.putString("password", password);
                        e2.putString("date", lgs.getCURRENTDATE());
                        e2.commit();
                    }
                }


                {

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();
                    request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                    request.addProperty("UserName", username);
                    request.addProperty("Type", "JOURNEYPLAN");
                    envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);
                    androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                    androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                    Object result1 = (Object) envelope.getResponse();

                    if (result1.toString().equalsIgnoreCase(Constant.KEY_FALSE)) {
                    }

                    // for failure
                    xpp.setInput(new StringReader(result1.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    FailureGetterSetter failureGetterSetter = XMLHandlers.failureXMLHandler(xpp, eventType);
                    if (failureGetterSetter.getStatus().equalsIgnoreCase(Constant.KEY_FAILURE)) {
                        return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD + "," + failureGetterSetter.getErrorMsg();
                    }

                    if (!result1.toString().equalsIgnoreCase(Constant.KEY_FALSE)) {
                        xpp.setInput(new StringReader(result1.toString()));
                        xpp.next();
                        eventType = xpp.getEventType();
                        jcpData = XMLHandlers.JCPXMLHandler(xpp, eventType);
                    }

                    if (jcpData.getSTORE_ID().size() == 0) {
                        return Constant.KEY_NO_DATA;
                    }
                    GskDatabase db = new GskDatabase(getApplicationContext());
                    db.openDB();
                    //  db.insertJCPData(jcpData);
                    preferences = PreferenceManager.getDefaultSharedPreferences(GSKOralCareActivity.this);
                    editor = preferences.edit();
                    editor.putString("store_name", jcpData.getSTORE().get(0));
                    editor.putString("store_address", jcpData.getCITY().get(0));
                    editor.putString("USERNAME", username);
                    editor.putString("PASSWORD", password);

                    editor.commit();
                    e2 = GSKOralCareActivity.this.getSharedPreferences("user_detail", Context.MODE_WORLD_READABLE).edit();
                    e2.putString("store_id", jcpData.getSTORE_ID().get(0));
                    e2.putString("date", jcpData.getVISIT_DATE().get(0));
                    e2.putString("processid", jcpData.getPROCESS_ID().get(0));
                    e2.commit();
                    return "Success";
                }


            } catch (MalformedURLException e) {
                String str = AlertMessageActivity.Message_problem;
                final AlertMessageActivity message = new AlertMessageActivity(GSKOralCareActivity.this, str, "acra", e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                String str = AlertMessageActivity.Message_network;
                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this, str, "socket", e);
                count++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (count < 10) {
                            new AuthenticateTask().execute();
                        } else {
                            message.showMessage();
                        }
                    }
                });
            } catch (Exception e) {

                String str = AlertMessageActivity.Message_problem;
                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this, str, "acra", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (jcpData != null && jcpData.getSTORE_ID().size() == 0) {
                AlertMessage message = new AlertMessage(GSKOralCareActivity.this,
                        AlertMessage.MESSAGE_JCP_FALSE, "NoJCP", null);
                message.showMessage();
            }
            if (result.equals("Success")) {
                for (int i = 0; i < videoarr.length; i++) {
                    if (!new File(Path + videoarr[i]).exists()) {
                        vidflag = 1;
                        videocount = i;
                        break;
                    }
                }
                if (vidflag == 1) {
                    DownloadVideo(vidpath + videoarr[videocount]);
                } else {
                    if (!(lgs.getAPP_VERSION().equalsIgnoreCase(Integer.toString(versionCode)))) {
                        String path = lgs.getAPP_PATH();
                        Update(path);
                    } else {
                        Intent i = new Intent(GSKOralCareActivity.this, ShowUpdateActivity.class);
                        startActivity(i);
                        GSKOralCareActivity.this.finish();
                    }
                }
            }


        }

    }


    public void DownloadVideo(String path) {

        new DownloadVid(GSKOralCareActivity.this).execute(path);

    }

    private class DownloadVid extends AsyncTask<String, Data, String> {


        private Context context;

        DownloadVid(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Downloading Video " + videoarr[videocount]);
            dialog.setCancelable(false);
            dialog.show();

            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                data1 = new Data();
                data1.name = "Downloading Application";
                publishProgress(data1);

                version = getPackageManager().getPackageInfo(
                        getPackageName(), 0).versionName;


                data1.name = "Version : " + version;
                publishProgress(data1);

                // download application
                URL url = new URL(params[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                // c.setDoOutput(true);
                c.getResponseCode();
                c.connect();
                length = c.getContentLength();

                String size = new DecimalFormat("##.##")
                        .format((double) ((double) length / 1024) / 1024)
                        + " MB";

                String PATH = Environment.getExternalStorageDirectory().getPath()
                        + "/GSKOralCare/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, videoarr[videocount]);
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                int bytes = 0;
                byte[] buffer = new byte[1024];
                int len1 = 0;

                while ((len1 = is.read(buffer)) != -1) {

                    bytes = (bytes + len1);

                    s = new DecimalFormat("##.##")
                            .format((double) ((double) (bytes / 1024)) / 1024);

                    p = s.length() == 3 ? s + "0" : s;

                    p = p + " MB";
                    data1.value = (int) ((double) (((double) bytes) / length) * 100);

                    data1.name = "Download " + p + "/" + size;
                    publishProgress(data1);

                    fos.write(buffer, 0, len1);

                }
                fos.close();
                is.close();

                return Constant.KEY_SUCCESS;

            } catch (NameNotFoundException e) {
                // TODO Auto-generated catch block
                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this,
                        AlertMessageActivity.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            } catch (MalformedURLException e) {

                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this,
                        AlertMessageActivity.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this,
                        AlertMessageActivity.MESSAGE_SOCKETEXCEPTION, "socket",
                        e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            } catch (Exception e) {
                final AlertMessageActivity message = new AlertMessageActivity(
                        GSKOralCareActivity.this,
                        AlertMessageActivity.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        message.showMessage();
                    }
                });
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub

            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();

            if (result.equals(Constant.KEY_SUCCESS)) {

                boolean fileflag = false;
                videocount++;
                if (videocount < videoarr.length) {
                    int l;
                    for (l = videocount; l < videoarr.length; l++) {
                        if (!new File(Path + videoarr[l]).exists()) {
                            videocount = l;
                            DownloadVideo(vidpath + videoarr[l]);

                            break;
                        }
                    }
                    if (l > videocount) {
                        fileflag = true;
                    }

                } else {
                    fileflag = true;
                }

                if (fileflag) {
                    if (!(lgs.getAPP_VERSION().equalsIgnoreCase(Integer
                            .toString(versionCode)))) {

                        String path = data.getApp_path().get(0);
                        Update(path);
                    } else {

                        Intent i = new Intent(GSKOralCareActivity.this,
                                ShowUpdateActivity.class);
                        startActivity(i);
                        GSKOralCareActivity.this.finish();

                    }
                }
            }

        }


    }

    class Data {
        int value;
        String name;
    }

    public void Update(String path) {

        Intent i = new Intent(GSKOralCareActivity.this,
                AutoupdateActivity.class);
        i.putExtra("path", path);
        startActivity(i);
        this.finish();
    }

    public boolean CheckNetAvailability() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            if (connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                    || connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                // we are connected to a network
                connected = true;
            }
        }

        return connected;

    }

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

        return intime;
    }

    public void onBackPressed() {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }


}
