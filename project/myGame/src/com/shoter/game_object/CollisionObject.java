package com.shoter.game_object;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionObject extends DynamicGameObject
{
	CollisionType collisionType;
	float diagonal;
	
	public CollisionObject(String texture, Vector2 position, CollisionType collisionType)
	{
		this(texture,position, 0f, 1f, new Rectangle(0,0,0,0));
		this.collisionType = collisionType;
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation, float size)
	{
		this(texture, position, rotation, size,  new Rectangle(0,0,0,0));
	}
	
	public CollisionObject(String texture, Vector2 position, float rotation,
			float size, Rectangle rectangle) {
		super(texture, position, rotation, size, rectangle);
		diagonal = new Vector2(rectangle.width, rectangle.height).len();
		
		
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
	
	public void checkCollision(CollisionObject other)
	{
		if(collisionType == CollisionType.STATIC)
			return;
		if(collide(other))
		{
			Line2D movementLine = new Line2D.Float(position.x, position.y, position.x - speed.x * 100, position.y - speed.y * 100);
			Line2D[] lines = other.getCollisionLines();
			for(Line2D line : lines)
			{
				Point2D intersection = intersectionPoint(movementLine, line);
				if(intersection != null)
				{
					Vector2 diff = new Vector2((float)intersection.getX() - position.x, (float)intersection.getY() - position.y);
					float len = diff.len() + diagonal;
					diff.nor();
					
					diff.x *= len;
					diff.y *= len;
					setSpeed(diff);
					
				}
			}

		}
	}
	
	public Line2D[] getCollisionLines()
	{
		Line2D[] collisionLines = new Line2D[4];
		collisionLines[0] = new Line2D.Float(position.x, position.y,
				position.x + rectangle.width, position.y);
		
		collisionLines[1] = new Line2D.Float(position.x, position.y,
				position.x, position.y + rectangle.height);
		
		collisionLines[2] = new Line2D.Float(position.x  ,
				position.y + rectangle.height, position.x  + rectangle.width , position.y);
		
		collisionLines[3] = new Line2D.Float(position.x + rectangle.width, position.y,
				position.x, position.y + rectangle.height);
		
		return collisionLines;
	}
	
	/**
	 * Check if the line intersects with other line
	 * 
	 * @param line 
	 * @return point if intersection was found, otherwise null
	 */
	Point2D intersectionPoint(Line2D line, Line2D line2)
	{				
	    float A1 = (float)(line.getY2() - line.getY1());
		float B1 = (float)(line.getX1() - line.getX2());
		float C1 = (float)(A1*line.getX1() - B1*line.getY1());
		
		float A2 = (float)(line2.getY2() - line2.getY1());
		float B2 = (float)(line2.getX1() - line2.getX2());
		float C2 = (float)(A1*line2.getX1() - B1*line2.getY1());
		
		float delta = A1*B2 - A2*B1;
		if(delta == 0) 
		   return null;

		float x = (B2*C1 - B1*C2)/delta;
		float y = (A1*C2 - A2*C1)/delta;
		return new Point2D.Float(x, y);
	}
}
