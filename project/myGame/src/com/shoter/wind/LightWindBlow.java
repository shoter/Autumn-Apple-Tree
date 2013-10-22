package com.shoter.wind;

import com.shoter.game.Game;

public class LightWindBlow extends WindBlow
{
	public LightWindBlow() {
		super(600, (-0.5f + Game.rand.nextFloat()) / 300f);
	}
	
}
