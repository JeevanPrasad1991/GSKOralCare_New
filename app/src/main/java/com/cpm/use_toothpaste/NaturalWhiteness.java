package com.cpm.use_toothpaste;

import com.cpm.gsk.R;
import com.cpm.mainmenu.Startsales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class NaturalWhiteness extends Activity implements OnClickListener{

	Button btnnext;
	VideoView video;
	MediaController mc;
	String SrcPath;
	Button btnrnp,btnbase,btnrr, btnskip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.natural_white_layout);

		video = (VideoView) findViewById(R.id.videoviewsrnp);
		SrcPath = Environment.getExternalStorageDirectory().getPath()+"/GSKOralCare/SensodyneWhiteningPromo.mp4";
		btnnext=(Button) findViewById(R.id.btnnext);
		btnskip = (Button) findViewById(R.id.btnskip);
		btnnext.setOnClickListener(this);
		
		btnrnp=(Button) findViewById(R.id.btnrnp);
		btnbase=(Button) findViewById(R.id.btnbase);
		btnrr=(Button) findViewById(R.id.btnrr);
		btnrnp.setOnClickListener(this);
		btnbase.setOnClickListener(this);
		btnrr.setOnClickListener(this);
		btnskip.setOnClickListener(this);
		
		video.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				video.seekTo(0);
				video.start();
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id=v.getId();
		
		switch (id) {

		case R.id.btnnext:
			
			//Intent in = new Intent(getApplicationContext(),LargestSellingActivity.class);
			Intent in = new Intent(getApplicationContext(),LargestSellingActivity.class);
			startActivity(in);
			finish();
			
			break;
			
			
		case R.id.btnskip:
			
			Intent inn = new Intent(getApplicationContext(), Startsales.class);
			startActivity(inn);
			finish();

		}
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		video.stopPlayback();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		video.setVideoPath(SrcPath);
		mc = new MediaController(this);
		mc.setAnchorView(video);
		video.setMediaController(mc);
		video.requestFocus();
		video.seekTo(0);
		video.start();
	}

}
