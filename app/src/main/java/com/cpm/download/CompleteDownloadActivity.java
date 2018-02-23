package com.cpm.download;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.Timer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cpm.alert_message.AlertMessageActivity;
import com.cpm.database.GskDatabase;

import com.cpm.gettersetter.BrandInfoGetterSetter;
import com.cpm.gettersetter.SkuInfoGetterSetter;
import com.cpm.gettersetter.StoreSaleInfoGetterSetter;
import com.cpm.gsk.R;


import com.cpm.xmlhandler.BrandInfoXmlHandler;
import com.cpm.xmlhandler.SkuInfoXmlHandler;
import com.cpm.xmlhandler.StoreSaleInfoXmlHandler;
import com.gsk.constants.Constant;

public class CompleteDownloadActivity extends Activity {

    private TextView licenceinfo;

    private Handler handler = new Handler();

    ProgressBar progressBar;

    int timerCount = 0;
    boolean failure = false;

    final Timer timer = new Timer();

    boolean isConnected = true;
    GskDatabase mDataClassobj;
    SoapObject request;
    SoapSerializationEnvelope envelope;
    HttpTransportSE androidHttpTransport;
    Object result;
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private String username;
    private Data data;
    String tar_val = "0";
    /**
     * Object of class editor used to edit shared preferences-------->
     */

    static Editor e1;
    static int count = 1;
    String userid;
    public String userinfo_xml;
    StoreSaleInfoGetterSetter storedata;
    BrandInfoGetterSetter branddata;
    SkuInfoGetterSetter skudata;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainmenuscreen);

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        userid = preferences.getString("USERNAME", null);

	/*	userid = this.getSharedPreferences("userdetail",
                Context.MODE_WORLD_WRITEABLE).getString("username", "");*/

        new BackgroundTask(this).execute();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mDataClassobj = new GskDatabase(this);
        mDataClassobj.openDB();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert Message");
            builder.setMessage("Downloading Data")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {

        super.onPause();

        mDataClassobj.CloseDB();
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Download Files");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                data = new Data();

                SAXParserFactory saxPF = SAXParserFactory.newInstance();
                SAXParser saxP = saxPF.newSAXParser();
                XMLReader xmlR = saxP.getXMLReader();
                SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type", "MAPPING_SKU_OHC");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                Object result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                if (result.toString().equals("Failure")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;

                } else if (result.toString().equals("False")) {
                    return Constant.METHOD_NAME_STORE_SALE;
                } else if (result.toString().equalsIgnoreCase("No Data")) {
                    return AlertMessageActivity.MESSAGE_NO_DATA;
                } else {

                    StoreSaleInfoXmlHandler myXMLHandler = new StoreSaleInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);

                    storedata = StoreSaleInfoXmlHandler.data;

                    data.value = 30;
                    data.name = "JCP Data Downloading";
                    publishProgress(data);

                }

                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type", "MAPPING_SKU_OHC");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();

                if (result.toString().equals("Failure")) {
                    return Constant.METHOD_NAME_BRAND_INFO;
                } else if (result.toString().equals("False")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;

                } else {

                    BrandInfoXmlHandler myXMLHandler = new BrandInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);

                    branddata = BrandInfoXmlHandler.data;

                    data.value = 60;
                    data.name = "Brand Data Downloading";
                    publishProgress(data);
                }

                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                request.addProperty("Type", "MAPPING_SKU_OHC");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();

                if (result.toString().equals("Failure")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;
                } else if (result.toString().equals("False")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;
                } else {

                    SkuInfoXmlHandler myXMLHandler = new SkuInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);

                    skudata = SkuInfoXmlHandler.data;

                    data.value = 80;
                    data.name = "Sku Data Downloading";
                    publishProgress(data);

                }
                mDataClassobj.openDB();
                mDataClassobj.delete_downloaded_data();
                mDataClassobj.InsertStoreSalesInfo(storedata, branddata, skudata);
                mDataClassobj.InsertBrandSalesInfo(branddata);
                mDataClassobj.InsertSkuSalesInfo(skudata);
                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type", "GSKORAL_VALUE_TARGET");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                if (result.toString().equals("Failure")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;
                } else if (result.toString().equals("False")) {
                    return Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD;
                } else {
                    SkuInfoXmlHandler myXMLHandler = new SkuInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);
                    skudata = SkuInfoXmlHandler.data;
                    if (skudata.getTarget_value().size() > 0) {
                        tar_val = skudata.getTarget_value().get(0);
                        if (!tar_val.equalsIgnoreCase("0")) {
                            editor = preferences.edit();
                            editor.putString("TargetValue", tar_val);
                            editor.commit();

                        } else {
                            tar_val = "0";
                        }
                    } else {
                        tar_val = "0";

                    }

                    data.value = 80;
                    data.name = "value target Downloading";
                    publishProgress(data);

                }

                data.value = 100;
                data.name = "Data is Saving";
                publishProgress(data);

                return Constant.KEY_SUCCESS;

            } catch (MalformedURLException e) {

                final AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
                        AlertMessageActivity.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
                        AlertMessageActivity.MESSAGE_SOCKETEXCEPTION,
                        "socket_download", e);
                count++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (count < 10) {
                            new BackgroundTask(CompleteDownloadActivity.this)
                                    .execute();
                        } else {
                            message.showMessage();
                            count = 1;
                        }
                    }
                });
            } catch (Exception e) {
                final AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
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
            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            dialog.dismiss();

            if (result.equals(Constant.KEY_SUCCESS)) {
                AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
                        AlertMessageActivity.MESSAGE_DOWNLOAD, "success", null);
                message.showMessage();
            } else if (result.equals(AlertMessageActivity.MESSAGE_NO_DATA)) {
                AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
                        AlertMessageActivity.MESSAGE_NO_DATA, "success", null);
                message.showMessage();
            } else if (!result.equals("")) {
                AlertMessageActivity message = new AlertMessageActivity(
                        CompleteDownloadActivity.this,
                        AlertMessageActivity.MESSAGE_ERROR + result, "success",
                        null);
                message.showMessage();
            }

        }

    }

    class Data {
        int value;
        String name;
    }

}
