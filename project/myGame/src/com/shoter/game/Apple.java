package com.shoter.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Apple extends DynamicGameObject
{
	
	public int score;
	
	public Apple(Sprite sprite, Vector2 position)
	{
		this(sprite,position, 0f, 1f, new Rectangle(sprite.getBoundingRectangle()));
	}
	
	public Apple(Sprite sprite, Vector2 position, float rotation, float size)
	{
		this(sprite, position, rotation, size,  sprite.getBoundingRectangle());
	}

	public Apple(Sprite sprite, Vector2 position, float rotation, float size,
			Rectangle rectangle) {
		super(sprite, position, rotation, size, rectangle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	void onDestroy() {
		super.onDestroy();
	}
	
	public Apple clone()
	{
		Apple retApple = new Apple(new Sprite(sprite), acceleration, rotation_change, mass, rectangle);
		retApple.setSpeed(speed);
		retApple.setAcceleration(acceleration);
		retApple.mass = this.mass;
		retApple.rotation_change = this.rotation_change;
		retApple.score = this.score;
		
		return retApple;
	}

}
