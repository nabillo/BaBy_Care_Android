package com.nabillo.baby_care;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.nabillo.baby_care.Voice_Chat;

public class VoiceActivity extends Activity {

	private SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	private voice_Chat = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.Voice_View);
		voice_Chat = new Voice_Chat();
		voice_Chat.voice_Start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		voice_Chat.voice_Stop();
	}
	
	
}
