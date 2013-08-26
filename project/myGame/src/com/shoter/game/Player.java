package com.shoter.game;

public class Player
{
	int score;
	Bowl bowl;
	
	public Player()
	{
		this.score = 0;
		bowl = new Bowl(this);
	}

}
