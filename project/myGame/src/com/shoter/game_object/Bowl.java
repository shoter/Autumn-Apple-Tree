package com.shoter.game_object;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Mouse;

public class Bowl extends GameObject
{
	Player player; 
	List<CatchedApple> apples = new ArrayList<CatchedApple>();
	public int capacity = 10;
	public static int BOWL_DEPTH = 2; //pixels
	
	public Bowl(Player player)
	{
		super("bowl", Vector2.Zero);
		this.player = player;
		setPosition(position.x, 100);
		
	}
	
	public void Tick(List<Apple> appleList)
	{
		if(isBowlFull())
			onBowlFull();

		
		for(CatchedApple apple : apples)
			apple.tick();
		
		for( int i = 0; i < appleList.size(); i++)
		{
			Apple apple = appleList.get(i);
			
			if(tryToCatch(apple))
				appleList.remove(apple);
		}
		
	}
	
	public int bowlScore()
	{
		int retVal = 0;
		for(CatchedApple apple : apples)
		{
			retVal += apple.getScore();
		}
		return retVal;
	}
	
	public boolean isBowlFull()
	{
		return  apples.size() >= capacity;
	}
	
	void onBowlFull()
	{
		clearApples();
	}
	
	public void clearApples()
	{
		int bowlScore = bowlScore();
		player.score += bowlScore * getAppleCount();
		createAppleParticles();
		apples.clear();
	}
	
	void createAppleParticles()
	{
		
	}
	
	public boolean tryToCatch(Apple apple)
	{
		if(collide(apple))
		{
			apple.destroy();
			apples.add(new CatchedApple(apple, this));
			return true;
		}
		return false;
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		for(CatchedApple apple : apples)
			apple.draw(spriteBatch);
		
		super.Draw(spriteBatch);
	}
	
	public int getAppleCount()
	{
		return apples.size();
	}
	
	
}
