package com.shoter.game_object;

import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Game;

public class Cloud extends DynamicGameObject
{
	static float CLOUD_MAX_SPEED = 0.1f;
	
	public Cloud(String texture, Vector2 position, Vector2 speed)
	{
		super(texture, position);
		setSpeed(speed);
	}
	
	@Override
	void ApplyWind() {
		speed.x += Game.rand.nextFloat() * (Game.wind.direction.x/10);
		
		if(speed.x > CLOUD_MAX_SPEED)
			speed.x = CLOUD_MAX_SPEED;
		else if(speed.x < -CLOUD_MAX_SPEED)
			 speed.x = -CLOUD_MAX_SPEED;
		
		position.y += Game.wind.direction.y;
		setPosition(position.x, position.y);
	}
	@Override
	public Cloud copy() {
		return new Cloud(texture, position, speed);
	}
	
	@Override
	public boolean isOutOfScreen() {
		return (position.x < -100f || position.x > 640f);
	}
}
