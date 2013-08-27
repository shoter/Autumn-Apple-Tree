package com.shoter.aat;

import com.shoter.logger.Logger;

public class WindowManager
{
	GameWindow currentWindow = null;
	GameWindow nextWindow = null;
	
	public void prepareToChangeWindow(GameWindow nextWindow)
	{
		this.nextWindow = nextWindow;
		Logger.d("WindowManager", "Preparing to change window to " + nextWindow.toString());
	}
	
	void changeWindow()
	{
		if(currentWindow != null)
			currentWindow.onDestroy();
		currentWindow = nextWindow;
		currentWindow.onCreate();
		
		Logger.d("WindowManager", "Changed window to " + currentWindow.toString());
		
		nextWindow = null;
	}
	
	public void tick()
	{
		if(nextWindow != null)
			changeWindow();
	}
	
	
}
