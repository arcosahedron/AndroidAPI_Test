package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity {

	StringBuilder builder = new StringBuilder(); //Will hold all messages produced
	TextView textView; //Used to display those text messages directly in Activity
	
	//This method will log a String to logcat
	private void log(String text){
		Log.d("LifeCycleTest", text); //Send to logcat
		builder.append(text);
		builder.append('\n');
		textView.setText(builder.toString());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView = new TextView(this); //this - will fill complete space of activity
		textView.setText(builder.toString());
		setContentView(textView);
		log("created");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		log("resumed");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		log("paused");
		
		if (isFinishing()){
			log("finishing"); //output to log so that we can see app finishing
		}
	}
}
