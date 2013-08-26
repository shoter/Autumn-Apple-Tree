package com.shoter.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bowl
{
	Player player; 
	Sprite sprite;
	List<Apple> apples = new ArrayList<Apple>();
	public int capacity = 10;
	
	public Bowl(Player player)
	{
		this.player = player;
	}
	
	public void Tick()
	{
		if(isBowlFull())
			onBowlFull();
	}
	
	public int bowlScore()
	{
		int retVal = 0;
		for(Apple apple : apples)
		{
			retVal += apple.score;
		}
		return retVal;
	}
	
	public boolean isBowlFull()
	{
		return capacity >= apples.size();
	}
	
	void onBowlFull()
	{
		
		createAppleParticles();
	}
	
	void createAppleParticles()
	{
		
	}
}
