package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cpm.gsk.R;
import com.cpm.mainmenu.MainMenuScreenActivity;

public class RapidRelief extends Activity implements OnClickListener{
	
	Button btnnext;
	Button btnrnp,btnbase,btnrr,tb,dc,whit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.rapidrelief_layout);
		
		//btnOther=(Button) findViewById(R.id.other);
		btnnext=(Button) findViewById(R.id.rapidreliefnext);
		
		//btnOther.setOnClickListener(this);
		btnnext.setOnClickListener(this);
		
		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				finish();

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				finish();

			}
		});
		
		btnrnp=(Button) findViewById(R.id.btnrnp);
		btnbase=(Button) findViewById(R.id.btnbase);
		btnrr=(Button) findViewById(R.id.btnrr);
		tb=(Button) findViewById(R.id.tb);
		dc=(Button) findViewById(R.id.dc);
		whit=(Button) findViewById(R.id.whit);
		btnrnp.setOnClickListener(this);
		btnbase.setOnClickListener(this);
		btnrr.setOnClickListener(this);
		dc.setOnClickListener(this);
		tb.setOnClickListener(this);
		whit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id=v.getId();
		
		switch (id) {
		/*case R.id.other:
			
			Intent intent = new Intent(getApplicationContext(),Sales_Record.class);
			startActivity(intent);
			finish();
			
			break;*/

		case R.id.rapidreliefnext:
			
			Intent in = new Intent(getApplicationContext(),InstantRelief.class);
			startActivity(in);
			finish();
			
			break;
			
		case  R.id.btnrnp:

			//Intent intent = new Intent(this, Pain_no.class);
		
			//Intent in3 = new Intent(getApplicationContext(),ToothbrushSensitive.class);
			Intent in3 = new Intent(getApplicationContext(),OthersRNP.class);
			startActivity(in3);
			finish();
			
		break;
		
	case R.id.btnbase:

			//Intent intent = new Intent(this, Pain_no.class);
		
			Intent in1 = new Intent(getApplicationContext(),DrRecommendedActivity.class);
			startActivity(in1);
			finish();
			
		break;
		
	case R.id.btnrr:

			//Intent intent = new Intent(this, Pain_no.class);
		
			Intent in2 = new Intent(getApplicationContext(),RapidRelief.class);
			startActivity(in2);
			finish();
			
			break;
			case R.id.tb:
				Intent tb = new Intent(getApplicationContext(),ToothbrushSensitive.class);
				startActivity(tb);
				finish();

				break;
			case R.id.dc:
				Intent dc = new Intent(getApplicationContext(),SensodyneWhitingVideo.class);
				startActivity(dc);
				finish();

				break;
			case R.id.whit:
				Intent whit = new Intent(getApplicationContext(),NaturalWhiteness.class);
				startActivity(whit);
				finish();

				break;
		}
		
	}
	
	public void onBackPressed() {

		return;
	}


}
