package com.shoter.aat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shoter.game.Logic;
import com.shoter.gfx.Graphic;

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
		Graphic.get().draw();
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
		Graphic.create(batch);
		Logic.create();
		Game.create();
	}
}
