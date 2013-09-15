package com.shoter.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.shoter.aat.GameWindow;
import com.shoter.aat.MyGame;
import com.shoter.aat.Window;
import com.shoter.aat.WindowManager;
import com.shoter.factories.AppleFactory;
import com.shoter.factories.CloudFactory;
import com.shoter.factories.LeafFactory;
import com.shoter.game.Game;
import com.shoter.game_object.Apple;
import com.shoter.game_object.Cloud;
import com.shoter.game_object.CollisionObject;
import com.shoter.game_object.CollisionType;
import com.shoter.game_object.GameObject;
import com.shoter.game_object.Leaf;
import com.shoter.game_object.Tree;

public class MenuWindow extends Window implements ButtonListener
{
	CollisionObject ground;
	Tree tree;
	List<Apple> appleList = new ArrayList<Apple>();
	List<Leaf> leafList = new ArrayList<Leaf>();
	List<Cloud> cloudList = new ArrayList<Cloud>();
	AppleFactory appleFactory;
	static int MAX_CLOUDS = 10;
	Random rand = new Random();
	Button bttnPlay, bttnQuit;

	@Override
	public void onCreate() {
		Game.wind.disableWind();
		createAppleFactory();
		backgroundColor = new Color(0.5f, 0.7f, 1f, 1f);
		ground = new CollisionObject("ground", new Vector2(320, 40),
				CollisionType.STATIC);
		tree = new Tree(new Rectangle(0, 400 - 142, 400, 143), this);
		
		bttnPlay = new Button("play", "play_on", new Vector2(160, 250));
		bttnPlay.setButtonListener(this);
		addToQueue(bttnPlay, 9);
		
		bttnQuit = new Button("quit", "quit_on", new Vector2(480, 250));
		bttnQuit.setButtonListener(this);
		addToQueue(bttnQuit, 9);
		
		addToQueue(ground, 5);

		Timer.schedule(new Task() {

			@Override
			public void run() {
				Leaf test = LeafFactory.createLeaf();
				Rectangle spawnRectangle = new Rectangle(-200, 480, 1040, 300);
				test.setPosition(MyGame
						.getCoordinatesInsideRectangle(spawnRectangle));
				addToQueue(test, 6);
				leafList.add(test);
			}
		}, 0f, 0.2f);
		
		createEnviroment();
		
		for(int i = 0; i < MAX_CLOUDS; i++)
			generateCloud(new Rectangle(-200,160,1040,330));
		
		super.onCreate();
	}
	
	@Override
	public void tick() {
		super.tick();
		bttnPlay.Tick();
		bttnQuit.Tick();
		
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
		//draw_debug();
		//Game.wind.debug_draw();
	}
	
	
	void createAppleFactory()
	{
		appleFactory = new AppleFactory(this, new Rectangle(120f,350f,400f,300f))
		{
			@Override
			public void onAppleSpawn(Apple apple) {
				super.onAppleSpawn(apple);
				MenuWindow _this = MenuWindow.this;
				_this.appleList.add(apple);
			}
		};
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

	@Override
	public void onButtonClick(Button button) {
		if(button == bttnPlay)
		{
			WindowManager.prepareToChangeWindow(new GameWindow());
		}
		else if( button == bttnQuit )
		{
			WindowManager.end = true;
		}
		
	}
	
	
	
}
