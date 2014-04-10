package com.nabillo.baby_care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.VideoView;

public class CameraActivity extends Activity {

	private SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Camera_View);
		
		final VideoView cameraView = (VideoView) findViewById(R.id.cameraView);
		videoView.setVideoPath(sharedPref.getString(SettingsActivity.KEY_PREF_CAM_URL, ""));
		cameraView.start();
	}


}
