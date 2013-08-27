package com.shoter.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.wind.Wind;

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
	
	
	
	void ApplyWind( Wind wind )
	{
		speed.add( wind.direction.cpy().div(mass) );
		rotation_change += (wind.direction.x * 10f) / mass;
		rotation_change -= rotation_change / 10f;
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
	
	public void Tick(Wind wind)
	{
		position.add(speed);
		updatePosition();
		speed.add(acceleration);
		ApplyWind(wind);
		setRotation(rotation + rotation_change);
	}
	
	void updatePosition()
	{
		rectangle.x = position.x;
		rectangle.y = position.y;
		sprite.setPosition(position.x, position.y);
	}
	public DynamicGameObject Clone()
	{
		DynamicGameObject DGO = new DynamicGameObject(texture, position, rotation, rotation_change, size, mass, speed, acceleration, rectangle);
		return DGO;
	}

}
