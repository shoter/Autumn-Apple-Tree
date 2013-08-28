package com.shoter.game_object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Apple extends DynamicGameObject
{
	
	public int score;
	
	public Apple(String texture, Vector2 position)
	{
		this(texture, position, 0f, 1f, new Rectangle(0,0,0,0));
	}
	
	public Apple(String texture, Vector2 position, float rotation, float size)
	{
		this(texture, position, rotation, size,  new Rectangle(0,0,0,0));
	}

	public Apple(String texture, Vector2 position, float rotation, float size,
			Rectangle rectangle) {
		super(texture, position, rotation, size, rectangle);
		// TODO Auto-generated constructor stub
	}
	
	public Apple(String texture, Vector2 position, float rotation, float rotation_change, float size, float mass, Vector2 speed, Vector2 acceleration, Rectangle rectangle)
	{
		super(texture, position, rotation, rotation_change, size, mass, speed, acceleration, rectangle);
	}
	
	@Override
	void onDestroy() {
		super.onDestroy();
	}
	
	public Apple clone()
	{
		Apple retApple = new Apple(texture, position, rotation, rotation_change, size, mass, speed, acceleration, rectangle);
		return retApple;
	}

}
