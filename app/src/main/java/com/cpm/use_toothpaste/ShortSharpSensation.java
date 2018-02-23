package com.cpm.use_toothpaste;

import com.cpm.gsk.R;
import com.cpm.mainmenu.Startsales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShortSharpSensation extends Activity implements OnClickListener{

	Button btnnext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortsharpsensation);
		
		btnnext=(Button) findViewById(R.id.btnnexts);
		
		btnnext.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		
		if(id==R.id.btnnexts){
			
			//Intent in=new Intent(getApplicationContext(),NaturalWhiteness.class);
			Intent in=new Intent(getApplicationContext(),SensodyneWhitingVideo.class);
			startActivity(in);
			finish();
		}
		
	}
	
	public void onBackPressed() {

		return;
	}
	
}
