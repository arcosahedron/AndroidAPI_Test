package com.badlogic.androidgames;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class AssetsTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try{
			inputStream = assetManager.open("texts/myawesometext.txt");
			String text = loadTextFile(inputStream);
			textView.setText(text);
		} catch (IOException e){
			textView.setText("Couldn't load file.");
		} finally {
			if (inputStream != null)
				try{
					inputStream.close();
				} catch (IOException e){
					textView.setText("Couldn't close file");
				}
		}
	}
	
	public String loadTextFile (InputStream inputStream) throws IOException {
		return IOUtils.toString(inputStream); //This version is much easier
	}

}
