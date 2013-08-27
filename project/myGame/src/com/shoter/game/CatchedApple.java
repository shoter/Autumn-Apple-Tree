package com.shoter.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CatchedApple
{
	Apple catchedApple;
	Vector2 relativePosition = new Vector2(0f,0f);
	Bowl bowl;
	
	public CatchedApple(Apple apple, Bowl bowl)
	{
		catchedApple = apple;
		this.bowl = bowl;
		apple.setRotation(0f);
		getRelativePosition();
	}
	
	public void getRelativePosition()
	{
		Random rand = new Random();
		int minHeight = (int)bowl.sprite.getHeight() - Bowl.BOWL_DEPTH;
		int maxHeight = minHeight + Bowl.BOWL_DEPTH;
		int maxWidth = (int)bowl.sprite.getWidth() - (int)catchedApple.sprite.getWidth();
		
		int currentRelativeX = (int)(catchedApple.position.x - bowl.position.x);
		
		if(currentRelativeX < 0)
			currentRelativeX = 0;
		if(currentRelativeX > maxWidth)
			currentRelativeX = maxWidth;
		
		relativePosition.y = minHeight + rand.nextInt(maxHeight-minHeight);
		relativePosition.x = currentRelativeX;
	}
	
	public void tick()
	{
		Vector2 currentPosition = bowl.position.cpy().add(relativePosition);
		catchedApple.setPosition(currentPosition, false);
	}
	
	public void draw(SpriteBatch spriteBatch)
	{
		catchedApple.Draw(spriteBatch);
	}
	
	public int getScore()
	{
		return catchedApple.score;
	}

}
