package com.shoter.game_object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionObject extends DynamicGameObject
{
	public CollisionObject(String texture, Vector2 position)
	{
		this(texture,position, 0f, 1f, new Rectangle(0,0,0,0));
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation, float size)
	{
		this(texture, position, rotation, size,  new Rectangle(0,0,0,0));
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation,
			float size, Rectangle rectangle) {
		super(texture, position, rotation, size, rectangle);
		
		
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation, float rotation_change, float size, Vector2 speed, Vector2 acceleration)
	{
		this(texture, position, rotation, size, new Rectangle(0,0,0,0));
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation, float rotation_change, float size, float mass, Vector2 speed, Vector2 acceleration, Rectangle rectangle)
	{
		this(texture, position, rotation, size, rectangle);
		setSpeed(speed);
		setAcceleration(acceleration);
		this.mass = mass;
		this.rotation_change = rotation_change;
	}
	
	public CollisionObject(String texture, Vector2 position, float mass)
	{
		super(texture, position, 0f, 1f, new Rectangle(0,0,0,0));
		this.mass = mass;
	}

}
