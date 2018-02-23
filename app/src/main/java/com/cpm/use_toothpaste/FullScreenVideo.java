package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.cpm.gsk.R;

public class FullScreenVideo extends Activity
{
	MediaController mc;
	VideoView video;
	int num ;
	String path;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		video = (VideoView) findViewById(R.id.whyusesensodyne_questionico);
		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		String classname = intent.getStringExtra("class");
			
		if(classname.equalsIgnoreCase("repair"))
		{
			num =1;
		}
		if(classname.equalsIgnoreCase("Paradontax"))
		{
			num =2;
		}
		if(classname.equalsIgnoreCase("oralcare"))
		{
			num =3;
		}
		if(classname.equalsIgnoreCase("newscreen"))
		{
			num =4;
		}
		if(classname.equalsIgnoreCase("rapidrelief"))
		{
			num =5;
		}
		
	
		/*video.setVideoPath(path);	
		mc = new MediaController(this);
		mc.setAnchorView(video);
		video.setMediaController(mc);
		video.start();*/
		
		ImageView back = (ImageView) findViewById(R.id.imageView2);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(num == 1)
				{
					video.stopPlayback();
					Intent i = new Intent(getApplicationContext(),
							Sensodyne_repair_protectActivity.class);
					startActivity(i);
					FullScreenVideo.this.finish();
				}
				if(num == 2)
				{
					video.stopPlayback();
					Intent i = new Intent(getApplicationContext(),
							WhyUseParandontax_Activity.class);
					startActivity(i);
					FullScreenVideo.this.finish();
				}
				if(num == 3)
				{
					video.stopPlayback();
					Intent i = new Intent(getApplicationContext(),
							OralCare_Activity.class);
					startActivity(i);
					FullScreenVideo.this.finish();
				}
				if(num == 4)
				{
					video.stopPlayback();
					Intent i = new Intent(getApplicationContext(),
							NewSreen_Activity.class);
					startActivity(i);
					FullScreenVideo.this.finish();
				}
				if(num == 5)
				{
					video.stopPlayback();
					Intent i = new Intent(getApplicationContext(),
							RapidRelief_Activity.class);
					startActivity(i);
					FullScreenVideo.this.finish();
				}
				

			}
		});
		
}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
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
		video.setVideoPath(path);	
		mc = new MediaController(this);
		mc.setAnchorView(video);
		video.setMediaController(mc);
		video.start();
	}
	
}
