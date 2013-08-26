package com.shoter.logger;

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
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
}
