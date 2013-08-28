package com.shoter.game_object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.WindowManager;
import com.shoter.gfx.TextureAtlas;

public class GameObject
{
	static boolean debug_draw = true;
	
	protected Sprite sprite;
	protected Vector2 position = new Vector2(0f,0f);
	float rotation;
	protected Rectangle rectangle = new Rectangle(0,0,0,0);
	float size;
	
	boolean flipped = true;
	
	
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
		
		setRectangle(rectangle);
		setPosition(position);
		setRotation(rotation);
		setSize(size);
		
		if(rectangle.x == 0 && rectangle.y == 0 && rectangle.width == 0 && rectangle.height == 0)
			setRectangleFromSPrite();
		
		onCreate();
	}
	
	public void setPosition(Vector2 position)
	{
		setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
		
	}
	
	public void setPosition(Vector2 position, boolean center)
	{
		if(center)
			setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
		else
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
	
	public void setRectangle(Rectangle other)
	{
		rectangle.x = other.x;
		rectangle.y = other.y;
		rectangle.width = other.width;
		rectangle.height = other.height;
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
	
	
	static ShapeRenderer SR = null;
	public void Draw(SpriteBatch spriteBatch)
	{
		if(!flipped)
			sprite.setScale(1,1);
		else
			sprite.setScale(-1,1);
			sprite.draw(spriteBatch);
		if(SR == null)
		{
			SR = new ShapeRenderer();
		}
	}
	
	void onCreate()
	{
		
	}
	
	public void destroy()
	{
		onDestroy();
		WindowManager.currentWindow.removeObjectFromQueue(this);
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
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	void setRectangleFromSPrite()
	{
		setRectangle(sprite.getBoundingRectangle());
	}
	
	public boolean isOutOfScreen()
	{
		return (position.x < -100f || position.x > 640f) && (position.y > 1000f || position.y < -100f);
	}
	
	public float getHeight()
	{
		return rectangle.height;
	}
}
