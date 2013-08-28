package com.shoter.game_object;

import java.awt.geom.Point2D;
import java.util.EnumSet;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends CollisionObject
{
	public int score;
	public Bowl bowl;
	boolean jumped = false;
	
	public Player(String texture, Vector2 position)
	{
		super(texture, position, CollisionType.DYNAMIC);
		this.score = 0;
		setAcceleration(new Vector2(0f, -0.2f));
		bowl = new Bowl(this);
		this.mass = 1000f;
		//this.collisionSides = EnumSet.of( CollisionSides.TOP);
	}
	
	public void tick(List<Apple> appleList, boolean move)
	{
		speed.x = 0f;
		if(move)
		{
			debugg = true;
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			speed.x = -2f;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			speed.x = 2f;
		}
		
		if(Gdx.input.isButtonPressed(Keys.UP) && jumped == false)
		{
			speed.y += 5.5f;
			jumped = true;
			//setPosition(position.x, position.y);
		}
		bowl.Tick(appleList);
		}
		super.Tick();
		
		
		
	}
	
	@Override
	public void onCollisionTop(CollisionObject other) {
		// TODO Auto-generated method stub
		super.onCollisionTop(other);
		jumped = false;
	}
	
	@Override
	protected void onCollision(Point2D collisionPlace, CollisionObject other) {
		if(collisionPlace.getY() == other.position.y + other.sprite.getHeight())
			jumped = false;
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		super.Draw(spriteBatch);
		bowl.Draw(spriteBatch);
	}
	
	

}
