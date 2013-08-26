package com.shoter.aat;

import com.shoter.aat.log.Logger;


public class Game
{
	static Game instance;
	
	public static void Create()
	{
		if(instance == null)
			new Game();
		
		Logger.d("GAME", "Initialized", true);
	}

}
