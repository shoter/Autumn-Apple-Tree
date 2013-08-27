package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.shoter.gfx.TextureAtlas;

public class AppleFactory
{
	static Apple badApple, normalApple, goodApple;
	
	static int delay = 12, current_delay = 0;
	
	public static void init()
	{
		badApple = new Apple("bad_apple", new Vector2(0f,0f));
		goodApple = new Apple("good_apple", new Vector2(0f,0f));
		normalApple = new Apple("normal_apple", new Vector2(0f,0f));
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
	}
}
