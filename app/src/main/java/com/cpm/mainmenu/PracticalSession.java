package com.cpm.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cpm.gsk.R;
import com.cpm.use_toothpaste.NaturalWhiteness;
import com.cpm.use_toothpaste.NextCallActivity;
import com.cpm.use_toothpaste.ShortSharpSensation;

public class PracticalSession extends Activity implements OnClickListener {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.practical_session);

		Button exit = (Button) findViewById(R.id.practicealert_exitsessionbtn);
		Button becontinue = (Button) findViewById(R.id.practicealert_continuebtn);

		exit.setOnClickListener(this);
		becontinue.setOnClickListener(this);

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				PracticalSession.this.finish();
			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				PracticalSession.this.finish();
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.practicealert_exitsessionbtn) {
			Intent intent = new Intent(this, MainMenuScreenActivity.class);
			startActivity(intent);
			PracticalSession.this.finish();
		}

		if (v.getId() == R.id.practicealert_continuebtn) {
			Intent intent = new Intent(this, ShortSharpSensation.class);
			startActivity(intent);
			PracticalSession.this.finish();
		}

	}

	public void onBackPressed() {

		return;
	}
}
