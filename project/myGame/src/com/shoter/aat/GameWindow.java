package com.shoter.aat;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Apple;
import com.shoter.game.AppleFactory;
import com.shoter.game.GameObject;
import com.shoter.game.Player;
import com.shoter.wind.Wind;

public class GameWindow extends Window
{
	GameObject ground, tree;
	Player player;
	List<Apple> appleList = new ArrayList<Apple>();
	AppleFactory appleFactory;
	Wind wind = new Wind();
	
	@Override
	public void onCreate() {
		createAppleFactory();
		
		backgroundColor = new Color(0.5f, 0.7f, 1f, 1f);
		ground = new GameObject("ground", new Vector2(320,40));
		tree = new GameObject("tree", new Vector2(320, 280));
		
		player = new Player();
		
		addToQueue(ground, 5);
		addToQueue(tree, 4);
		addToQueue(player.bowl, 8);
		super.onCreate();
	}
	
	@Override
	public void tick() {
		super.tick();
		player.tick(appleList);
		appleFactory.tick();
		
		for(int i = 0; i < appleList.size(); i++)
		{
			Apple apple = appleList.get(i);
			apple.Tick(wind);
			if(apple.isOutOfScreen())
				appleList.remove(i);
		}
			
		
		wind.tick();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		wind.debug_draw();
	}
	
	void createAppleFactory()
	{
		appleFactory = new AppleFactory(this, new Rectangle(120f,350f,400f,300f))
		{
			@Override
			public void onAppleSpawn(Apple apple) {
				super.onAppleSpawn(apple);
				GameWindow _this = GameWindow.this;
				_this.appleList.add(apple);
			}
		};
	}
	
}
