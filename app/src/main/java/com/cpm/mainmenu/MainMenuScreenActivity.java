package com.cpm.mainmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.database.GskDatabase;
import com.cpm.delegates.CallPerformanceDataModel;

import com.cpm.delegates.SkuDataModel;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.gettersetter.BrandGroupSaleGetterSetter;
import com.cpm.gettersetter.SkuSaleInfoGetterSetter;

import com.cpm.gsk.GSKOralCareActivity;
import com.cpm.gsk.R;
import com.cpm.upload.UploadSale;

import com.cpm.use_toothpaste.ShortSharpSensation;
import com.cpm.xmlhandler.CallMTDGetterSetter;
import com.cpm.xmlhandler.IncentiveDashGetterSetter;

public class MainMenuScreenActivity extends Activity implements OnClickListener {

    static Editor e11, e2;
    Button salesession;
    Button practicesession;
    Button download, sensitometer, upload, exit;
    private LocationManager m_gpsManager = null;
    String visitdate;
    ListView listsales, incentive_list;
    SharedPreferences preferences;
    ArrayList<SkuSaleInfoGetterSetter> skusaleList = new ArrayList<>();
    ArrayList<CallPerformanceDataModel> callperformancedata = new ArrayList<CallPerformanceDataModel>();
    ArrayList<BrandGroupSaleGetterSetter> brandgroupdatalist = new ArrayList<>();
    ArrayList<SkuDataModel> skuinfo = new ArrayList<SkuDataModel>();
    ///////new addd
    TextView calltxt, callmtb_txt;
    ArrayList<IncentiveDashGetterSetter> incetvieDATAList = new ArrayList<>();
    CallMTDGetterSetter callMTDGetterSetter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenuscreen);

    }

    @Override
    protected void onResume() {
        super.onResume();
        salesession = (Button) findViewById(R.id.dashboard_startsalessessionbtn);
        practicesession = (Button) findViewById(R.id.dashboard_startpractisesessionbtn);
        download = (Button) findViewById(R.id.download);
        upload = (Button) findViewById(R.id.upload);
        exit = (Button) findViewById(R.id.exit);
        sensitometer = (Button) findViewById(R.id.sensitometer);
        ////newwwwwwwwwwwwwwww
        incentive_list = (ListView) findViewById(R.id.incentive_list);
        calltxt = (TextView) findViewById(R.id.calltxt);
        callmtb_txt = (TextView) findViewById(R.id.callmtb_txt);

        salesession.setOnClickListener(this);
        practicesession.setOnClickListener(this);
        download.setOnClickListener(this);
        upload.setOnClickListener(this);
        exit.setOnClickListener(this);
        sensitometer.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        visitdate = this.getSharedPreferences("user_detail", Context.MODE_WORLD_WRITEABLE).getString("date", "");
        TextView toothpaste_name = (TextView) findViewById(R.id.toothpaste_name);
        TextView toothpaste_val_target = (TextView) findViewById(R.id.toothpaste_val_target);
        TextView toothpaste_val_acv = (TextView) findViewById(R.id.toothpaste_val_acv);
        TextView toothbrush_name = (TextView) findViewById(R.id.toothbrush_name);
        TextView toothbrush_target = (TextView) findViewById(R.id.toothbrush_target);
        TextView toothbrush_acv = (TextView) findViewById(R.id.toothbrush_acv);
        TextView toothpaste_act_per = (TextView) findViewById(R.id.toothpaste_act_per);
        TextView toothbrush_acv_per = (TextView) findViewById(R.id.toothbrush_acv_per);
        listsales = (ListView) findViewById(R.id.listview_sales);
        listsales.setAdapter(new Sales_Adapter(MainMenuScreenActivity.this));
        GskDatabase db = new GskDatabase(getApplicationContext());
        db.openDB();
        m_gpsManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", false);
        skuinfo = db.getSkuData();

        skusaleList = db.getSkusalesData();
        brandgroupdatalist = db.getBrandGroupData();
        ///incentive.................
        incetvieDATAList = db.getIncentiveMasterData();
        if (incetvieDATAList.size() > 0) {
            incentive_list.setAdapter(new MyAdapterIncentive());
        }
        /////////////call mTD............

        callMTDGetterSetter = db.getcallMTDData();
        if (callMTDGetterSetter.getMtd_call() != null) {
            calltxt.setText(callMTDGetterSetter.getToday_call().get(0) + "");
            callmtb_txt.setText(callMTDGetterSetter.getMtd_call().get(0) + "");
        } else {
            calltxt.setText("0");
            callmtb_txt.setText("0");
        }
        if (brandgroupdatalist.size() > 0) {
            try {
                toothpaste_name.setText(brandgroupdatalist.get(0).getBrandgroup().get(0));
                toothpaste_val_target.setText("Target :" + brandgroupdatalist.get(0).getTarget().get(0));
                toothpaste_val_acv.setText("Achivement :" + brandgroupdatalist.get(0).getAchievment().get(0));

                toothbrush_name.setText(brandgroupdatalist.get(1).getBrandgroup().get(0));
                toothbrush_target.setText("Target :" + brandgroupdatalist.get(1).getTarget().get(0));
                toothbrush_acv.setText("Achivement :" + brandgroupdatalist.get(1).getAchievment().get(0));

                double a = Double.parseDouble((brandgroupdatalist.get(0).getAchievment().get(0)));
                double b = Double.parseDouble(brandgroupdatalist.get(0).getTarget().get(0));

                double a1 = Double.parseDouble(brandgroupdatalist.get(1).getAchievment().get(0));
                double b1 = Double.parseDouble(brandgroupdatalist.get(1).getTarget().get(0));
                double c = a / b * 100;
                double c1 = a1 / b1 * 100;
                int i = (int) c;
                int i1 = (int) c1;
                toothpaste_act_per.setText("Achivement % :" + String.valueOf(i));
                toothbrush_acv_per.setText("Achivement % :" + String.valueOf(i1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        callperformancedata = db.getCallPerformanceDataInfo();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String storename = preferences.getString("store_name", "");
        final String storeaddress = preferences.getString("store_address", "");
        TextView title = (TextView) findViewById(R.id.dashboard_titleaddress);
        title.setText("Store Name : " + storename + "   Address : " + storeaddress);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainMenuScreenActivity.this, GSKOralCareActivity.class);
                startActivity(i);
                MainMenuScreenActivity.this.finish();
            }
        });

    }

    public void onClick(View v) {
        if (v.getId() == R.id.dashboard_startsalessessionbtn) {
            if (skuinfo.size() == 0) {
                Toast.makeText(getApplicationContext(), "Please Download Data First", Toast.LENGTH_LONG).show();
            } else {
                e2 = this.getSharedPreferences("user_detail", Context.MODE_WORLD_READABLE).edit();
                e2.putString("status", "Y");
                e2.commit();
                Intent intent = new Intent(this, ShortSharpSensation.class);
                startActivity(intent);
                MainMenuScreenActivity.this.finish();
            }
        }


        if (v.getId() == R.id.dashboard_startpractisesessionbtn) {
            if (skuinfo.size() == 0) {
                Toast.makeText(getApplicationContext(),
                        "Please Download Data First", Toast.LENGTH_LONG).show();
            } else {
                e2 = this.getSharedPreferences("user_detail", Context.MODE_WORLD_READABLE).edit();
                e2.putString("status", "N");
                e2.commit();
                Intent intent = new Intent(this, PracticalSession.class);
                startActivity(intent);
                MainMenuScreenActivity.this.finish();
            }
        }


        if (v.getId() == R.id.download) {

            if (CheckNetAvailability()) {
                Intent intent = new Intent(this, CompleteDownloadActivity.class);
                startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(getApplicationContext(), "No Network",
                        Toast.LENGTH_LONG).show();
            }

        }

        if (v.getId() == R.id.upload) {

            if (!CheckNetAvailability()) {
                Toast.makeText(getApplicationContext(), "No Network", Toast.LENGTH_LONG).show();

            } else {
                GskDatabase db = new GskDatabase(getApplicationContext());
                db.openDB();
                if (db.getcoverage().size() > 0) {
                    Intent i = new Intent(MainMenuScreenActivity.this, UploadSale.class);
                    startActivity(i);
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No Data For Upload", Toast.LENGTH_LONG).show();
                }


            }
        }

        if (v.getId() == R.id.exit) {


            Intent i = new Intent(MainMenuScreenActivity.this,
                    GSKOralCareActivity.class);
            startActivity(i);
            MainMenuScreenActivity.this.finish();
        }


        if (v.getId() == R.id.sensitometer) {

            Intent in = new Intent(this, SensodyneQuestions.class);
            startActivity(in);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.upload:

                if (!CheckNetAvailability()) {

                    Toast.makeText(getApplicationContext(), "No Network",
                            Toast.LENGTH_LONG).show();
                    break;
                } else {
                    GskDatabase db = new GskDatabase(getApplicationContext());
                    db.openDB();
                    if (db.getcoverage().size() > 0) {
                        Intent i = new Intent(MainMenuScreenActivity.this,
                                UploadSale.class);
                        startActivity(i);

                        this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "No Data For Upload", Toast.LENGTH_LONG).show();
                    }
                    break;

                }

            case R.id.exit:

                Intent i = new Intent(MainMenuScreenActivity.this,
                        GSKOralCareActivity.class);
                startActivity(i);
                MainMenuScreenActivity.this.finish();
                break;

        }
        return true;
    }

    public boolean CheckNetAvailability() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // we are connected to a network
            connected = true;
        }

        return connected;

    }


    public void onBackPressed() {

        return;
    }

    private class Calls_Adapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public Calls_Adapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return callperformancedata.size();

        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {

                convertView = mInflater.inflate(R.layout.call_performance_list_view, null);
                holder = new ViewHolder();
                holder.product = (TextView) convertView.findViewById(R.id.dashboard_7c_titletext);
                holder.mtd = (TextView) convertView.findViewById(R.id.dashboard_8c_titletext);
                holder.acheivement = (TextView) convertView.findViewById(R.id.dashboard_9c_titletext);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.product.setText(callperformancedata.get(position).getProduct().toString());
            holder.mtd.setText(callperformancedata.get(position).getMtd().toString());
            holder.acheivement.setText(callperformancedata.get(position).getAchievement().toString());


            return convertView;
        }

        class ViewHolder {
            TextView product, mtd, acheivement;

        }
    }

    private class Sales_Adapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public Sales_Adapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return skusaleList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.sales_performance_list_view, null);
                holder = new ViewHolder();
                holder.product = (TextView) convertView.findViewById(R.id.dashboard_1c_titletext);
                holder.target = (TextView) convertView.findViewById(R.id.dashboard_2c_titletext);
                holder.mtd = (TextView) convertView.findViewById(R.id.dashboard_4c_titletext);
                holder.acheivement = (TextView) convertView.findViewById(R.id.dashboard_5c_titletext);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.product.setText(skusaleList.get(position).getSku().get(0));
            holder.mtd.setText(skusaleList.get(position).getMtd().get(0));
            holder.target.setText(skusaleList.get(position).getTarget().get(0));
            holder.acheivement.setText(skusaleList.get(position).getAch_per().get(0));
            return convertView;
        }

        class ViewHolder {
            TextView product, mtd, target, acheivement;
        }
    }


    //////////////////INCENTIVE DATA.....................
    private class MyAdapterIncentive extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return incetvieDATAList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_incentive, null);
                holder.txt_monthname = (TextView) convertView.findViewById(R.id.txt_monthname);
                holder.txt_incentive = (TextView) convertView.findViewById(R.id.txt_incentive);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txt_incentive.setText(incetvieDATAList.get(position).getIncentive().get(0));
            holder.txt_monthname.setText(incetvieDATAList.get(position).getMonth_name().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView txt_monthname, txt_incentive;

        }
    }

}
