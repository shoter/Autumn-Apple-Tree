package com.shoter.aat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.game_object.CollisionObject;
import com.shoter.game_object.GameObject;

public class Window
{
	Queue<GameObject>[] drawQueue = new Queue[10];
	List<CollisionObject> collisionObjects = new ArrayList<CollisionObject>();
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
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
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
	
	public void addColision(CollisionObject object)
	{
		collisionObjects.add(object);
	}
	
	public void removeCollisioN(CollisionObject object)
	{
		while(collisionObjects.remove(object));
	}
	
	public void removeObjectFromQueue(GameObject object)
	{
		for(Queue<GameObject> queue : drawQueue)
			while(queue.remove(object));
	}
	
	public void onCreate()
	{
		
	}
	
	public void onDestroy()
	{
	}
	
	
}
