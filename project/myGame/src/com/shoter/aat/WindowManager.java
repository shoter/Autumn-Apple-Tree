package com.shoter.aat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.logger.Logger;

public class WindowManager
{
	public static Window currentWindow = null;
	static Window nextWindow = null;
	public static boolean end = false;
	
	public static void prepareToChangeWindow(Window nextWindow)
	{
		WindowManager.nextWindow = nextWindow;
		Logger.d("WindowManager", "Preparing to change window to " + nextWindow.toString());
	}
	
	static void changeWindow()
	{
		if(currentWindow != null)
			currentWindow.onDestroy();
		currentWindow = nextWindow;
		currentWindow.onCreate();
		
		Logger.d("WindowManager", "Changed window to " + currentWindow.toString());
		
		nextWindow = null;
	}
	
	public static void tick()
	{
		if(nextWindow != null)
			changeWindow();
		
		if(currentWindow != null)
			currentWindow.tick();
	}
	
	public static void draw(SpriteBatch spriteBatch)
	{
		if(currentWindow != null)
			currentWindow.draw(spriteBatch);
	}
	
	
}
