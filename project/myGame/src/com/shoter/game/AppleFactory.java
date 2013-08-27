package com.shoter.game;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.MyGame;
import com.shoter.aat.Window;

public class AppleFactory
{
	static Apple badApple, normalApple, goodApple;
	
	Window window;
	int delay = 60, current_delay = 0;
	Rectangle spawnRectangle;
	
	public AppleFactory(Window window, Rectangle spawnRectangle)
	{
		this.window = window;
		this.spawnRectangle = spawnRectangle;
	}
	
	public static void init()
	{
		badApple = new Apple("bad_apple", new Vector2(0f,0f));
		goodApple = new Apple("good_apple", new Vector2(0f,0f));
		normalApple = new Apple("normal_apple", new Vector2(0f,0f));
		
		badApple.setMass(3.8f);
		goodApple.setMass(3.5f);
		normalApple.setMass(4.4f);
	}
	
	public void tick()
	{
		current_delay++;
		if(current_delay > delay)
		{
			Random rand = new Random();
			switch(rand.nextInt(3))
			{
				case 0:
					spawnApple(badApple);
				break;
				case 1:
					spawnApple(normalApple);
					break;
					
				case 2:
					spawnApple(goodApple);
					break;
			}
			current_delay = 0;
		}
	}
	
	public  void spawnApple(Apple apple)
	{
		
		Apple newApple = apple.clone();
		newApple.setPosition(MyGame.getCoordinatesInsideRectangle(spawnRectangle));
		newApple.setSpeed(new Vector2(0,-1));
		newApple.setAcceleration(new Vector2(0,-0.025f));
		
		onAppleSpawn(newApple);
		
		window.addToQueue(newApple, 5);
	}
	
	
	
	public void onAppleSpawn(Apple apple)
	{
		
	}
}
