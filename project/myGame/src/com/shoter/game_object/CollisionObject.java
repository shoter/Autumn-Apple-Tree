package com.shoter.game_object;

import java.awt.geom.Point2D;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Game;

public class CollisionObject extends DynamicGameObject
{
	CollisionType collisionType;
	boolean collisionDetected = false;
	Vector2 afterCollisionPlace;
	float diagonal;
	boolean debugg = false;
	
	public CollisionObject attachedObject;
	public boolean attached = false;
	
	
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
				resolve_object(other);
		}
	}
	
	/**
	 * 
	 * @param a1p axis 1 position
	 * @param a1s axis 1 size
	 * @param a2p axis 2 position
	 * @param a2s axis 2 size
	 * @return
	 */
	public boolean axis_colission(float a1p, float a1s, float a2p, float a2s)
	{
		return (a1p + a1s > a2p && a1p < a2p + a2s );
	}
	
	public float axis_overlap(float a1pp, float a1p, float a1s, float a2p, float a2s)
	{
		if(a1pp > a2p)
			return -(a2p + a2s - a1p);
		else
			return a1p + a1s - a2p;
	}
	
	//Thx to MiJyn tutorial for helping me to resolve AABB problem
	//http://mijyn.github.io/tutorial/2013/06/05/platformer-aabb-cr.html 
	public void resolve_object(CollisionObject other)
	{
		if(attached && speed.x == 0 && speed.y == 0)
			return;
		
		boolean axis_y = false, axis_x = false;
		
		float overlap_x=0f, overlap_y=0f;
		
		boolean pp_colides_x = axis_colission(previousPosition.x, rectangle.width, other.previousPosition.x, other.rectangle.width);
		boolean pp_colides_y = axis_colission(previousPosition.y, rectangle.height, other.previousPosition.y, other.rectangle.height);
		if(pp_colides_x || pp_colides_y)
		{
			if(pp_colides_x)
				axis_y = true;
			if(pp_colides_y)
				axis_x = true;
		}
		else
		{
			axis_x = true;
			axis_y = true;
		}
			if(axis_colission(position.x, rectangle.width, other.position.x, other.rectangle.width))
		overlap_x = axis_overlap(previousPosition.x, position.x, rectangle.width, other.position.x, other.rectangle.width);
			if(axis_colission(position.y, rectangle.height, other.position.y, other.rectangle.height))
		overlap_y = axis_overlap(previousPosition.y, position.y, rectangle.height, other.position.y, other.rectangle.height);
		
		if(axis_x)
		{
			if(overlap_x < 0)
				onRightCollision(other);
			else
				onLeftCollision(other);
			
			position.x -= overlap_x;
			speed.x = 0f;
		}
		if(axis_y)
		{
			if(overlap_y < 0)
				onTopCollision(other);
			else
				onBottomCollision(other);
			
			position.y -= overlap_y;
			speed.y = 0f;
		}
	}
	
	void onTopCollision(CollisionObject other)
	{
			attached = true;
			attachedObject = other;
			onBottomCollided(other);
			
	}
	
	void onBottomCollision(CollisionObject other)
	{
		onTopCollided(other);
	}
	
	void onLeftCollision(CollisionObject other)
	{
		onRightCollided(other);
	}
	
	void onRightCollision(CollisionObject other)
	{
		
		onLeftCollided(other);
	}
	
	void onTopCollided(CollisionObject fromWho)
	{
		
	}
	
	void onBottomCollided(CollisionObject fromWho)
	{
		
	}
	
	void onLeftCollided(CollisionObject fromWho)
	{
		
	}
	
	void onRightCollided(CollisionObject fromWho)
	{
		
	}
	public void checkAttached()
	{
		if(attached == false)
			return;
		if(!axis_colission(position.x, rectangle.width, attachedObject.position.x, attachedObject.rectangle.width))
			cancelAttachment();
		else if(attachedObject.position.y + attachedObject.rectangle.height + 1 < position.y)
			cancelAttachment();
	}
	
	public void cancelAttachment()
	{
		attached = false;
		attachedObject = null;
	}
	
	
	protected void onCollision(Point2D collisionPlace, CollisionObject other)
	{
		
	}
	
	@Override
	void ApplyWind() 
	{
		speed.add( Game.wind.direction.cpy().div(mass) );
	}
	

	public void Tick(List<CollisionObject> otherObjects)
	{
		updatePreviousPosition();
		position.add(speed);
		updatePosition();
		checkAttached();
		if(!attached)
			speed.add(acceleration);
		ApplyWind();
		setRotation(rotation + rotation_change);
		
		if(collisionDetected)
		{
			setPosition(afterCollisionPlace.x, afterCollisionPlace.y);
			collisionDetected = false;
		}
		
		if(speed.x != 0f || speed.y != 0f)
			for(CollisionObject object : otherObjects)
				if(this != object)
					checkCollision(object);
	}
}
