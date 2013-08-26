package com.shoter.aat.types;

public class Position
{
	float x,y;
	public Position(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Position(Position other)
	{
		this.x = other.x;
		this.y = other.y;
	}
	
	public Position copy()
	{
		return new Position(this);
	}
	
	public Position add(Position other)
	{
		this.y += other.y;
		this.x += other.x;
		return new Position(this);
	}
	
	public Position add(float x, float y)
	{
		return add(new Position(x,y));
	}
	
	public Position substract(Position other)
	{
		this.x -= other.x;
		this.y -= other.y;
		return new Position(this);
	}

}
