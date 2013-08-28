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
	List<CollisionObject>[] collisionList = new List[5];
	List<CollisionObject> collisionObjects = new ArrayList<CollisionObject>();
	Color backgroundColor;
	
	
	
	
	public Window(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		for(int i = 0; i < 10; i++)
			drawQueue[i] = new LinkedList<GameObject>();
		for(int i = 0; i < 5; i++)
			collisionList[i] = new ArrayList<CollisionObject>();
	}
	
	public Window()
	{
		this(Color.WHITE);
	}
	
	public void tick()
	{
		for(CollisionObject object : collisionObjects)
			for(List<CollisionObject> list : collisionList)
				for(CollisionObject otherObject : list)
					if(object != otherObject)
						object.checkCollision(otherObject);
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
	
	public void addColision(CollisionObject object, int order)
	{
		collisionList[order].add(object);
		collisionObjects.add(object);
	}
	
	public void removeCollisioN(CollisionObject object)
	{
		for(int i = 0; i< 5; i++)
			while(collisionList[i].remove(object));
		collisionObjects.remove(object);
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
