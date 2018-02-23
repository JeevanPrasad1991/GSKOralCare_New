package com.cpm.gsk;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cpm.alert_message.AlertMessageActivity;
import com.cpm.database.GskDatabase;
import com.cpm.gettersetter.AcheivementGetterSetter;
import com.cpm.gettersetter.BrandGroupSaleGetterSetter;
import com.cpm.gettersetter.CallPerformanceGetterSetter;
import com.cpm.gettersetter.PerformanceInfoGetterSetter;
import com.cpm.gettersetter.SkuSaleInfoGetterSetter;
import com.cpm.gettersetter.languagegettersetter;
import com.cpm.mainmenu.MainMenuScreenActivity;
import com.cpm.xmlhandler.AcheivementInfoXmlHandler;
import com.cpm.xmlhandler.CallMTDGetterSetter;
import com.cpm.xmlhandler.CallMTDGetterSetterXMLHandler;
import com.cpm.xmlhandler.CallfPerformancerInfoXmlHandler;
import com.cpm.xmlhandler.IncentiveDashGetterSetter;
import com.cpm.xmlhandler.IncentiveDashGetterSetterXMLHandler;
import com.cpm.xmlhandler.PerformancerInfoXmlHandler;
import com.cpm.xmlhandler.XMLHandlers;
import com.cpm.xmlhandler.languagexmlhandler;
import com.gsk.constants.Constant;

public class PerformanceDetailAuthentication extends Activity {
    GskDatabase mDataClassobj;
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data backdata;
    static Editor e11, e2;
    static int count = 1;
    String userid;
    String val_target = "";
    public String userinfo_xml;
    public PerformanceInfoGetterSetter data;
    public CallPerformanceGetterSetter call_data;
    public languagegettersetter lang_data;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    IncentiveDashGetterSetter incentiveDashGetterSetter;
    CallMTDGetterSetter callMTDGetterSetter;
    TextView val_trgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenuscreen);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        userid = preferences.getString("USERNAME", null);
        mDataClassobj = new GskDatabase(this);
        mDataClassobj.openDB();
        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
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
                                public void onClick(DialogInterface dialog, int id) {
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
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Updating Performance");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(Void... params) {


            try {

                backdata = new Data();

                SAXParserFactory saxPF = SAXParserFactory.newInstance();
                SAXParser saxP = saxPF.newSAXParser();
                XMLReader xmlR = saxP.getXMLReader();

                SoapObject request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                request.addProperty("Type","GSKORAL_SKU_TARGET_PERFORMANCE");

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(
                        Constant.NEW_URL);

                androidHttpTransport.call(
                        Constant.SOAP_ACTION_UNIVERSAL, envelope);
                Object result = (Object) envelope.getResponse();
                String userinfo_xml = result.toString();
                //now copied
                if (result.toString().equals("Failure")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else if (result.toString().equals("False")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });
                }

                else {

                    PerformancerInfoXmlHandler myXMLHandler = new PerformancerInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);

                    data = PerformancerInfoXmlHandler.data;

                    GskDatabase db = new GskDatabase(getApplicationContext());
                    db.openDB();

                    //db.delete_performance_data();
                    //db.InsertPerformanceData(data);
                    db.CloseDB();

                    backdata.value = 30;
                    backdata.name = "Updating Sku Performance";
                    publishProgress(backdata);

                }

                //code added by Ankur
                //GSKORAL_SKU_SALE property
                request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                request.addProperty("Type","GSKORAL_SKU_SALE");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                Log.v("GSKORAL_SKU_SALE ",result.toString());
                GskDatabase db = new GskDatabase(getApplicationContext());
                db.openDB();
                if(result.toString()!=null){
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    int eventType = xpp.getEventType();

                    SkuSaleInfoGetterSetter skuSaleInfoGetterSetter = XMLHandlers.skuSaleHandler(xpp, eventType);
                    if (skuSaleInfoGetterSetter.getSku_id().size() > 0) {
                        String skuSaleTable = skuSaleInfoGetterSetter.getSku_sale_table_name();
                        db.createTable(skuSaleTable);
                        db.deleteTable(Constant.KEY_SKU_SALE);
                        db.insertINToSKUSale(skuSaleInfoGetterSetter);

                    }

                }


                //GSKORAL_BRAND_GROUP_SALE property
                request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                    request.addProperty("Type","GSKORAL_BRAND_GROUP_SALE");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();
                Log.v("GSKORAL_BRAND_GRO_SALE ",result.toString());

                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            message.showMessage();
                        }
                    });

                }

                else if (result.toString().equals("False")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {
                    userinfo_xml=result.toString();
                    if (userinfo_xml!=null){

                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        XmlPullParser xpp = factory.newPullParser();

                        xpp.setInput(new StringReader(userinfo_xml));
                        xpp.next();

                        int eventType = xpp.getEventType();
                        GskDatabase dbNew = new GskDatabase(getApplicationContext());
                        dbNew.openDB();
                      BrandGroupSaleGetterSetter brandGroupSaleGetterSetter = XMLHandlers.BrandGroupSaleHandler(xpp, eventType);
                            if (brandGroupSaleGetterSetter.getTarget().size() > 0) {
                                String brandgroupTable = brandGroupSaleGetterSetter.getMetadataTable();
                                db.createBrandGTable(brandgroupTable);
                                db.deleteBrandGTable(Constant.KEY_BRAND_GROUP_SALE);
                                db.insertBrandGroupSale(brandGroupSaleGetterSetter);
                                db.CloseDB();
                            }

                    }

                }


                //the end
                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type","GSKORAL_BRAND_TARGET_ACHIEVEMENT");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                System.out.print(result);
                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            message.showMessage();
                        }
                    });

                }

                else if (result.toString().equals("False")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {

                    CallfPerformancerInfoXmlHandler myXMLHandler = new CallfPerformancerInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);
                    call_data = CallfPerformancerInfoXmlHandler.data;
                    GskDatabase dbc = new GskDatabase(getApplicationContext());
                    dbc.openDB();
                    dbc.delete_call_performance_data();
                    dbc.InsertCallPerformanceData(call_data);
                    dbc.CloseDB();

                    backdata.value = 60;
                    backdata.name = "Updating Call Performance";
                    publishProgress(backdata);

                }


                // Language master

                request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                request.addProperty("Type","GSKORAL_LANGUAGE_MASTER");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL,
                        envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                System.out.print(result);
                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            message.showMessage();
                        }
                    });

                }

                else if (result.toString().equals("False")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {

                    languagexmlhandler myXMLHandler = new languagexmlhandler();
                    xmlR.setContentHandler(myXMLHandler);

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);
                    lang_data = languagexmlhandler.data;

                    GskDatabase dba = new GskDatabase(getApplicationContext());
                    dba.openDB();
                    dba.delete_language_data();
                    dba.InsertLanguageData(lang_data);
                    dba.CloseDB();

                    backdata.value = 70;
                    backdata.name = "Updating language";
                    publishProgress(backdata);

                }

                request = new SoapObject(Constant.NAMESPACE,
                        Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);

                request.addProperty("UserName", userid);
                request.addProperty("Type","GSKORAL_CALL_TARGET_ACHIEVEMENT");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                System.out.print(result);
                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else if (result.toString().equals("False")) {

                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {

                    AcheivementInfoXmlHandler myXMLHandler = new AcheivementInfoXmlHandler();
                    xmlR.setContentHandler(myXMLHandler);

                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);

                    AcheivementGetterSetter acheivement_data = AcheivementInfoXmlHandler.data;

                    try {
                        e2 = PerformanceDetailAuthentication.this.getSharedPreferences("user_detail", Context.MODE_WORLD_READABLE).edit();
                        e2.putString("acheivement", acheivement_data.getACHIEVEMENT().get(0));
                        e2.putString("target", acheivement_data.getTarget().get(0));
                        e2.commit();
                    } catch (Exception e) {
                    }

                    backdata.value = 80;
                    backdata.name = "Updating Acheivement Performance";
                    publishProgress(backdata);

                }


                //////////////OHC_INCENTIVE downloadddddddddddddddd
                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type","OHC_INCENTIVE");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                System.out.print(result);
                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {
                    IncentiveDashGetterSetterXMLHandler myXMLHandler = new IncentiveDashGetterSetterXMLHandler();
                    xmlR.setContentHandler(myXMLHandler);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);
                    incentiveDashGetterSetter = IncentiveDashGetterSetterXMLHandler.data;
                    GskDatabase dbc = new GskDatabase(getApplicationContext());
                    dbc.openDB();
                    dbc.delete_incentive_dashboard_data();
                    dbc.InsertIncentiveDashboardData(incentiveDashGetterSetter);
                    dbc.CloseDB();
                    backdata.value = 90;
                    backdata.name = "Updating OHC Incentive";
                    publishProgress(backdata);

                }

                //////////////OHC_CALL_REPORT downloadddddddddddddddd
                request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", userid);
                request.addProperty("Type","OHC_CALL_REPORT");

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
                androidHttpTransport.call(Constant.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();
                userinfo_xml = result.toString();
                System.out.print(result);
                if (result.toString().equals("Failure")) {
                    final AlertMessageActivity message = new AlertMessageActivity(
                            PerformanceDetailAuthentication.this,
                            AlertMessageActivity.Message_problem, "acra", null);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            message.showMessage();
                        }
                    });

                }

                else {
                    CallMTDGetterSetterXMLHandler myXMLHandler = new CallMTDGetterSetterXMLHandler();
                    xmlR.setContentHandler(myXMLHandler);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(userinfo_xml));
                    xmlR.parse(is);
                    callMTDGetterSetter = CallMTDGetterSetterXMLHandler.data;
                    GskDatabase dbc = new GskDatabase(getApplicationContext());
                    dbc.openDB();
                    dbc.delete_callMTD_dashboard_data();
                    dbc.InsertCALLMTDDashboardData(callMTDGetterSetter);
                    dbc.CloseDB();
                    backdata.value = 100;
                    backdata.name = "Updating Call Report";
                    publishProgress(backdata);

                }



                return Constant.KEY_SUCCESS;

            } catch (MalformedURLException e) {
                String str = AlertMessageActivity.Message_problem;

                final AlertMessageActivity message = new AlertMessageActivity(
                        PerformanceDetailAuthentication.this, str, "acra", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                String str = AlertMessageActivity.Message_network;
                final AlertMessageActivity message = new AlertMessageActivity(
                        PerformanceDetailAuthentication.this, str, "socket", e);

                count++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (count < 10) {
                            new BackgroundTask(
                                    PerformanceDetailAuthentication.this)
                                    .execute();
                        } else {
                            message.showMessage();
                            count = 1;
                        }
                    }
                });
            }

            catch (Exception e) {

                String str = AlertMessageActivity.Message_problem;
                final AlertMessageActivity message = new AlertMessageActivity(
                        PerformanceDetailAuthentication.this, str, "acra", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
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
                Intent i = new Intent(PerformanceDetailAuthentication.this, MainMenuScreenActivity.class);
                startActivity(i);
                PerformanceDetailAuthentication.this.finish();

            }
        }

    }

    class Data {
        int value;
        String name;
    }

}
