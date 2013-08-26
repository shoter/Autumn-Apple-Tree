package com.shoter.gfx;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.logger.Logger;

public class Graphic
{
	SpriteBatch batch;
	static int queueCount = 5;
	static Graphic instance;
	public TextureAtlas textureAtlas;
	
	Sprite ground; //it will be ALWAYS displayed
	
	Queue<Sprite>[] drawQueue = new Queue[queueCount]; 
	
	Graphic(SpriteBatch batch)
	{
		instance = this;
		this.batch = batch;
		for(int i = 0;i < queueCount; i++)
			drawQueue[i] = new LinkedList<Sprite>();
		
		textureAtlas = new TextureAtlas();
		
		ground = new Sprite(textureAtlas.getTexture("ground"));
		
		addToQueue(ground, 0);
	}
	
	public static void create(SpriteBatch batch)
	{
		if(get() == null)
			new Graphic(batch);
		else
			Logger.w("LOGIC", "Trying to create new Graphic when other logic exist", true);
	}
	
	public void draw()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		batch.begin();
		for(Queue<Sprite> queue : drawQueue)
		{
			for(Sprite sprite : queue)
			{
				sprite.draw(batch);
			}
		}
		batch.end();
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

	
	public void destroy()
	{
		textureAtlas.destroy();
	}
}
