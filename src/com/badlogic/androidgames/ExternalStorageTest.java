package com.badlogic.androidgames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class ExternalStorageTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		
		String state = Environment.getExternalStorageState(); // get external storage state
		//CHeck if external storage is accessible
		if (!state.equals(Environment.MEDIA_MOUNTED)){
			textView.setText("No External storage mounted");
		} else{
			File externalDir = Environment.getExternalStorageDirectory(); // 	Create a variable to name the storage
			File textFile = new File(externalDir.getAbsolutePath()+File.separator+"text.txt"); // Create new file place in proper directory
			
			//The try statement encloses code that will may throw an error.
			//It provides a means for the application to simply not stop and keep going
			try{
				writeTextFile(textFile, "If you can this. The program has sucessfully found external media mounted and has created this file.");
				String text = readTextFile(textFile);
				textView.setText(text);
				if (!textFile.delete()){
					textView.setText("Couldn't remove temporary file");
					}
				} catch (IOException e){ // The exception could happen here
					textView.setText("Something went wrong!" + e.getMessage());
				}
			}
		}
		
		private void writeTextFile(File file, String text) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.close();
		}
		
		private String readTextFile(File file) throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder text = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null){
				text.append(line);
				text.append("\n");
			}
			
			reader.close();	
			return text.toString();
			
		}
		
	}

