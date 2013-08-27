package com.shoter.aat;

import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.game.GameObject;

public class GameWindow
{
	Queue<GameObject>[] drawQueue = new Queue[10];
	Color backgroundColor;
	Timer tickTimer;
	private static int FRAME_PER_SECOND = 30;
	
	public GameWindow(Color backgroundColor)
	{
		Timer.Task runTick = new Task() {
			
			@Override
			public void run() {
				GameWindow.this.tick();
				
			}
		};
		
		tickTimer.scheduleTask(runTick, 0f, 1f / Float.valueOf(FRAME_PER_SECOND));
	}
	
	public GameWindow()
	{
		this(Color.WHITE);
	}
	
	public void tick()
	{
		
	}
	
	public void onCreate()
	{
		
	}
	
	public void onDestroy()
	{
		tickTimer.clear();
	}
	
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
	
}
