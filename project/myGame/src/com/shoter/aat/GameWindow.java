package com.shoter.aat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.GameObject;

public class GameWindow extends Window
{
	GameObject ground, tree;
	
	@Override
	public void onCreate() {
		backgroundColor = new Color(0.5f, 0.7f, 1f, 1f);
		ground = new GameObject("ground", new Vector2(320,40));
		tree = new GameObject("tree", new Vector2(320, 280));
		
		addToQueue(ground, 5);
		addToQueue(tree, 4);
		super.onCreate();
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		super.tick();
	}
}
