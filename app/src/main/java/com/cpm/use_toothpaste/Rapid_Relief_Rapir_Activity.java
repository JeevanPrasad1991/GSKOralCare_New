package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cpm.gsk.R;
import com.cpm.mainmenu.MainMenuScreenActivity;
import com.cpm.mainmenu.Sensodyne_Offer;

public class Rapid_Relief_Rapir_Activity extends Activity implements
		OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rapid_relief_repair);

		Button buy = (Button) findViewById(R.id.buy);
		Button notnow = (Button) findViewById(R.id.notnow);
		buy.setOnClickListener(this);
		notnow.setOnClickListener(this);

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Rapid_Relief_Rapir_Activity.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Rapid_Relief_Rapir_Activity.this.finish();

			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.buy) {

			Intent intent = new Intent(this, Sales_Record.class);
			startActivity(intent);
			Rapid_Relief_Rapir_Activity.this.finish();
		}
		if (v.getId() == R.id.notnow) {

			Intent intent = new Intent(this, RapidRelief_Activity.class);
			startActivity(intent);
			Rapid_Relief_Rapir_Activity.this.finish();
		}

	}

	public void onBackPressed() {

		return;
	}
}