package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cpm.database.GskDatabase;
import com.cpm.gsk.R;
import com.cpm.mainmenu.Startsales;

public class AfterNewVideo extends Activity implements OnClickListener{
	
	Button btnnext,btnbuy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afternewvideo_layout);
		
		btnnext=(Button) findViewById(R.id.btnnexts);
		
		btnnext.setOnClickListener(this);
		
		btnbuy=(Button) findViewById(R.id.buythis);

		btnbuy.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		
		if(id==R.id.btnnexts){
			
			Intent in=new Intent(getApplicationContext(),ShortSharpSensation.class);
			startActivity(in);
			finish();
		}

		if(id==R.id.buythis){
			
			GskDatabase  mdatabaseObj = new GskDatabase(getApplicationContext());
			mdatabaseObj.openDB();
			String sensodyne_id = mdatabaseObj.getBrandId("Sensodyne");
			Editor e2 = this.getSharedPreferences("brand_count",
					Context.MODE_WORLD_READABLE).edit();

			e2.putString("brand", "Sensodyne");
			e2.putString("brand_id", sensodyne_id);
			e2.putString("count_status", "Yes");
			e2.commit();

			Intent intent = new Intent(getApplicationContext(),Sales_Record.class);
			startActivity(intent);
			finish();
			
		}
		
	}
	
	public void onBackPressed() {

		return;
	}

}
