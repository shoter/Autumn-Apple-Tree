package com.shoter.aat.gfx;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.shoter.aat.Board;

public class Graphic
{
	Board board;
	static int queueCount = 5;
	static Graphic instance;
	
	Queue<Sprite>[] drawQueue = new Queue[queueCount]; 
	
	public Graphic(Board board)
	{
		instance = this;
		this.board = board;
		for(int i = 0;i < queueCount; i++)
			drawQueue[i] = new LinkedList<Sprite>();
	}
	
	public void draw(Graphics2D g2d)
	{
		for(Queue<Sprite> queue : drawQueue)
		{
			for(Sprite sprite : queue)
			{
				sprite.Draw(g2d);
			}
		}
	}
	
	public void addToQueue(Sprite sprite)
	{
		drawQueue[sprite.drawOrder].add(sprite);
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
