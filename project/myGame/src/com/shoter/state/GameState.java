package com.shoter.state;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.shoter.gfx.Graphic;

public class GameState extends State
{
	Sprite ground, tree;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Graphic graphic = Graphic.get();
		
		ground = new Sprite(graphic.textureAtlas.getTexture("ground"));
		tree = new Sprite(graphic.textureAtlas.getTexture("tree"));
		tree.setPosition(320 - tree.getWidth()/2, 80);
		
		graphic.addToQueue(ground, 1);
		graphic.addToQueue(tree, 2);
	}
	
	@Override
	public void onDestroy() {
		Graphic.get().clear();
		super.onDestroy();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
