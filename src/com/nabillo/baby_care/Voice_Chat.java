package com.nabillo.baby_care;

import android.media.MediaRecorder;

public class Voice_Chat
{
	private SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	private MediaRecorder mRecorder = null;
	private Socket voice_soc = null;
	privaet ParcelFileDescriptor voice_pfd = null;
	
	public Voice_Chat()
	{
	
		// TODO : Send request to raspi to add playlist on this address
		
		// Define descriptor to mdp server
		String voice_url = sharedPref.getString(SettingsActivity.KEY_PREF_VOICE_IP, "")
		String voice_port = sharedPref.getString(SettingsActivity.KEY_PREF_VOICE_PORT, "")
		voice_soc=new Socket(voice_url,voice_port);
		voice_pfd = ParcelFileDescriptor.fromSocket(voice_soc);
		
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(voice_pfd);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mRecorder.prepare();
	}
	
	public void voice_Start()
	{
		// Start recording and streaming voice
		mRecorder.start();
		// Start mdp playing stream from android
		// TODO : Send request to raspi to play playlist
		
	}
	
	public void voice_Stop()
	{
		// TODO : Send request to raspi to stop playing and empty playlist
		
		// Stop recording and streaming voice
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}
}
