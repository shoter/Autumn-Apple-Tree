package com.shoter.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.shoter.aat.Game;
import com.shoter.logger.Logger;
import com.shoter.state.StateMachine;

public class Logic implements ActionListener
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
		
		Timer timer = new Timer(1000 / 30, this);
		
	}
	
	public void Tick()
	{
		StateMachine.tick();
	}
	
	public static Logic get()
	{
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tick();
		
	}

}
