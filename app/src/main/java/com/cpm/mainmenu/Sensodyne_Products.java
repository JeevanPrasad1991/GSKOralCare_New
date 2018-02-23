package com.cpm.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cpm.gsk.R;
import com.cpm.use_toothpaste.Parandontax_Offer_Activity;
import com.cpm.use_toothpaste.Rapid_Relief_Rapir_Activity;
import com.cpm.use_toothpaste.Sensodyne_Rapid_Relief_Offer_Activity;
import com.cpm.use_toothpaste.Sensodyne_repair_protectActivity;

public class Sensodyne_Products extends Activity implements OnClickListener {
	String type;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensodyne_products);

		Intent intent = getIntent();
		type = intent.getStringExtra("type");

		if (type.equals("sensodyne")) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.question2_btn);
			RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.question3_btn);
			RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.question4_btn);

			layout.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.GONE);
		} else if (type.equals("rapidrelief")) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.question1_btn);
			RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.question3_btn);
			RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.question4_btn);
			;
			layout.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.GONE);
		} else if (type.equals("repair")) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.question1_btn);
			RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.question2_btn);
			RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.question3_btn);

			layout.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.GONE);
		} else if (type.equals("paradontax")) {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.question1_btn);
			RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.question2_btn);
			RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.question4_btn);

			layout.setVisibility(View.GONE);
			layout1.setVisibility(View.GONE);
			layout2.setVisibility(View.GONE);
		}

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sensodyne_Products.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Sensodyne_Products.this.finish();

			}
		});

		Button gms1 = (Button) findViewById(R.id.gms1);
		Button gms2 = (Button) findViewById(R.id.gms2);
		Button gms3 = (Button) findViewById(R.id.gms3);
		Button gms1a = (Button) findViewById(R.id.gms1a);
		Button gms2a = (Button) findViewById(R.id.gms2a);
		Button gms1b = (Button) findViewById(R.id.gms1b);
		Button gms2b = (Button) findViewById(R.id.gms2b);
		Button gms3b = (Button) findViewById(R.id.gms3b);

		Button gms1c = (Button) findViewById(R.id.gms1c);
		Button gms2c = (Button) findViewById(R.id.gms2c);

		gms1.setOnClickListener(this);
		gms2.setOnClickListener(this);
		gms3.setOnClickListener(this);
		gms1a.setOnClickListener(this);
		gms2a.setOnClickListener(this);
		gms1b.setOnClickListener(this);
		gms2b.setOnClickListener(this);
		gms3b.setOnClickListener(this);
		gms1c.setOnClickListener(this);
		gms2c.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.gms1) {
			Intent intent = new Intent(this, Sensodyne_Offer.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms2) {
			Intent intent = new Intent(this, Sensodyne_Offer.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms3) {
			Intent intent = new Intent(this, Sensodyne_Offer.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms1a) {
			Intent intent = new Intent(this,
					Sensodyne_Rapid_Relief_Offer_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms2a) {
			Intent intent = new Intent(this,
					Sensodyne_Rapid_Relief_Offer_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms1b) {
			Intent intent = new Intent(this, Parandontax_Offer_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms2b) {
			Intent intent = new Intent(this, Parandontax_Offer_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms3b) {
			Intent intent = new Intent(this, Parandontax_Offer_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms1c) {
			Intent intent = new Intent(this, Rapid_Relief_Rapir_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
		if (v.getId() == R.id.gms2c) {
			Intent intent = new Intent(this, Rapid_Relief_Rapir_Activity.class);
			startActivity(intent);
			Sensodyne_Products.this.finish();
		}
	}

	public void onBackPressed() {

		return;
	}
}
