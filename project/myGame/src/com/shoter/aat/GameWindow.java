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
import com.shoter.factories.AppleFactory;
import com.shoter.factories.CloudFactory;
import com.shoter.factories.LeafFactory;
import com.shoter.game.Game;
import com.shoter.game.HeavyWindBlowListener;
import com.shoter.game.Player;
import com.shoter.game_object.Apple;
import com.shoter.game_object.Cloud;
import com.shoter.game_object.CollisionObject;
import com.shoter.game_object.CollisionType;
import com.shoter.game_object.GameObject;
import com.shoter.game_object.Leaf;
import com.shoter.game_object.Tree;

public class GameWindow extends Window implements HeavyWindBlowListener
{
	CollisionObject ground;
	Tree tree;
	Player player;
	List<Apple> appleList = new ArrayList<Apple>();
	List<Leaf> leafList = new ArrayList<Leaf>();
	List<Cloud> cloudList = new ArrayList<Cloud>();
	AppleFactory appleFactory;
	Random rand = new Random();
	
	static int MAX_CLOUDS = 10;
	
	@Override
	public void onCreate() {
		createAppleFactory();
		
		backgroundColor = new Color(0.5f, 0.7f, 1f, 1f);
		ground = new CollisionObject("ground", new Vector2(320,40), CollisionType.STATIC);
		tree = new Tree(new Rectangle(0,400 - 142,400,143), this);
		
		player = new Player();
		
		addToQueue(ground, 5);
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
		
		for(int i = 0; i < MAX_CLOUDS; i++)
			generateCloud(new Rectangle(-200,160,1040,330));
		
		Game.wind.setListener(this);
		
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
			apple.Tick();
			if(apple.isOutOfScreen())
				appleList.remove(i);
		}
		
		for(int i = 0; i < leafList.size(); i++)
		{
			Leaf leaf = leafList.get(i);
			leaf.Tick();
			if(leaf.isOutOfScreen())
				leafList.remove(i);
		}
		
		for(int i = 0; i < cloudList.size(); i++)
		{
			Cloud cloud = cloudList.get(i);
			cloud.Tick();
			if(cloud.isOutOfScreen())
				cloudList.remove(i);
		}
		while(cloudList.size() < MAX_CLOUDS)
			generateCloud();
	}
	
	void generateCloud()
	{
		Cloud newCloud = CloudFactory.create_cloud();
		addToQueue(newCloud, 0);
		cloudList.add(newCloud);
	}
	
	void generateCloud(Rectangle spawnRectangle)
	{
		Cloud newCloud = CloudFactory.create_cloud(spawnRectangle);
		addToQueue(newCloud, 0);
		cloudList.add(newCloud);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.draw(spriteBatch);
		Game.wind.debug_draw();
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
			spawnRectangle = new Rectangle(640,0,3000,1000);
		}
		else
		{
			spawnRectangle = new Rectangle(-3000,0,3000,1000);
		}
		
		for(int i = 0;i < 100;i ++)
		{
			Leaf test = LeafFactory.createLeaf();
			test.setPosition(MyGame.getCoordinatesInsideRectangle(spawnRectangle));
			addToQueue(test, 8);
			leafList.add(test);
		}
		
	}

	@Override
	public void onHeavyBlow(boolean isGoingLeft) {
		generateLeafsOnBlow(isGoingLeft);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Game.wind.setListener(null);
	}
	
}
