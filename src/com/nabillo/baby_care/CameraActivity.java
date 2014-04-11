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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Camera_View);
		
		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		
		// Implemente camera view with url of motion webserver
		final VideoView cameraView = (VideoView) findViewById(R.id.cameraView);
		videoView.setVideoPath(sharedPref.getString(SettingsActivity.KEY_PREF_CAM_URL, ""));
		cameraView.start();
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
			// Start voice chat if scroll speed was high enough
			if (velocityY > 10)
				
			return true;
		}
	}
}
