package com.shoter.game_object;

public enum CollisionType {
	STATIC(1),
	DYNAMIC(2);
	
	int type;
	
	CollisionType(int value)
	{
		this.type = value;
	}
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
