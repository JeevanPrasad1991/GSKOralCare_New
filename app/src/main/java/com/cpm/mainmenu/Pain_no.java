package com.cpm.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpm.gsk.R;
import com.cpm.use_toothpaste.NewSreen_Activity;
import com.cpm.use_toothpaste.OralCare_Activity;
import com.cpm.use_toothpaste.WhyUseParandontax_Activity;

public class Pain_no extends Activity implements OnClickListener {

	Button yes,home;
	Button no;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startsale2);

		TextView msg = (TextView) findViewById(R.id.message);
		yes = (Button) findViewById(R.id.yes);
		no = (Button) findViewById(R.id.no);
		home= (Button) findViewById(R.id.home);

		msg.setText("Do you spit blood when you brush your teeth ?");

		ImageView img = (ImageView) findViewById(R.id.imageView2);
		img.setImageResource(R.drawable.whh);

		yes.setOnClickListener(this);
		no.setOnClickListener(this);

		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Pain_no.this.finish();
				

			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				Pain_no.this.finish();


			}
		});

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.yes) {
			{
				Intent intent = new Intent(this,
						WhyUseParandontax_Activity.class);
				startActivity(intent);
				Pain_no.this.finish();
			}

		}

		if (v.getId() == R.id.no) {

			Intent intent = new Intent(this, NewSreen_Activity.class);
			startActivity(intent);
			Pain_no.this.finish();

		}

	}

	public void onBackPressed() {

		return;
	}
}
