package com.nabillo.baby_care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/** Launch camera activity */
	public void camera_launch(View view) {
		startActivity(new Intent(this, CameraActivity.class));
		overridePendingTransition(R.anim.slide_in_up.xml, android.R.anim.fade_out.xml );
	}



}
