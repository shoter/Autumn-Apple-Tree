package com.shoter.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


import com.badlogic.gdx.utils.Timer;
import com.shoter.logger.Logger;
import com.shoter.state.GameState;
import com.shoter.state.StateMachine;

public class Logic implements ActionListener
{
	static Logic instance;
	Queue<GameObject> tickQueue = new LinkedList<GameObject>();
	Timer timer;
	public static void create()
	{
		if(get() == null)
			instance = new Logic();
		else
			Logger.w("LOGIC", "Trying to create new Logic when other logic exist", true);
		
		StateMachine.changeState(new GameState());
	}
	
	Logic()
	{
		initTimer();
		AppleFactory.init();
		
	}
	
	public void initTimer()
	{
		    Timer.schedule(new Timer.Task() {
			
			@Override	
			public void run() {
				Logic.get().Tick();
			}
		},0f, 1f/30);
	}
	
	public void Tick()
	{
		StateMachine.tick();
		for(GameObject object : tickQueue)
		{
			object.Tick();
		}
	}
	
	public static Logic get()
	{
		return instance;
	}
	
	public void removeFromQueue(GameObject object)
	{
		while(tickQueue.remove(object));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tick();
		
		
	}

}
