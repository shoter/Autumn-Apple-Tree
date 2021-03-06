package com.shoter.game_object;

import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Game;

public class Leaf extends DynamicGameObject
{
	public Leaf(String texture, Vector2 position, float mass, Vector2 acceleration)
	{
		super(texture, position, mass);
		setAcceleration(acceleration);
	}
	
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
		super.Tick();
		
	}
	
	@Override
	void ApplyWind() {
		speed.add( Game.wind.direction.cpy().div(mass) );
		rotation_change += (Game.wind.direction.x * 30f) / mass;
		rotation_change -= rotation_change / 10f;
	}
	
	//@Override
	//public void setPosition(float x, float y) {
		//super.setPosition(x, y > 80 ? y : position.y);
	//}
	
	@Override
	public boolean isOutOfScreen() {
		return position.y < -100f;
	}
	
	@Override
	public Leaf copy() {
		return new Leaf(texture, position, mass, acceleration);
	}
}
