package com.shoter.aat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shoter.game.Logic;
import com.shoter.gfx.Graphic;

public class MyGame implements ApplicationListener {
	
	private SpriteBatch batch;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		initSingletons();
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
		Logic.create();
		Graphic.create(batch);
		Game.create();
	}
}
