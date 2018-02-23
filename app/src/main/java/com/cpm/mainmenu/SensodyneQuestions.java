package com.cpm.mainmenu;


import com.cpm.gsk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SensodyneQuestions extends Activity implements OnClickListener {
	
	Button score;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sensodyne_question);
		score = (Button)findViewById(R.id.score_btn);
		
		score.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.score_btn) {
			
			Intent in = new Intent(SensodyneQuestions.this, Score.class);
			startActivity(in);
			
		}
		
	}

}
