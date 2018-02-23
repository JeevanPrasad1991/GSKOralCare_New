package com.cpm.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cpm.gsk.R;
import com.cpm.use_toothpaste.Sales_Record;

public class Sensodyne_Offer extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensodyne_offer);

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sensodyne_Offer.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sensodyne_Offer.this.finish();

			}
		});
	}

	public void onBackPressed() {

		return;
	}

	public void Checking(View v) {
		switch (v.getId()) {
		case R.id.offer_buy:
			Intent intent = new Intent(this, Sales_Record.class);

			startActivity(intent);

			Sensodyne_Offer.this.finish();
			break;
		}
	}
}