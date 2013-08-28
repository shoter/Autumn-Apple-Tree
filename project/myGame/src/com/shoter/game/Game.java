package com.shoter.game;

import java.util.Random;

import com.shoter.wind.Wind;

public class Game
{
	static public Wind wind = new Wind();
	static public Random rand = new Random();
	public static void tick()
	{
		wind.tick();
	}
	
	public static void init()
	{
		
	}
}
