package com.shoter.aat;

import java.util.ArrayList;
import java.util.List;

import com.shoter.game.Apple;
import com.shoter.logger.Logger;
import com.shoter.state.StateMachine;

public class Game
{
	static Game instance;
	public StateMachine stateMachine;
	public List<Apple> appleList = new ArrayList<Apple>();
	
	public static void create()
	{
		if(instance == null)
			instance = new Game();
		
		Logger.d("GAME", "Initialized", true);
	}
	
	public static Game get()
	{
		return instance;
	}

}
