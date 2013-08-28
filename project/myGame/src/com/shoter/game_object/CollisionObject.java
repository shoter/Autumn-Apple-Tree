package com.shoter.game_object;

import java.awt.geom.Point2D;
import java.util.EnumSet;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Game;
import com.shoter.logger.Logger;

public class CollisionObject extends DynamicGameObject
{
	CollisionType collisionType;
	boolean collisionDetected = false;
	Vector2 afterCollisionPlace;
	float diagonal;
	boolean debugg = false;
	
	
	public Set<CollisionSides> collisionSides;
	

	public CollisionObject(String texture, Vector2 position,
			CollisionType collisionType) {
		this(texture, position, 0f, 1f, new Rectangle(0, 0, 0, 0));
		this.collisionType = collisionType;
		this.mass = 1f;
		this.collisionSides = EnumSet.of(CollisionSides.BOTTOM , CollisionSides.TOP , CollisionSides.LEFT , CollisionSides.RIGHT);
	}

	public CollisionObject(String texture, Vector2 position, float rotation,
			float size) {
		this(texture, position, rotation, size, new Rectangle(0, 0, 0, 0));
	}

	public CollisionObject(String texture, Vector2 position, float rotation,
			float size, Rectangle rectangle) {
		super(texture, position, rotation, size, rectangle);
		diagonal = new Vector2(rectangle.width, rectangle.height).len();

	}

	public CollisionObject(String texture, Vector2 position, float rotation,
			float rotation_change, float size, Vector2 speed,
			Vector2 acceleration) {
		this(texture, position, rotation, size, new Rectangle(0, 0, 0, 0));
	}

	public CollisionObject(String texture, Vector2 position, float rotation,
			float rotation_change, float size, float mass, Vector2 speed,
			Vector2 acceleration, Rectangle rectangle) {
		this(texture, position, rotation, size, rectangle);
		setSpeed(speed);
		setAcceleration(acceleration);
		this.mass = mass;
		this.rotation_change = rotation_change;
	}

	public CollisionObject(String texture, Vector2 position, float mass) {
		super(texture, position, 0f, 1f, new Rectangle(0, 0, 0, 0));
		this.mass = mass;
	}

	public void checkCollision(CollisionObject other) {
		if (collisionType == CollisionType.STATIC) return;
		if (collide(other))
		{
			if(isOnLeft(other) )
			{
				if(other.collisionSides.contains(CollisionSides.LEFT))
				onCollisionLeft(other);
				if(debugg)
				Logger.i("Collision", "Left");
			}
			else if(isOnRight(other))
			{
				if(other.collisionSides.contains(CollisionSides.RIGHT))
				onCollisionRight(other);
				if(debugg)
				Logger.i("Collision", "RIGHT");
			}
			else if(isOnTop(other))
			{
				if(other.collisionSides.contains(CollisionSides.TOP))
				onCollisionTop(other);
				if(debugg)
				Logger.i("Collision", "TOP");
			}
			else if(isOnBottom(other))
			{
				if(other.collisionSides.contains(CollisionSides.BOTTOM))
				onCollisionBottom(other);
				if(debugg)
				Logger.i("Collision", "BOTTOM");
			}
			
			
				
		}
	}
	
	public void onCollisionLeft(CollisionObject other)
	{
		speed.x = 0f;
		position.x = other.position.x - sprite.getWidth();
	}
	
	public void onCollisionRight(CollisionObject other)
	{
		speed.x = 0f;
		position.x = other.position.x + other.sprite.getWidth();
	}
	
	public void onCollisionTop(CollisionObject other)
	{
		speed.y = 0f;
		position.y = other.position.y + other.sprite.getHeight();
	}
	
	public void onCollisionBottom(CollisionObject other)
	{
		speed.y = 0f;
		position.y = other.position.y - sprite.getHeight();
	}
	
	public boolean isOnTop(CollisionObject other)
	{
		return (position.y < other.position.y + other.sprite.getHeight() &&
				position.y + sprite.getHeight() > other.position.y
				&& isGoingDown()
				);
	}
	
	public boolean isOnBottom(CollisionObject other)
	{
		return (position.x + sprite.getWidth() >= other.position.x && position.x + sprite.getWidth() <= other.position.x + other.sprite.getWidth()
				&&
				position.y +  sprite.getHeight() <= other.position.y + other.sprite.getHeight() / 2 
						&& isGoingTop()
				);
	}
	
	public boolean isOnLeft(CollisionObject other)
	{
		return (position.x  <= other.position.x && isGoingRight());
	}
	
	public boolean isOnRight(CollisionObject other)
	{
		return (position.x + sprite.getWidth() > other.position.x + other.sprite.getWidth()  && isGoingLeft());
	}
	
	
	protected void onCollision(Point2D collisionPlace, CollisionObject other)
	{
		
	}
	
	@Override
	void ApplyWind() 
	{
		speed.add( Game.wind.direction.cpy().div(mass) );
	}
	
	@Override
	public void Tick()
	{
		updatePreviousPosition();
		super.Tick();
		if(collisionDetected)
		{
			setPosition(afterCollisionPlace.x, afterCollisionPlace.y);
			collisionDetected = false;
		}
	}
}
