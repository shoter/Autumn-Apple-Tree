package com.shoter.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject
{
	Vector2 speed = new Vector2(0f, 0f);
	float mass = 1f;
	float rotation_change = 0f;
	Vector2 acceleration = new Vector2(0f, 0f);
	
	public DynamicGameObject(String texture, Vector2 position)
	{
		this(texture,position, 0f, 1f, new Rectangle(0,0,0,0));
	}
	
	public DynamicGameObject(String texture, Vector2 position, float rotation, float size)
	{
		this(texture, position, rotation, size,  new Rectangle(0,0,0,0));
	}

	/**
	 * Automatically sets collision rectangle from sprite
	 * 
	 */
	public DynamicGameObject(String texture, Vector2 position, float rotation,
			float size, Rectangle rectangle) {
		super(texture, position, rotation, size, rectangle);
		
		if(rectangle.x == 0 && rectangle.y == 0 && rectangle.width == 0 && rectangle.height == 0)
			setRectangleFromSPrite();
	}
	
	public DynamicGameObject(String texture, Vector2 position, float rotation, float rotation_change, float size, Vector2 speed, Vector2 acceleration)
	{
		this(texture, position, rotation, size, new Rectangle(0,0,0,0));
	}
	
	public DynamicGameObject(String texture, Vector2 position, float rotation, float rotation_change, float size, float mass, Vector2 speed, Vector2 acceleration, Rectangle rectangle)
	{
		this(texture, position, rotation, size, rectangle);
		setSpeed(speed);
		setAcceleration(acceleration);
		this.mass = mass;
		this.rotation_change = rotation_change;
	}
	
	public DynamicGameObject(String texture, Vector2 position, float mass)
	{
		super(texture, position, 0f, 1f, new Rectangle(0,0,0,0));
		this.mass = mass;
	}
	
	void setRectangleFromSPrite()
	{
		rectangle = sprite.getBoundingRectangle();
	}
	
	void ApplyWind( Vector2 wind )
	{
		speed.add( wind.div(mass) );
	}
	
	public void setMass(float mass)
	{
		this.mass = mass;
	}
	
	public void setSpeed(Vector2 speed)
	{
		this.speed.x = speed.x;
		this.speed.y = speed.y;
	}
	
	public void setAcceleration(Vector2 acceleration)
	{
		this.acceleration.x = acceleration.x;
		this.acceleration.y = acceleration.y;
	}
	
	@Override
	public void Tick()
	{
		position.add(speed);
		speed.add(acceleration);
		sprite.setPosition(position.x, position.y);
		//ApplyWind(wind);
	}
	
	public DynamicGameObject Clone()
	{
		DynamicGameObject DGO = new DynamicGameObject(texture, position, rotation, rotation_change, size, mass, speed, acceleration, rectangle);
		DGO.mass = this.mass;
		DGO.rotation_change = this.rotation_change;
		DGO.speed = this.speed;
		return null;
	}

}
