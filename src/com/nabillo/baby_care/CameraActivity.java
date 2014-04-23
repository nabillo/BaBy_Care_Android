package com.nabillo.baby_care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.VideoView;

public class CameraActivity extends Activity {

	private SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	private GestureDetectorCompat mDetector;
	final private VideoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Camera_View);
		
		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		
		cameraView = (VideoView) findViewById(R.id.cameraView);
		// Implemente camera view with url of motion webserver
		cameraView.setVideoPath(sharedPref.getString(SettingsActivity.KEY_PREF_CAM_URL, ""));
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		cameraView.start();
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		cameraView.stopPlayback();
	}

	@Override 
	public boolean onTouchEvent(MotionEvent event){ 
		this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		
		@Override
		public boolean onDown(MotionEvent event) {
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, 
				float velocityX, float velocityY) {
			// Close camera view if scroll speed was high enough
			if (velocityY > 10)
				finish();
			return true;
		}
		
		@Override
		public boolean onLongPress(MotionEvent event) {
			// Launch voice chat
			startActivity(new Intent(this, VoiceActivity.class));
			return true;
		}
	}
}
