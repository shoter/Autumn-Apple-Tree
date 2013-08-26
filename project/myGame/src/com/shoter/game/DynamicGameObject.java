package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject
{
	Vector2 speed = new Vector2(0f, 0f);
	float mass = 1f;
	float rotate;
	float rotation_change = 0f;
	Vector2 acceleration = new Vector2(0f, 0f);
	
	public DynamicGameObject(Sprite sprite, Vector2 position)
	{
		this(sprite,position, 0f, 1f, new Rectangle(sprite.getBoundingRectangle()));
	}
	
	public DynamicGameObject(Sprite sprite, Vector2 position, float rotation, float size)
	{
		this(sprite, position, rotation, size,  sprite.getBoundingRectangle());
	}

	public DynamicGameObject(Sprite sprite, Vector2 position, float rotation,
			float size, Rectangle rectangle) {
		super(sprite, position, rotation, size, rectangle);
		// TODO Auto-generated constructor stub
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
		DynamicGameObject retObject = new DynamicGameObject(sprite, acceleration, rotation_change, mass, rectangle);
		retObject.setSpeed(speed);
		retObject.setAcceleration(acceleration);
		retObject.mass = this.mass;
		retObject.rotation_change = this.rotation_change;
		return retObject;
	}

}
