package com.shoter.gfx;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.game.Logic;
import com.shoter.logger.Logger;

public class Graphic
{
	SpriteBatch batch;
	static int queueCount = 5;
	static Graphic instance;
	
	Queue<Sprite>[] drawQueue = new Queue[queueCount]; 
	
	Graphic(SpriteBatch batch)
	{
		instance = this;
		this.batch = batch;
		for(int i = 0;i < queueCount; i++)
			drawQueue[i] = new LinkedList<Sprite>();
	}
	
	public static void create(SpriteBatch batch)
	{
		if(get() == null)
			new Graphic(batch);
		else
			Logger.w("LOGIC", "Trying to create new Graphic when other logic exist", true);
	}
	
	public void draw(Graphics2D g2d)
	{
		for(Queue<Sprite> queue : drawQueue)
		{
			for(Sprite sprite : queue)
			{
				sprite.draw(batch);
			}
		}
	}
	
	public void addToQueue(Sprite sprite,int drawOrder)
	{
		drawQueue[drawOrder].add(sprite);
	}
	
	public static Graphic get()
	{
		return instance;
	}
	
	public void repaint()
	{
		for(Queue<Sprite> queue : drawQueue)
		queue.clear();
		
	}

}
