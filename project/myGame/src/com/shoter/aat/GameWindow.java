package com.shoter.aat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.game.Apple;
import com.shoter.game.AppleFactory;
import com.shoter.game.GameObject;
import com.shoter.game.Leaf;
import com.shoter.game.LeafFactory;
import com.shoter.game.Player;
import com.shoter.wind.Wind;

public class GameWindow extends Window
{
	GameObject ground, tree;
	Player player;
	List<Apple> appleList = new ArrayList<Apple>();
	List<Leaf> leafList = new ArrayList<Leaf>();
	AppleFactory appleFactory;
	Random rand = new Random();
	Wind wind = new Wind()
	{
		public void onHeavyWindBlow(boolean isGoingLeft, com.shoter.wind.WindBlow windBlow) {
			super.onHeavyWindBlow(isGoingLeft, windBlow);
			generateLeafsOnBlow(isGoingLeft);
		};
	};
	
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
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				Leaf test = LeafFactory.createLeaf();
				Rectangle spawnRectangle = new Rectangle(-200,480,1040,300);
				test.setPosition(MyGame.getCoordinatesInsideRectangle(spawnRectangle));
				addToQueue(test, 6);
				leafList.add(test);
			}
		}, 0f, 0.2f);
		
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
		
		for(int i = 0; i < leafList.size(); i++)
		{
			Leaf leaf = leafList.get(i);
			leaf.Tick(wind);
			if(leaf.isOutOfScreen())
				leafList.remove(i);
		}
			
		
		wind.tick();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		//wind.debug_draw();
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
	
	public void generateLeafsOnBlow(boolean isGoingLeft)
	{
		Rectangle spawnRectangle = new Rectangle();
		if(isGoingLeft)
		{
			spawnRectangle = new Rectangle(640,0,5000,1000);
		}
		else
		{
			spawnRectangle = new Rectangle(-5000,0,5000,1000);
		}
		
		for(int i = 0;i < 500;i ++)
		{
			Leaf test = LeafFactory.createLeaf();
			test.setPosition(MyGame.getCoordinatesInsideRectangle(spawnRectangle));
			addToQueue(test, 6);
			leafList.add(test);
		}
		
	}
	
}
