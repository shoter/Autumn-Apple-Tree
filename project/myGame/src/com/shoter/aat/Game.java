package com.shoter.aat;

import com.shoter.logger.Logger;
import com.shoter.state.GameState;
import com.shoter.state.StateMachine;

public class Game
{
	static Game instance;
	public StateMachine stateMachine;
	
	public static void create()
	{
		if(instance == null)
			new Game();
		
		StateMachine.changeState(new GameState());
		
		Logger.d("GAME", "Initialized", true);
	}
	
	public Game get()
	{
		return instance;
	}

}
