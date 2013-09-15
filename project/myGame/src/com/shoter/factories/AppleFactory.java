package com.shoter.factories;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.MyGame;
import com.shoter.aat.Window;
import com.shoter.game_object.Apple;

public class AppleFactory
{
	public static Apple badApple, normalApple, goodApple;
	
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
		
		badApple.score = 1;
		normalApple.score = 4;
		goodApple.score = 9;
		
		badApple.setMass(3.8f);
		goodApple.setMass(3.5f);
		normalApple.setMass(4.4f);
	}
	
	public void tick()
	{
		current_delay++;
		if(current_delay > delay)
		{
			createApple(spawnRectangle);
			current_delay = 0;
		}
	}

	public void createApple(Rectangle where) {
		Random rand = new Random();
		switch(rand.nextInt(3))
		{
			case 0:
				spawnApple(badApple, where);
			break;
			case 1:
				spawnApple(normalApple, where);
				break;
				
			case 2:
				spawnApple(goodApple, where);
				break;
		}
	}
	
	private  void spawnApple(Apple apple, Rectangle where)
	{
		
		Apple newApple = apple.clone();
		newApple.setPosition(MyGame.getCoordinatesInsideRectangle(where));
		newApple.setSpeed(new Vector2(0,-1));
		newApple.setAcceleration(new Vector2(0,-0.025f));
		
		onAppleSpawn(newApple);
		
		window.addToQueue(newApple, 5);
	}
	
	
	
	public void onAppleSpawn(Apple apple)
	{
		
	}
}
