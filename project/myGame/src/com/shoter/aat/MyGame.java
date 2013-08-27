package com.shoter.aat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.gfx.TextureAtlas;

public class MyGame implements ApplicationListener {
	
	private SpriteBatch batch;
	Timer tickTimer;
	private static int FRAME_PER_SECOND = 30;
	
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
		WindowManager.draw(batch);
	}
	
	public void tick()
	{
		WindowManager.tick();
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
	}
}
