package com.cpm.use_toothpaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.cpm.database.GskDatabase;
import com.cpm.gsk.R;
import com.cpm.mainmenu.Startsales;

public class VideoNew extends Activity implements OnClickListener{

	Button btnnext,btnbuy;
	VideoView video;
	MediaController mc;
	String SrcPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.video_new_layout);

		video = (VideoView) findViewById(R.id.videoviewsrnp);
		SrcPath = Environment.getExternalStorageDirectory().getPath()+"/GSKOralCare/SensodyneExpertToothbrushVideo.mp4";
		//btnOther=(Button) findViewById(R.id.other);
		btnnext=(Button) findViewById(R.id.nextvideorapid);

		//btnOther.setOnClickListener(this);
		btnnext.setOnClickListener(this);

		btnbuy=(Button) findViewById(R.id.buythis);

		btnbuy.setOnClickListener(this);


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
		
		case R.id.nextvideorapid:

			Intent in = new Intent(getApplicationContext(),ToothBrush_Activity.class);
			startActivity(in);
			finish();

			break;

		case  R.id.buythis:

			GskDatabase  mdatabaseObj = new GskDatabase(getApplicationContext());
			mdatabaseObj.openDB();
			String sensodyne_id = mdatabaseObj.getBrandId("Sensodyne");
			Editor e2 = this.getSharedPreferences("brand_count",
					Context.MODE_WORLD_READABLE).edit();

			e2.putString("brand", "Sensodyne");
			e2.putString("brand_id", sensodyne_id);
			e2.putString("count_status", "Yes");
			e2.commit();

			Intent intent = new Intent(getApplicationContext(),Sales_Record.class);
			startActivity(intent);
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
	
	public void onBackPressed() {

		return;
	}

}

