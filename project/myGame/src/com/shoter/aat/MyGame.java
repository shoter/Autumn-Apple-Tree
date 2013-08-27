package com.shoter.aat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame implements ApplicationListener {
	
	private SpriteBatch batch;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		initSingletons();
		
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
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

	}
}
