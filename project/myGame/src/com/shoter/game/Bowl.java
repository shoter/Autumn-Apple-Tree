package com.shoter.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoter.logger.Logger;

public class Bowl extends GameObject
{
	Player player; 
	List<Apple> apples = new ArrayList<Apple>();
	public int capacity = 10;
	
	public Bowl(Player player)
	{
		super("bowl", Vector2.Zero);
		this.player = player;
		
	}
	
	public void Tick(List<Apple> appleList)
	{
		if(isBowlFull())
			onBowlFull();
		for( Apple apple : appleList)
		{
			tryToCatch(apple);
		}
		setPosition(Mouse.x, position.y);
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
		int bowlScore = bowlScore();
		player.score += bowlScore;
		createAppleParticles();
		apples.clear();
	}
	
	void createAppleParticles()
	{
		
	}
	
	public void tryToCatch(Apple apple)
	{
		if(collide(apple))
		{
			apple.destroy();
			apples.add(apple);
		}
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		// TODO Auto-generated method stub
		super.Draw(spriteBatch);
		Logger.i("BOWL", "drawn");
	}
	
	
}
