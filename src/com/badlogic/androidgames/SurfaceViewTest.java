package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
	FastRenderView renderView; // Instantiate a FastRenderView
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); //Inheritance
		requestWindowFeature(Window.FEATURE_NO_TITLE); //FullScreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//FullScreen
		renderView = new FastRenderView(this);
		
	}
	
	protected void onResume(){
		super.onResume();
		renderView.resume(); //Begin Rendering
	}

	protected void onPause(){
		super.onPause();
		renderView.pause(); //Pause when onPause is called
	}
	
	class FastRenderView extends SurfaceView implements Runnable{
		Thread renderThread = null; //Reference ro thread instance that will be responsible for executing our rendering thread logic
		SurfaceHolder holder; //reference to surfaceholder instance
		
		//Volatile means...
		volatile boolean running = false; //Flag to signal when to stop rendering
	
		public FastRenderView(Context context){
			super(context); //call superclass constructor
			holder = getHolder(); //store surfaceholder reference in the holder member
		}
		
		//Responsible for starting up the rendering thread
		public void resume(){
			running = true; //Change flag
			renderThread = new Thread(this); //create a new thread every time this method is called
			renderThread.start();
		}
		
		public void run(){
			//Loop will stop when running is set ot false
			while(running){
				//If surface is valid...it is locked then rendered, then unlocked
				if (!holder.getSurface().isValid())
					continue;
				Canvas canvas = holder.lockCanvas();
				
				canvas.drawRGB(255, 0, 0);
				holder.unlockCanvasAndPost(canvas);
			}
		}
		
		public void pause(){
			running = false; //will terminate run function above
			
			//This while loop run until the thread dies
			//The thread.join wait for thread to die 
			//but may throw in an exception, hence the need for a try and the while loop
			while(true){
				try{
					renderThread.join();
					return;
				} catch (InterruptedException e){
					//retry
				}
			}
		}
	}
}
