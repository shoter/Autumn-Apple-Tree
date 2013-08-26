package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject
{
	Sprite sprite;
	Vector2 position;
	float rotation;
	Rectangle rectangle;
	float size;
	
	public GameObject(Sprite sprite, Vector2 position)
	{
		this(sprite,position, 0f, 1f, new Rectangle(sprite.getBoundingRectangle()));
	}
	
	public GameObject(Sprite sprite, Vector2 position, float rotation, float size)
	{
		this(sprite, position, rotation, size,  sprite.getBoundingRectangle());
	}
	
	public GameObject(Sprite sprite, Vector2 position, float rotation, float size,  Rectangle rectangle)
	{
		this.sprite = sprite;
		this.position = position;
		this.rotation = rotation;
		this.rectangle = rectangle;
		this.size = size;
		
		onCreate();
	}
	
	public boolean collide(GameObject other)
	{
		return rectangle.overlaps(other.rectangle);
	}
	
	public void Draw(SpriteBatch spriteBatch)
	{
		sprite.setPosition(position.x, position.y);
		sprite.setRotation(rotation);
		sprite.setScale(size);
		
		sprite.draw(spriteBatch);
	}
	
	void onCreate()
	{
		
	}
	
	public void destroy()
	{
		onDestroy();
	}
	
	void onDestroy()
	{
		
	}
}
