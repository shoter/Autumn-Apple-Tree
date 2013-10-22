package com.shoter.wind;

import com.shoter.game.Game;

public class HeavyWindBlow extends WindBlow
{	
	public HeavyWindBlow()
	{
		super(180, Game.rand.nextBoolean()?0.019f:-0.019f);
	}
}
