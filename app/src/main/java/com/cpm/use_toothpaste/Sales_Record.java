package com.cpm.use_toothpaste;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.cpm.database.GskDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.SkuDataModel;
import com.cpm.delegates.UploadSaveBean;
import com.cpm.gsk.R;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.mainmenu.MainMenuScreenActivity;
import com.cpm.mainmenu.Startsales;

public class Sales_Record extends Activity implements OnClickListener {

	GskDatabase db;
	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;
	String latitude = "0.0";
	String longitude = "0.0";
	public static int pos[];
	private int _mid;
	String userid;
	String visitdate;
	String storeid;
	String storelat;
	String storelong;
	private int myear;
	private int month;
	private int day;
	String brand_id;
	String brand, call_status;
	String intime, version;
	public ArrayList<SkuDataModel> skuinfo = new ArrayList<SkuDataModel>();
	public ArrayList article_no = new ArrayList();
	static Editor e2;
	public Keyboard mKeyboard;
	public CustomKeyboardView mKeyboardView;
	ListView lv;
	ArrayList<UploadSaveBean> sdata = new ArrayList<UploadSaveBean>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales_record);

		Button savesale = (Button) findViewById(R.id.savesale);
		Button nextcall = (Button) findViewById(R.id.nextcall);

		lv = (ListView) findViewById(R.id.listView1);
		db = new GskDatabase(getApplicationContext());
		db.openDB();

		intime = getCurrentTime();

		storeid = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("store_id", "");

		visitdate = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("date", "");

		userid = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("username", "");

		version = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("version", "");

		e2 = this.getSharedPreferences("brand_count",
				Context.MODE_WORLD_READABLE).edit();

		brand_id = this.getSharedPreferences("brand_count",
				Context.MODE_WORLD_WRITEABLE).getString("brand_id", "");
		brand = this.getSharedPreferences("brand_count",
				Context.MODE_WORLD_WRITEABLE).getString("brand", "");

		call_status = this.getSharedPreferences("brand_count",
				Context.MODE_WORLD_WRITEABLE).getString("count_status", "");

		 skuinfo = db.getSkuData();
		// skuinfo = db.getSkuDataInfo(brand_id);

		pos = new int[skuinfo.size()];

		for (int i = 0; i < skuinfo.size(); i++) {

			pos[i] = -1;
			skuinfo.get(i).setSku_quantity("");

		}

		savesale.setOnClickListener(this);
		nextcall.setOnClickListener(this);

		mKeyboard = new Keyboard(this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this));

		mKeyboardView.setVisibility(View.INVISIBLE);

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sales_Record.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sales_Record.this.finish();

			}
		});

		if (skuinfo.size() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please Download Data First", Toast.LENGTH_LONG).show();
		} else {
			lv.setAdapter(new Calls_Adapter(getApplicationContext()));

			lv.setOnScrollListener(new OnScrollListener() {
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {

				}

				@Override
				public void onScrollStateChanged(AbsListView arg0, int arg1) {
					
					lv.invalidateViews();
				}

			});

		}
	}

	public void onClick(View v) {
		
		if (v.getId() == R.id.nextcall) {

			e2 = this.getSharedPreferences("user_detail",
					Context.MODE_WORLD_READABLE).edit();

			final String status = this.getSharedPreferences("user_detail",

			Context.MODE_WORLD_WRITEABLE).getString("status", "");

			if (status.equalsIgnoreCase("Y")) {

				if (call_status.equalsIgnoreCase("Yes")) {
					try {

						db = new GskDatabase(Sales_Record.this);
						db.openDB();

						if (db.CheckBrand(brand) > 0) {
							db.UpdateBrandCount(brand_id);
						} else {
							db.InsertBrandCallCountInfo(getMid(), brand_id,
									brand, "1", storeid);
						}

						e2 = this.getSharedPreferences("brand_count",
								Context.MODE_WORLD_READABLE).edit();

						e2.putString("count_status", "No");
						e2.commit();

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}

			}

			Intent intent = new Intent(this, Startsales.class);
			startActivity(intent);
			this.finish();
		}

		if (v.getId() == R.id.savesale) {

			mKeyboardView.setVisibility(View.INVISIBLE);

			e2 = this.getSharedPreferences("user_detail",
					Context.MODE_WORLD_READABLE).edit();

			final String status = this.getSharedPreferences("user_detail",
					Context.MODE_WORLD_WRITEABLE).getString("status", "");

			if (status.equalsIgnoreCase("Y")) {
				if (checkValue()) {

					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage("Are you sure you want to save")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											SaveData();

										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					AlertDialog alert = builder.create();

					alert.show();

				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage("Please Enter Data")
							.setCancelable(false)
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											dialog.cancel();
										}
									});
					AlertDialog alert = builder.create();
					alert.show();

				}

			} else {
				Intent intent = new Intent(Sales_Record.this,
						MainMenuScreenActivity.class);
				startActivity(intent);
				this.finish();
			}
		}

	}

	@Override
	public void onBackPressed() {
		
		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
			mKeyboardView.requestFocusFromTouch();
		} else {

		}
	}

	public void hide() {

		mKeyboardView.setVisibility(View.INVISIBLE);
		// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();
	}

	private void SaveData() {

		db.openDB();

		for (int i = 0; i < skuinfo.size(); i++) {

			/*int article = db.get_articleno(skuinfo.get(i).getSku_id());
			article_no.add(article);*/

		}
		
//		sdata = db.getUploadData(storeid);
		
//		if (sdata.size()>0) {
//			db.UpdateSales(storeid, skuinfo);
//		} else {
			db.InsertSaveSaleRecord(skuinfo, getMid(), article_no, storeid);
//		}
		
	

		if (call_status.equalsIgnoreCase("Yes")) {

			try {
				if (db.CheckBrand(brand) > 0) {
					db.UpdateBrandCount(brand_id);
				} else {
					db.InsertBrandCallCountInfo(getMid(), brand_id, brand, "1", storeid);
				}
				e2 = this.getSharedPreferences("brand_count",
						Context.MODE_WORLD_READABLE).edit();

				e2.putString("count_status", "No");
				e2.commit();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*for (int i = 0; i < skuinfo.size(); i++) {

			System.out.println("" + skuinfo.get(i).getSku_quantity());
		}*/

		Intent intent = new Intent(Sales_Record.this,
				MainMenuScreenActivity.class);
		startActivity(intent);
		this.finish();

	}

	public int getMid() {

		int mid = 0;

		db.openDB();

		mid = db.CheckMid(visitdate, storeid);

		if (mid == 0) {
			CoverageBean cdata = new CoverageBean();
			cdata.setStoreId(storeid);
			cdata.setVisitDate(visitdate);
			cdata.setUserId(userid);
			cdata.setInTime(intime);
			cdata.setOutTime(getCurrentTime());
			cdata.setReason("");
			cdata.setReasonid("0");
			cdata.setLatitude("0.0");
			cdata.setLongitude("0.0");
			cdata.setVersion(version);
			int mid1 = db.InsertCoverageData(cdata);

			_mid = mid1;

		} else {

			_mid = mid;
		}

		// mDataClassObj.CloseDB();
		return _mid;
	}

	public boolean checkValue() {

		boolean result = false;

		for (int i = 0; i < skuinfo.size(); i++) {
			if (!skuinfo.get(i).getSku_quantity().equals("")) {

				result = true;
				break;

			}

		}
		return result;

	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	private class Calls_Adapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mcontext;

		public Calls_Adapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return skuinfo.size();

		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.listrow1, null);
				holder = new ViewHolder();

				holder.text = (TextView) convertView.findViewById(R.id.text);
				holder.text2 = (EditText) convertView.findViewById(R.id.txtbox);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text2.setInputType(0);
			holder.text2.setId(position);
			holder.text.setText(skuinfo.get(position).getSku().toString());

			holder.text2.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					mKeyboardView.setVisibility(View.VISIBLE);
					return false;
				}
			});

			holder.text2.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					// mKeyboardView.setVisibility(View.VISIBLE);

					if (!hasFocus) {
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						if (value1.equals("")) {
							pos[position] = -1;
							skuinfo.get(position).setSku_quantity("");

						} else {
							pos[position] = 0;
							skuinfo.get(position).setSku_quantity(value1);
						}

					}
				}
			});

			if (pos[position] == 0) {

				holder.text2.setText(skuinfo.get(position).getSku_quantity());
			}
			if (pos[position] == -1) {

				holder.text2.setText("");

			}

			return convertView;
		}

		class ViewHolder {
			TextView text;
			EditText text2;

		}

	}

}