package com.shoter.state;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.shoter.game.AppleFactory;
import com.shoter.game.Player;
import com.shoter.gfx.Graphic;

public class GameState extends State
{
	Sprite ground, tree;
	Player player;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Graphic graphic = Graphic.get();
		
		ground = new Sprite(graphic.textureAtlas.getTexture("ground"));
		tree = new Sprite(graphic.textureAtlas.getTexture("tree"));
		tree.setPosition(320 - tree.getWidth()/2, 80);
		
		graphic.addToQueue(ground, 2);
		graphic.addToQueue(tree, 1);
		
		player = new Player();
		
		AppleFactory.tick();
	}
	
	@Override
	public void onDestroy() {
		Graphic.get().clear();
		super.onDestroy();
	}

	@Override
	public void tick() {
		AppleFactory.tick();
		
	}
}
