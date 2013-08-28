package com.shoter.game_object;

public enum CollisionSides {
	LEFT(1),
	RIGHT(2),
	TOP(4),
	BOTTOM(8);
	
	int type;
	
	CollisionSides(int value)
	{
		this.type = value;
	}
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public boolean get(CollisionSides side)
	{
		return (type & side.type) == side.type;
	}
}
