package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.gfx.TextureAtlas;

public class GameObject
{
	Sprite sprite;
	Vector2 position = new Vector2(0f,0f);
	float rotation;
	Rectangle rectangle = new Rectangle();
	float size;
	
	String texture; //for copying.
	
	public GameObject(String texture, Vector2 position)
	{
		this(texture,position, 0f, 1f, new Rectangle(0,0,0,0));
	}
	
	public GameObject(String texture, Vector2 position, float rotation, float size)
	{
		this(texture, position, rotation, size,  new Rectangle(0,0,0,0));
	}
	
	public GameObject(String texture, Vector2 position, float rotation, float size,  Rectangle rectangle)
	{
		sprite = new Sprite(TextureAtlas.getTexture(texture));
		
		this.texture = texture;
		this.rectangle.set(rectangle);
		setPosition(position);
		setRotation(rotation);
		setSize(size);
		
		onCreate();
	}
	
	public void setPosition(Vector2 position)
	{
		setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
		
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
	}
	
	public void Tick()
	{
		
	}
	
	public GameObject copy()
	{
		return new GameObject(texture, position, rotation, size, rectangle);
	}
}
