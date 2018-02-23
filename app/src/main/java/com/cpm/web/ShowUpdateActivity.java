package com.cpm.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import com.cpm.gsk.GSKOralCareActivity;
import com.cpm.gsk.PerformanceDetailAuthentication;
import com.cpm.gsk.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;



public class ShowUpdateActivity extends Activity {

	WebView mWebView;

	Button mcontinuebtn;
	String status, service_status;
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		mcontinuebtn = (Button) findViewById(R.id.header);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.clearCache(true);
		mWebView.loadUrl("http://parinaam.in/gskmerc/notice/index.html");

		mWebView.setWebViewClient(new HelloWebViewClient());

		mcontinuebtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(ShowUpdateActivity.this, PerformanceDetailAuthentication.class);
				startActivity(i);
				ShowUpdateActivity.this.finish();

			}
		});

	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(ShowUpdateActivity.this, GSKOralCareActivity.class);
		startActivity(i);
		ShowUpdateActivity.this.finish();

	}

}
