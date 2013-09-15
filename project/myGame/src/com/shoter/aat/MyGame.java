package com.shoter.aat;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.factories.AppleFactory;
import com.shoter.factories.CloudFactory;
import com.shoter.factories.LeafFactory;
import com.shoter.game.Game;
import com.shoter.gfx.TextureAtlas;
import com.shoter.logger.Logger;
import com.shoter.menu.MenuWindow;

public class MyGame implements ApplicationListener {
	
	private SpriteBatch batch;
	Timer tickTimer;
	public static int FRAME_PER_SECOND = 50;
	
	
	@Override
	public void create() {		
		try
		{
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
		WindowManager.prepareToChangeWindow(new MenuWindow());
		tickTimer.scheduleTask(runTick, 0f, 1f / Float.valueOf(FRAME_PER_SECOND));
		}
		catch(Exception exception)
		{
			Logger.d("ERROR", exception.toString());
			Logger.d("ERROR", exception.getMessage());
			exception.printStackTrace();
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
		WindowManager.draw(batch);
	}
	
	public void tick()
	{
		WindowManager.tick();
		Game.tick();
		if(WindowManager.end)
			Gdx.app.exit();
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
		CloudFactory.init();
		Game.init();
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
