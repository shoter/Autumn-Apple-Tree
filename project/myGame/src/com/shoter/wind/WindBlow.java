package com.shoter.wind;

public class WindBlow
{
	float strength;
	int time;
	int currentTime;
	
	public WindBlow(int durationTime, float strength)
	{
		time = durationTime;
		currentTime = 0;
		this.strength = strength/10;
	}
	
	public void tick()
	{
		currentTime++;
	}
	
	public boolean isDone()
	{
		return currentTime >= time;
	}
	public float getStrength()
	{
		//Logger.i("WIND", "Strength = " + String.valueOf(currentTime) + "//" + String.valueOf(time));
		return (float) Math.sin(((float)currentTime/(float)time)* Math.PI)  * strength;
	}
}
