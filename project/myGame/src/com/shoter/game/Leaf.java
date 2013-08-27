package com.shoter.game;

import com.badlogic.gdx.math.Vector2;
import com.shoter.wind.Wind;

public class Leaf extends DynamicGameObject
{
	public Leaf(String texture, Vector2 position, float mass, Vector2 acceleration)
	{
		super(texture, position, mass);
		setAcceleration(acceleration);
	}
	
	@Override
	public void Tick(Wind wind) {
		// TODO Auto-generated method stub
		
		super.Tick(wind);
		
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
