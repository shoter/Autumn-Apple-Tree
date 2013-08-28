package com.shoter.wind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.SoundAtlas;
import com.shoter.game.HeavyWindBlowListener;
import com.shoter.logger.Logger;

public class Wind
{
	public Vector2 direction = new Vector2(0f,0f);
	Random rand = new Random();
	List<WindBlow> windBlows = new ArrayList<WindBlow>();
	ShapeRenderer SR = new ShapeRenderer();
	public float maxStrength = 0.2f;
	
	HeavyWindBlowListener listener = null;
	
	int windStrengthDebug[] = new int[640];
	int windStrengthDebugY[] = new int[640];
	int currentDebug = 0;
	
	int timeFromHeavyWindBlow = 0;
	static int timeBetweenHeavyWindBlow = 300;
	
	public Wind()
	{
		for(int i = 0;i < 640; i++)
		{
			windStrengthDebug[i] = 0;
			windStrengthDebugY[i] = 0;
		}
		
		direction.x = rand.nextFloat() * maxStrength;
		direction.y = (rand.nextFloat() * maxStrength) / 100;
	}
	
	public void tick()
	{
		for(int i = 0;i < windBlows.size(); i++)
		{
			WindBlow windBlow = windBlows.get(i);
			if(windBlow.isDone())
			{
				windBlows.remove(i);
				continue;
			}
			
			direction.x += windBlow.getStrength();
			direction.y += windBlow.getStrength() / 15f;
			
			if(direction.x > maxStrength)
				direction.x = maxStrength;
			else if(direction.x < -maxStrength)
				direction.x = -maxStrength;
			
			if(direction.y > maxStrength/3)
				direction.y = maxStrength/3;
			else if(direction.y < -maxStrength/3)
				direction.y = -maxStrength/3;
			
			
			
			windBlow.tick();
			
		}
		
		if(isHeavyBlow())
		{
			WindBlow heavyWindBlow = generateHeavyBlow();
			onHeavyWindBlow(heavyWindBlow.strength < 0f, heavyWindBlow);
			windBlows.add(heavyWindBlow);
			timeFromHeavyWindBlow = 0;
		}
		
		direction.x -= direction.x / 200f;
		direction.y -= direction.y / 800f;
		
		float strength = (direction.x / maxStrength);
		strength *= 100;
		
		windStrengthDebug[currentDebug] = (int) strength;
		windStrengthDebugY[currentDebug++] = (int) (direction.y / ((float)maxStrength/3f) * 100f);
		if(rand.nextInt(1000) > 995)
		{
			Logger.i("WIATR", "Nowy wiatr " + String.valueOf(windBlows.size()));
			windBlows.add(new WindBlow(600, (-0.5f + rand.nextFloat()) / 300f));
		}
		currentDebug %= 640;
		timeFromHeavyWindBlow++;
	}
	
	public void setListener(HeavyWindBlowListener heavyWindBlowListener)
	{
		this.listener = heavyWindBlowListener;
	}
	
	public void debug_draw()
	{
		 SR.begin(ShapeType.Filled );
		 SR.setColor(Color.WHITE);
		 SR.rect(0, 0, 640, 100);
		 
		 
		 SR.end();
		 SR.begin(ShapeType.Point );
		 
		 for(int i = 0;i < 640;i++)
		 {
			 if(windStrengthDebug[i] > 0f)
			 {
				 SR.setColor(Color.RED);
				 SR.point(i, windStrengthDebug[i], 0);
			 }
			 else
			 {
				 SR.setColor(Color.BLUE);
				 SR.point(i, -windStrengthDebug[i], 0);
			 }
			 
			 if(windStrengthDebugY[i] > 0f)
			 {
				 SR.setColor(Color.PINK);
				 SR.point(i, windStrengthDebugY[i], 0);
			 }
			 else
			 {
				 SR.setColor(Color.GREEN);
				 SR.point(i, -windStrengthDebugY[i], 0);
			 }
		 
		 }
		 SR.end();
	}
	
	public WindBlow generateHeavyBlow()
	{
		boolean isMinus = rand.nextBoolean();
		return new WindBlow(180, isMinus?0.015f:-0.015f);
	}
	
	public void onHeavyWindBlow(boolean isGoingLeft, WindBlow windBlow)
	{
		Sound blowSound = SoundAtlas.getSound("wind1");
		blowSound.play();
		Logger.i("WIATR", "Silny wiatr! Lepiej uwa¿aæ :)");
		if(listener != null)
			listener.onHeavyBlow(isGoingLeft);
	}
	
	boolean isHeavyBlow()
	{
		double chance = rand.nextDouble();
		if(timeFromHeavyWindBlow > timeBetweenHeavyWindBlow)
		{
			double propability = (timeFromHeavyWindBlow - timeBetweenHeavyWindBlow) / (timeBetweenHeavyWindBlow * 2);
			if(chance < propability)
				return true;
		}
		return false;
	}
}
