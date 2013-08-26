package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.Game;
import com.shoter.gfx.Graphic;

public class AppleFactory
{
	static Apple badApple, normalApple, goodApple;
	
	static int delay = 12, current_delay = 0;
	
	public static void init()
	{
		badApple = new Apple(new Sprite(Graphic.get().textureAtlas.getTexture("bad_apple")), new Vector2(0f,0f));
		goodApple = new Apple(new Sprite(Graphic.get().textureAtlas.getTexture("good_apple")), new Vector2(0f,0f));
		normalApple = new Apple(new Sprite(Graphic.get().textureAtlas.getTexture("normal_apple")), new Vector2(0f,0f));
	}
	
	public static void tick()
	{
		current_delay++;
		if(current_delay > delay)
		{
			spawnApple(badApple);
			current_delay = 0;
		}
	}
	
	public static void spawnApple(Apple apple)
	{
		Apple newApple = apple.clone();
		newApple.setPosition(new Vector2(320,300));
		newApple.setSpeed(new Vector2(0,-1));
		newApple.setAcceleration(new Vector2(0,-0.01f));
		
		Graphic.get().addToQueue(newApple.sprite, 2);
		Logic.get().tickQueue.add(newApple);
		Game.get().appleList.add(newApple);
	}
}
