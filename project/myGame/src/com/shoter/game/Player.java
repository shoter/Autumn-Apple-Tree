package com.shoter.game;

import java.util.List;

import com.shoter.game_object.Apple;
import com.shoter.game_object.Bowl;

public class Player 
{
	public int score;
	public Bowl bowl;
	
	public Player()
	{
		this.score = 0;
		bowl = new Bowl(this);
	}
	
	public void tick(List<Apple> appleList)
	{
		bowl.Tick(appleList);
	}
	
	

}
