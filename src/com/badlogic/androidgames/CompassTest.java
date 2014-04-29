package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class CompassTest extends Activity implements SensorEventListener {
	TextView textView;
	StringBuilder builder = new StringBuilder();
	int screenRotation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		setContentView(textView);
		SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (manager.getSensorList(Sensor.TYPE_ORIENTATION).size() == 0) {
			textView.setText("No accelerometer installed");
		} else {
			Sensor compass = manager.getSensorList(
					Sensor.TYPE_ORIENTATION).get(0);
			if (!manager.registerListener(this, compass, SensorManager.SENSOR_DELAY_GAME)) {
				textView.setText("Couldn't register sensor listener");
			}	
		}
	}
	
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("x: ");
		builder.append(event.values[0]);
		builder.append(", y: ");
		builder.append(event.values[1]);
		builder.append(", z: ");
		builder.append(event.values[2]);
		textView.setText(builder.toString());
		}
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nothing to do here
		}
	}