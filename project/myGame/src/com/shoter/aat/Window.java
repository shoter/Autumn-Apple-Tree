package com.shoter.aat;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.game.GameObject;

public class Window
{
	Queue<GameObject>[] drawQueue = new Queue[10];
	Color backgroundColor;
	
	
	
	
	public Window(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		for(int i = 0; i < 10; i++)
			drawQueue[i] = new LinkedList<GameObject>();
	}
	
	public Window()
	{
		this(Color.WHITE);
	}
	
	public void tick()
	{
		
	}
	
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.begin();
		for(Queue<GameObject> queue : drawQueue)
			for(GameObject object : queue)
			{
				object.Draw(spriteBatch);
			}
		spriteBatch.end();
	}
	
	public void addToQueue(GameObject object, int order)
	{
		drawQueue[order].add(object);
	}
	
	public void onCreate()
	{
		
	}
	
	public void onDestroy()
	{
	}
	
	
}
