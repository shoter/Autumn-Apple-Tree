package com.shoter.state;

public class StateMachine
{
	static State currentState;
	
	public static void changeState(State newState)
	{
		if(currentState != null)
			currentState.onDestroy();
		newState.onCreate();
		currentState = newState;
	}
	
	public static void tick()
	{
		if(currentState != null)
			currentState.tick();
	}
}
