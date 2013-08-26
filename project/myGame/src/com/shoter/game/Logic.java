package com.shoter.game;

import com.shoter.logger.Logger;

public class Logic
{
	static Logic instance;
	
	public static void create()
	{
		if(get() == null)
			new Logic();
		else
			Logger.w("LOGIC", "Trying to create new Logic when other logic exist", true);
	}
	
	Logic()
	{
		
	}
	
	public static Logic get()
	{
		return instance;
	}

}
