package com.shoter.aat;

import com.shoter.logger.Logger;

public class Game
{
	static Game instance;
	
	public static void create()
	{
		if(instance == null)
			new Game();
		
		Logger.d("GAME", "Initialized", true);
	}

}
