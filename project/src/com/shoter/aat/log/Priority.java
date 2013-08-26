package com.shoter.aat.log;

public enum Priority
{
	INFO(1),
	WARNING(2),
	DEBUG(3),
	ERROR(4);
	
	int type;
	
	Priority(int value)
	{
		this.type = value;
	}
	
	int get()
	{
		return type;
	}
	
	@Override
	public String toString() {
		String name = super.toString();
		return super.toString().toLowerCase();
	}
	
}
