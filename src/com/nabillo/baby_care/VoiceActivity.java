package com.nabillo.baby_care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class VoiceActivity extends Activity {

	private SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Voice_View);


	}


}
