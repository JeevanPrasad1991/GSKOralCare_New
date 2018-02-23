package com.cpm.upload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Timer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cpm.alert_message.AlertMessageActivity;
import com.cpm.database.GskDatabase;
import com.cpm.delegates.CalCountBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.UploadDataModel;
import com.cpm.delegates.UploadSaveBean;
import com.cpm.gsk.R;
import com.gsk.constants.Constant;

public class UploadSale extends Activity {

	private TextView licenceinfo;

	private Handler handler = new Handler();

	ProgressBar progressBar;
	GskDatabase mDataClassobj;
	int timerCount = 0;
	String visitdate;

	final Timer timer = new Timer();

	boolean isConnected = true;
	boolean isFailure = false;
	String mid = "";
	static Editor e2;

	int versionCode;
	HttpTransportSE androidHttpTransport;

	SoapObject result;
	String datacheck = "";
	String[] words;
	String validity, userid;
	SoapObject request;
	SoapSerializationEnvelope envelope;
	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	boolean status = false;
	private Data backdata;
	String store_id;
	 String processid = "";
	ArrayList<CoverageBean> data = new ArrayList<CoverageBean>();
	ArrayList<UploadSaveBean> sdata = new ArrayList<UploadSaveBean>();
	ArrayList<CalCountBean> caldata = new ArrayList<CalCountBean>();
	static ArrayList<UploadDataModel> uploaddata = new ArrayList<UploadDataModel>();
	static int count = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mainmenuscreen);

		e2 = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_READABLE).edit();

		store_id = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("store_id", "");

		visitdate = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("date", "");

		userid = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("username", "");
		
		processid = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("processid", "");

		mDataClassobj = new GskDatabase(this);
		mDataClassobj.openDB();

		new BackgroundTask(this).execute();
	

	}

	@Override
	protected void onResume() {
		
		super.onResume();
		mDataClassobj.openDB();

	}

	@Override
	protected void onPause() {
		super.onPause();
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
			dialog.setTitle("Upload Data");
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
				data = mDataClassobj.getcoverage();

				for (int i = 0; i < data.size(); i++) {
					String onXMLCov = "[DATA][USER_DATA][STORE_ID]"
							+ data.get(i).getStoreId()
							+ "[/STORE_ID]" 
							+ "[VISIT_DATE]"
							+ data.get(i).getVisitDate()
							+ "[/VISIT_DATE]"
							+ "[LATITUDE]"
							+  "0"
							+ "[/LATITUDE]"
							+ "[LONGITUDE]"
							+ "0"
							+ "[/LONGITUDE]"									
							+ "[IN_TIME]"
							+ data.get(i).getInTime()
							+ "[/IN_TIME][OUT_TIME]"
							+ data.get(i).getOutTime()
							+ "[/OUT_TIME][UPLOAD_STATUS]N"
							+ "[/UPLOAD_STATUS][CREATED_BY]" + userid
							+ "[/CREATED_BY][REASON_REMARK]"
							+ "0"
							+ "[/REASON_REMARK][REASON_ID]"
							+ "0"
							+ "[/REASON_ID][APP_VERSION]" + data.get(i).getVersion()
							+ "[/APP_VERSION] [USER_ID]" + userid
							+ "[/USER_ID]" + "[PROCESS_ID]" + processid 
							+ "[/PROCESS_ID][SUB_REASON_ID]"
							+ "0" 
							+ "[/SUB_REASON_ID][IMAGE_ALLOW]" + "0"
							+ "[/IMAGE_ALLOW][STORE_IMAGE]" + "0"
							+ "[/STORE_IMAGE][/USER_DATA][/DATA]";
					
					SoapObject request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_UPLOAD_DR_STORE_COVERAGE_LOC);
					request.addProperty("onXML", onXMLCov);
					SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true;
					envelope.setOutputSoapObject(request);
					HttpTransportSE	androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);
					androidHttpTransport.call(Constant.SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE, envelope);
					Object result = (Object) envelope.getResponse();
					datacheck = result.toString();
					if (datacheck.equals("False")) {
						isFailure = true;
						break;
					}

					else if (datacheck.equals("Failure")) {
						isFailure = true;
						break;
					} else {
						words = datacheck.split("\\;");
						validity = (words[0]);
						mid = (words[1]);

						backdata.value = 30;
						backdata.name = "Coverage Data Downloading";
						publishProgress(backdata);
					}




					sdata = mDataClassobj.getUploadData(data.get(i).getStoreId());
					String final_data_xml = "";
					
					if(!(sdata.size()==0)){
						for (int i2 = 0; i2 < sdata.size(); i2++) {
							String qty;
							if (sdata.get(i2).getSku_qty().equals("")) {
								qty = "0";
							} else {
								qty = sdata.get(i2).getSku_qty();
							}
							String onXML = "[USER_DATA][MID]"
									+ mid
									+ "[/MID][CREATED_BY]"
									+ userid
									+ "[/CREATED_BY][SKU_CD]"
									+ Integer.parseInt(sdata.get(i2).getSku_id())
									+ "[/SKU_CD]"
									+"[QTY_SALE]"
									+ qty
									+ "[/QTY_SALE][/USER_DATA]";

							final_data_xml = final_data_xml + onXML;

						}
						
						
						final_data_xml = "[DATA]" + final_data_xml
								+ "[/DATA]";
							
						SoapObject request1 = new SoapObject(Constant.NAMESPACE, Constant.METHOD_UPLOAD_STOCK_XML_DATA);
							
							request1.addProperty("MID", mid);
							request1.addProperty("KEYS", "STORE_SALE_ORAL_CARE");
							request1.addProperty("USERNAME", userid);  
							request1.addProperty("XMLDATA", final_data_xml);
							SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
							envelope1.dotNet = true;
							envelope1.setOutputSoapObject(request1);

							HttpTransportSE	androidHttpTransport1 = new HttpTransportSE(
									Constant.NEW_URL);

							androidHttpTransport1.call(Constant.SOAP_ACTION_UPLOAD_ASSET_XMLDATA, envelope1);
						result = (Object) envelope1.getResponse();
						
						datacheck = result.toString();
							if (datacheck.equals("False")) {
								isFailure = true;
								break;

							}

							else if (datacheck.equals("Failure")) {

								isFailure = true;
								break;
							}

							else {
								backdata.value = 60;
								backdata.name = "Uploading Store Data";
								publishProgress(backdata);
								//datadelete
								mDataClassobj.openDB();
								mDataClassobj.delete_salerecord_info(data.get(i).getMID());
								

							}
					}

					if (isFailure) {
						break;
					}

					caldata = mDataClassobj.getcall_count(data.get(i).getStoreId());
					
					String final_xml = "";
					
					if (!(caldata.size()==0)) {
					
					for (int i1 = 0; i1 < caldata.size(); i1++) {
						
						String onXML1 = "[USER_DATA][MID]"
								+ mid
								+ "[/MID][CREATED_BY]"
								+ userid
								+ "[/CREATED_BY][BRAND_CD]"
								+ Integer.parseInt(caldata.get(i1).getBrandid())
								+ "[/BRAND_CD]"
								+ "[CALL_COUNT]"
								+  Integer.parseInt(caldata.get(i1).getCount())
								+ "[/CALL_COUNT][/USER_DATA]";

						final_xml = final_xml + onXML1;
						}
					
					final_xml = "[DATA]" + final_xml + "[/DATA]";
						request = new SoapObject(Constant.NAMESPACE, Constant.METHOD_UPLOAD_STOCK_XML_DATA);
						request.addProperty("MID", mid);
						request.addProperty("KEYS", "CALL_DATA_ORAL_CARE");
						request.addProperty("USERNAME", "testhfd");
						request.addProperty("XMLDATA", final_xml);
						envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);
						androidHttpTransport = new HttpTransportSE(Constant.NEW_URL);

						androidHttpTransport.call(
								Constant.SOAP_ACTION_UPLOAD_ASSET_XMLDATA, envelope);
						 result = (Object) envelope.getResponse();
						datacheck = result.toString();
						if (datacheck.equals("False")) {

							isFailure = true;
							break;

						}
						else if (datacheck.equals("Failure")) {
							isFailure = true;
							break;
						}
						else {
							backdata.value = 90;
							backdata.name = "Call Data Uploading";
							publishProgress(backdata);
							//delete data
							mDataClassobj.openDB();
							mDataClassobj.delete_brandcount_info(data.get(i).getMID());
						}
					}
					
					if (isFailure) {
						break;
					}

				}
				if (isFailure) {

					String str = AlertMessageActivity.Message_problem;
					final AlertMessageActivity message = new AlertMessageActivity(
							UploadSale.this, str, "download", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							
							message.showMessage();
						}
					});

				} else {
					String str = "Data Uploaded Successfully";
					mDataClassobj.openDB();
					mDataClassobj.delete_uploaded_data();
					return Constant.KEY_SUCCESS;
				}

			} catch (MalformedURLException e) {

				final AlertMessageActivity message = new AlertMessageActivity(UploadSale.this,
						AlertMessageActivity.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						message.showMessage();
					}
				});
			} catch (IOException e) {
				final AlertMessageActivity message = new AlertMessageActivity(UploadSale.this,
						AlertMessageActivity.MESSAGE_SOCKETEXCEPTION, "socket_upload", e);
				count++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if(count<10)
						{
							new BackgroundTask(UploadSale.this).execute();
						}
						else
						{
							message.showMessage();
							count = 1;
						}
					}
				});
			} catch (Exception e) {
				final AlertMessageActivity message = new AlertMessageActivity(
						UploadSale.this,
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
				AlertMessageActivity message = new AlertMessageActivity(UploadSale.this, AlertMessageActivity.MESSAGE_UPLOAD, "success_upload", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessageActivity message = new AlertMessageActivity(UploadSale.this, AlertMessageActivity.MESSAGE_ERROR + result, "success", null);
				message.showMessage();
			}

		}
	}

	class Data {
		int value = 0;
		String name;
	}

}
