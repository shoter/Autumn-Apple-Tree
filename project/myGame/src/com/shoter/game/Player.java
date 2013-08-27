package com.shoter.game;

import java.util.List;

public class Player
{
	int score;
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
