package com.shoter.aat;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.factories.AppleFactory;
import com.shoter.factories.CloudFactory;
import com.shoter.factories.LeafFactory;
import com.shoter.game.Game;
import com.shoter.game.HeavyWindBlowListener;
import com.shoter.game_object.Apple;
import com.shoter.game_object.Cloud;
import com.shoter.game_object.CollisionObject;
import com.shoter.game_object.CollisionType;
import com.shoter.game_object.GameObject;
import com.shoter.game_object.Leaf;
import com.shoter.game_object.Player;
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
	Player player2;
	Sound music;
	
	static int MAX_CLOUDS = 10;
	public boolean secondPlayer = true;
	
	@Override
	public void onCreate() {
		createAppleFactory();
		
		backgroundColor = new Color(0.5f, 0.7f, 1f, 1f);
		ground = new CollisionObject("ground", new Vector2(320,40), CollisionType.STATIC);
		tree = new Tree(new Rectangle(0,400 - 142,400,143), this);
		
		Sound[] hero1_good = new Sound[2];
		hero1_good[0] = SoundAtlas.getSound("hero1_good1");
		hero1_good[1] = SoundAtlas.getSound("hero1_good2");
		
		Sound[] hero1_ohno = new Sound[2];
		hero1_ohno[0] = SoundAtlas.getSound("hero1_ohno1");
		hero1_ohno[1] = SoundAtlas.getSound("hero1_ohno2");
		
		Sound[] hero1_jump = new Sound[2];
		hero1_jump[0] = SoundAtlas.getSound("hero1_jump1");
		hero1_jump[1] = SoundAtlas.getSound("hero1_jump2");
		
		Sound[] hero2_good = new Sound[2];
		hero2_good[0] = SoundAtlas.getSound("hero2_good1");
		hero2_good[1] = SoundAtlas.getSound("hero2_good2");
		
		Sound[] hero2_ohno = new Sound[2];
		hero2_ohno[0] = SoundAtlas.getSound("hero2_ohno1");
		hero2_ohno[1] = SoundAtlas.getSound("hero2_ohno2");
		
		Sound[] hero2_jump = new Sound[2];
		hero2_jump[0] = SoundAtlas.getSound("hero2_jump1");
		hero2_jump[1] = SoundAtlas.getSound("hero2_jump2");
		
		music = SoundAtlas.getSound("music1");
		music.setLooping(1, true);
		music.play(0.2f);
		
		player = new Player("hero1_i1", "hero1_i2", "hero1_j1", hero1_ohno, hero1_good, hero1_jump,  new Vector2(100, 150));
		
		player2 = new Player("hero2_i1", "hero2_i2", "hero2_j1", hero2_ohno, hero2_good, hero2_jump, new Vector2(300, 150));
		
		player2.setKeys(Keys.A, Keys.D , Keys.W);
		
		addToQueue(ground, 5);
		addToQueue(player, 8);
		addToQueue(player2, 8);
		addColision(ground);
		addColision(player);
		addColision(player2);
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
		
		createEnviroment();
		
		for(int i = 0; i < MAX_CLOUDS; i++)
			generateCloud(new Rectangle(-200,160,1040,330));
		
		Game.wind.setListener(this);
		
		super.onCreate();
	}
	
	@Override
	public void tick() {
		super.tick();
		player.tick(appleList, collisionObjects);
		player2.tick(appleList, collisionObjects);
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
		spriteBatch.begin();
		Game.gameFont.setColor(Color.GREEN);
		Game.gameFont.draw(spriteBatch, "Player 1 : "  + String.format("%05d  (X%d)",player.score, player.bowl.getAppleCount()), 10, 20);
		Game.gameFont.setColor(Color.BLUE);
		Game.gameFont.draw(spriteBatch, "Player 2 : "  + String.format("%05d  (X%d)",player2.score, player2.bowl.getAppleCount()), 330, 20);
		spriteBatch.end();
		//draw_debug();
		//Game.wind.debug_draw();
	}
	ShapeRenderer SR = new ShapeRenderer();
	
	void draw_debug()
	{
		SR.begin(ShapeType.Filled);
		SR.setColor(Color.RED);
		for(Point2D debugPoint : Game.debugPoints)
		SR.rect((float)debugPoint.getX()-2, (float)debugPoint.getY()-2, 4,4);
		SR.end();
		
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
			spawnRectangle = new Rectangle(640,0,3000,1500);
		}
		else
		{
			spawnRectangle = new Rectangle(-3000,0,3000,1500);
		}
		
		for(int i = 0;i < 200;i ++)
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
		for(int i = 0; i < 7;i ++)
			appleFactory.createApple(new Rectangle(120f,480f,400f,300f));
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Game.wind.setListener(null);
	}
	
	public int enviFrontCount = 3;
	public int enviBackCount = 2;
	public int enviDownCount = 1;
	public int envi_count = 25;
	public void createEnviroment()
	{
		GameObject[] enviFront = new GameObject[enviFrontCount];
		GameObject[] enviBack = new GameObject[enviBackCount];
		GameObject[] enviDown = new GameObject[enviDownCount];
		for(int i = 0; i < enviFrontCount; i++)
			enviFront[i] = new GameObject("envi_f" + String.valueOf(i+1), Vector2.Zero.cpy());
		
		for(int i = 0; i < enviBackCount; i++)
			enviBack[i] = new GameObject("envi_b" + String.valueOf(i+1), Vector2.Zero.cpy());
		
		for(int i = 0; i < enviDownCount; i++)
			enviDown[i] = new GameObject("envi_d" + String.valueOf(i+1), Vector2.Zero.cpy());
		
		for(int i = 0;i < envi_count; i++)
		{
			int position = Game.rand.nextInt(3);
			GameObject toCopy = null;
			int drawOrder = 9;
			int y = 80;
			switch(position)
			{
			case 0 :
				toCopy = enviFront[Game.rand.nextInt(enviFrontCount)];
				break;
			case 1 :
				{
					toCopy = enviBack[Game.rand.nextInt(enviBackCount)];
					drawOrder = 7;
					break;
				}
			case 2:
				toCopy = enviDown[Game.rand.nextInt(enviDownCount)];
				y = (int)(80 - toCopy.getHeight());
				break;
			}
			
			GameObject copied = toCopy.copy();
			copied.setPosition(Game.rand.nextInt(640),  y);
			addToQueue(copied, drawOrder);
		}
	}

}
