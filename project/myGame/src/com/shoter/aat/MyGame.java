package com.shoter.aat;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.game.AppleFactory;
import com.shoter.game.LeafFactory;
import com.shoter.gfx.TextureAtlas;

public class MyGame implements ApplicationListener {
	
	private SpriteBatch batch;
	Timer tickTimer;
	private static int FRAME_PER_SECOND = 60;
	boolean logicDone = false;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		initSingletons();
		
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		tickTimer = new Timer();
		
		Timer.Task runTick = new Task() {
			
			@Override
			public void run() {
				MyGame.this.tick();
				
			}
		};
		WindowManager.prepareToChangeWindow(new GameWindow());
		tickTimer.scheduleTask(runTick, 0f, 1f / Float.valueOf(FRAME_PER_SECOND));
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
		if(logicDone)
		{
		WindowManager.draw(batch);
		logicDone = false;
		}
	}
	
	public void tick()
	{
		WindowManager.tick();
		logicDone = true;
	}
	

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	void initSingletons()
	{
		TextureAtlas.init();
		SoundAtlas.init();
		LeafFactory.init();
		AppleFactory.init();
	}
	public static Vector2 getCoordinatesInsideRectangle(Rectangle rectangle)
	{
		Random rand = new Random();
		Vector2 retValue = new Vector2();
		retValue.x = rectangle.x + rand.nextInt((int)rectangle.width);
		retValue.y = rectangle.y + rand.nextInt((int)rectangle.height);
		return retValue;
	}
}
