package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cpm.gsk.R;
import com.cpm.mainmenu.MainMenuScreenActivity;

public class Parandontax_Offer_Activity extends Activity implements
		OnClickListener {

	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parodontax_offer);

		Button buy = (Button) findViewById(R.id.buy);
		Button notnow = (Button) findViewById(R.id.notnow);
		buy.setOnClickListener(this);
		notnow.setOnClickListener(this);

		app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = app_preferences.edit();

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Parandontax_Offer_Activity.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Parandontax_Offer_Activity.this.finish();

			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.buy) {

			Intent intent = new Intent(this, Sales_Record.class);
			startActivity(intent);
			Parandontax_Offer_Activity.this.finish();
		}

		if (v.getId() == R.id.notnow) {

			Intent intent = new Intent(this, OralCare_Activity.class);
			startActivity(intent);
			Parandontax_Offer_Activity.this.finish();
		}

	}

	public void onBackPressed() {

		return;
	}
}