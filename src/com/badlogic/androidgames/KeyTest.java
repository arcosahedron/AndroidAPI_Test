package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

//Implement OnKeyListener
public class KeyTest extends Activity implements OnKeyListener {
	StringBuilder builder = new StringBuilder();
	TextView textView;
	
	public void OnCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Press keys (if you have some)!");
		textView.setOnKeyListener(this);
		textView.setFocusableInTouchMode(true); // Force the view to be focused
		textView.requestFocus(); //request focus of the view
		setContentView(textView);
	}
	
	public boolean onKey(View view, int keyCode, KeyEvent event){
		builder.setLength(0);
		switch (event.getAction()){
		case KeyEvent.ACTION_DOWN:
			builder.append("down, ");
			break;
		case KeyEvent.ACTION_UP:
			builder.append("up, ");
			break;
		}
		builder.append(event.getKeyCode());
		builder.append(", ");
		builder.append((char) event.getUnicodeChar());
		String text = builder.toString();
		Log.d("KeyTest", text);
		textView.setText(text);
		
		return event.getKeyCode() != KeyEvent.KEYCODE_BACK;
	}
}
