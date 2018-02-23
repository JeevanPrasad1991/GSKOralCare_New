package com.cpm.use_toothpaste;

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

import com.cpm.gsk.R;

public class VideoRapidActivity extends Activity implements OnClickListener{
	
	Button btnnext;
	VideoView video;
	MediaController mc;
	String SrcPath;
	Button btnrnp,btnbase,btnrr,tb,dc,whit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.videorapidlayout);
		
		video = (VideoView) findViewById(R.id.videoviewsrnp);
		SrcPath = Environment.getExternalStorageDirectory().getPath()+"/GSKOralCare/Rapidrelief.mp4";
		//btnOther=(Button) findViewById(R.id.other);
		btnnext=(Button) findViewById(R.id.nextvideorapid);
		
		//btnOther.setOnClickListener(this);
		btnnext.setOnClickListener(this);
		
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
		
		
		/*ImageView back = (ImageView) findViewById(R.id.backto);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						DrActivity.class);
				startActivity(i);
				finish();

			}
		});*/
		
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
		/*case R.id.other:
			
			Intent intent = new Intent(getApplicationContext(),Sales_Record.class);
			startActivity(intent);
			finish();
			
			break;*/

		case R.id.nextvideorapid:
			
			//Intent in = new Intent(getApplicationContext(),ToothbrushSensitive.class); DrRecommendedActivity
			/*Intent in = new Intent(getApplicationContext(),DoCauseActivity.class);*/
			Intent in = new Intent(getApplicationContext(),DrRecommendedActivity.class);
			startActivity(in);
			finish();
			
			break;
			
		case  R.id.btnrnp:

				//Intent intent = new Intent(this, Pain_no.class);
			
				Intent intent = new Intent(getApplicationContext(),OthersRNP.class);
				startActivity(intent);
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
		
		//video.seekTo(2000);
	}

}
