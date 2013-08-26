package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.gfx.Graphic;

public class GameObject
{
	Sprite sprite;
	Vector2 position = new Vector2(0f,0f);
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
		this.rectangle = rectangle;
		
		
		setPosition(position);
		setRotation(rotation);
		setSize(size);
		
		
		
		onCreate();
	}
	
	public void setPosition(Vector2 position)
	{
		setPosition(position.x, position.y);
		
	}
	
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		this.rectangle.x = position.x;
		this.rectangle.y = position.y;
		sprite.setPosition(position.x, position.y);
	}
	
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
		sprite.setRotation(rotation);
	}
	
	public void setSize(float size)
	{
		this.size = size;
		sprite.setScale(size);
	}
	
	public boolean collide(GameObject other)
	{
		return rectangle.overlaps(other.rectangle);
	}
	
	public void Draw(SpriteBatch spriteBatch)
	{
		
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
		Graphic.get().remove(sprite);
		Logic.get().removeFromQueue(this);
	}
	
	public void Tick()
	{
		
	}
	
	public GameObject copy()
	{
		return new GameObject(sprite, position, rotation, size, rectangle);
	}
}
