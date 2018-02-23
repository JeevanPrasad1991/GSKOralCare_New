package com.cpm.mainmenu;

import com.cpm.gsk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Score extends Activity implements OnClickListener{
	
	Button return_btn;
	
	public void onCreate(Bundle svaedInstanceState){
		super.onCreate(svaedInstanceState);
		setContentView(R.layout.score);
		return_btn = (Button)findViewById(R.id.return_btn);
		
		return_btn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.return_btn) {
			
			Intent in = new Intent(Score.this, MainMenuScreenActivity.class);
			startActivity(in);
			
		}
		
	}

}
