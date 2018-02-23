package com.cpm.use_toothpaste;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cpm.database.GskDatabase;
import com.cpm.delegates.languageBean;
import com.cpm.gsk.R;
import com.cpm.mainmenu.MainMenuScreenActivity;
import com.cpm.mainmenu.Sensodyne_Offer;
import com.cpm.mainmenu.Startsales;

//Implement this interface to be notified when a gesture occurs
public class NewSreen_Activity extends Activity implements OnGestureListener,
		OnClickListener {
	// creates a Gesture Detector
	private GestureDetector gd;
	VideoView video;
	SharedPreferences app_preferences;
	SharedPreferences.Editor editor;
	GskDatabase mdatabaseObj;
	static Editor e11, e2;
	MediaController mc;
	String SrcPath;
	Spinner language_spinner;
	String selected_languageid;
	String selected_language= "ENGLISH";

	public boolean selection = false;
	public int selection_id;
	int previous_language_id = 1;
	String previous_language_name = "abc";
	
	public static ArrayList<languageBean> languagearray = new ArrayList<languageBean>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newscreen);
		
		video = (VideoView) findViewById(R.id.msggrdtfficosskujn);
		SrcPath = "/sdcard/Download/NewScreen.wmv";
		
		selected_language = this.getSharedPreferences("user_detail",
				Context.MODE_WORLD_WRITEABLE).getString("language", "");
	   
		
		 language_spinner = (Spinner) findViewById(R.id.language_namegh);
		 mdatabaseObj = new GskDatabase(getApplicationContext());
		 mdatabaseObj.openDB();

		
		 languagearray.clear();
		languagearray = mdatabaseObj.getLanguageData();
		
		
		
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		
		//adapter.add("Select language");
		
		for (int i = 0; i < languagearray.size(); i++) 
		{
			adapter.add(languagearray.get(i).getLanguage());
			
			if(languagearray.get(i).getLanguage().equalsIgnoreCase(selected_language))
			{
				//language_spinner.setSelection(i + 1 )	;
				//selection = i + 1;
				

				language_spinner.setSelection(i);
				selection_id=i;
				selection = true;
			}
		}		 
		language_spinner.setAdapter(adapter);		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		language_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int item = language_spinner.getSelectedItemPosition();
				if (selection) {
					language_spinner.setSelection(selection_id);
					item = language_spinner.getSelectedItemPosition();						
					selection = false;
				}
				//if(item != 0)
				//{
				selected_languageid = String.valueOf(item);				
				selected_language = languagearray.get(item).getLanguage();
				
			
			 if(selected_language.equalsIgnoreCase("English"))
				{
				 SrcPath = "/sdcard/Download/NewScreen.wmv";
					}
				else
				{
					SrcPath = "/sdcard/Download/NewScreen/" + selected_language + ".mp4";
					}
				//}
			 if (!selected_language
						.equalsIgnoreCase(previous_language_name))
			 {
				 
				/* if (new File("/mnt/sdcard/Download/NewScreen/"
						 + selected_language + ".mp4")
							.exists())*/
					 if (new File("/mnt" + SrcPath)
						.exists())
				 {
			video.stopPlayback();
			video.setVideoPath(SrcPath);
			video.start();	
			previous_language_id = language_spinner
					.getSelectedItemPosition();
			previous_language_name = languagearray
					.get(item).getLanguage();
				 }
				 else
				 {
					
					 Toast.makeText(getBaseContext(), "Video not available",Toast.LENGTH_LONG).show();
					 language_spinner
						.setSelection(previous_language_id);
					
				 }
				
			}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
			

		Button buy = (Button) findViewById(R.id.buy);
		Button notnow = (Button) findViewById(R.id.notnow);

		
		buy.setOnClickListener(this);
		notnow.setOnClickListener(this);

		mdatabaseObj = new GskDatabase(getApplicationContext());
		mdatabaseObj.openDB();

		String sensodyne_id = mdatabaseObj.getBrandId("Sensodyne");
		e2 = this.getSharedPreferences("brand_count",
				Context.MODE_WORLD_READABLE).edit();

		e2.putString("brand", "Sensodyne");
		e2.putString("brand_id", sensodyne_id);
		e2.putString("count_status", "Yes");
		e2.commit();

	

		video.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				video.seekTo(0);
				video.start();
				// TODO Auto-generated method stub
				return false;
			}
		});

		gd = new GestureDetector(this);

		// set the on Double tap listener
		gd.setOnDoubleTapListener(new OnDoubleTapListener() {
			@Override
			public boolean onDoubleTap(MotionEvent e) {

				Intent i = new Intent(getApplicationContext(),
						FullScreenVideo.class);
				i.putExtra("path", SrcPath);
				i.putExtra("class", "newscreen");
				startActivity(i);
				NewSreen_Activity.this.finish();
				return false;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				// if the second tap hadn't been released and it's being moved
				if (e.getAction() == MotionEvent.ACTION_MOVE) {

				} else if (e.getAction() == MotionEvent.ACTION_UP)

				{

				}
				return false;
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {

				return false;
			}
		});

		ImageView back = (ImageView) findViewById(R.id.back);
		Button home=(Button)findViewById(R.id.home);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				NewSreen_Activity.this.finish();
			}
		});
		home.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MainMenuScreenActivity.class);
				startActivity(i);
				NewSreen_Activity.this.finish();
			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gd.onTouchEvent(event);// return the double tap events
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.buy) {

			Intent intent = new Intent(this, Sales_Record.class);
			startActivity(intent);
			this.finish();
		}
		if (v.getId() == R.id.notnow) {

			Intent intent = new Intent(this, Startsales.class);
			startActivity(intent);
			this.finish();
		}

	}

	public void onBackPressed() {

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
		video.seekTo(2000);
	}
}