package com.cpm.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpm.gsk.R;
import com.cpm.use_toothpaste.DrRecommendedActivity;
import com.cpm.use_toothpaste.OthersRNP;
import com.cpm.use_toothpaste.RapidRelief;
import com.cpm.use_toothpaste.ToothbrushSensitive;
import com.cpm.use_toothpaste.WhichBrand;

public class Startsales extends Activity implements OnClickListener {

	Button yes,home;
	Button no;
	TextView msg;
	
	Button btnrnp,btnbase,btnrr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startsale1);

		yes = (Button) findViewById(R.id.yes);
		no = (Button) findViewById(R.id.no);
		home = (Button) findViewById(R.id.home);
		//msg = (TextView) findViewById(R.id.message);

		//msg.setText("Do you feel a short sharp pain when you drink or eat something Hot or Cold ?");
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		
		btnrnp=(Button) findViewById(R.id.btnrnp);
		btnbase=(Button) findViewById(R.id.btnbase);
		btnrr=(Button) findViewById(R.id.btnrr);
		btnrnp.setOnClickListener(this);
		btnbase.setOnClickListener(this);
		btnrr.setOnClickListener(this);

		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Startsales.this.finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Startsales.this.finish();

			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.yes) {
			//Intent intent = new Intent(this, Pain_yes.class);
			Intent intent = new Intent(this, WhichBrand.class);
			startActivity(intent);
			Startsales.this.finish();

		}

		if (v.getId() == R.id.no) {
		
			Intent intent = new Intent(getApplicationContext(),OthersRNP.class);
			startActivity(intent);
			finish();
			
		}
		
		if (v.getId() == R.id.btnrnp) {

			//Intent intent = new Intent(this, Pain_no.class);
		
			Intent intent = new Intent(getApplicationContext(),ToothbrushSensitive.class);
			startActivity(intent);
			finish();
			
		}
		
		if (v.getId() == R.id.btnbase) {

			//Intent intent = new Intent(this, Pain_no.class);
		
			Intent intent = new Intent(getApplicationContext(),DrRecommendedActivity.class);
			startActivity(intent);
			finish();
			
		}
		
		if (v.getId() == R.id.btnrr) {

			//Intent intent = new Intent(this, Pain_no.class);
		
			Intent intent = new Intent(getApplicationContext(),RapidRelief.class);
			startActivity(intent);
			finish();
			
		}
	}

	public void onBackPressed() {

		return;
	}
}
