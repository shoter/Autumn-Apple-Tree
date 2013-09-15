package com.shoter.game_object;

import java.awt.geom.Point2D;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Game;
import com.shoter.gfx.TextureAtlas;

public class Player extends CollisionObject
{
	public int score;
	public Bowl bowl;
	
	int BUTTON_LEFT = Keys.LEFT;
	int BUTTON_RIGHT = Keys.RIGHT;
	int BUTTON_UP = Keys.UP;
	
	int texture = 1;
	static int texture_change = 20;
	int current_texture_change = 0;
	
	Sprite texture_i1, texture_i2, texture_j1;
	
	Sound[] sound_ohno, sound_good, sound_jump;
	
	boolean turnedRight = true;
	
	public Player(String texture_i1, String texture_i2, String texture_j1, Sound[] ohno, Sound[] good, Sound[] jump, Vector2 position)
	{
		super(texture_i1, position, CollisionType.DYNAMIC);
		
		this.texture_i1 = sprite;
		this.texture_i2 = new Sprite(TextureAtlas.getTexture(texture_i2));
		this.texture_j1 = new Sprite(TextureAtlas.getTexture(texture_j1));
		
		this.score = 0;
		setAcceleration(new Vector2(0f, -0.4f));
		bowl = new Bowl(this);
		this.mass = 1000f;
		
		sound_ohno = ohno;
		sound_good = good;
		sound_jump = jump;
	}
	
	public void setKeys(int button_left, int button_right, int button_up)
	{
		BUTTON_LEFT = button_left;
		BUTTON_RIGHT = button_right;
		BUTTON_UP = button_up;
	}
	
	public void tick(List<Apple> appleList, List<CollisionObject> collisionList)
	{
		texture_tick();
		
		speed.x = 0f;
			debugg = true;
		if(Gdx.input.isKeyPressed(BUTTON_LEFT))
		{
			speed.x = -5.5f;	
			turnedRight = false;
			
		}
		else if(Gdx.input.isKeyPressed(BUTTON_RIGHT))
		{
			speed.x = 5.5f;
			turnedRight = true;
		}
		
		if(Gdx.input.isKeyPressed(BUTTON_UP) && attached == true)
		{
			speed.y += 8.0f;
			cancelAttachment();
			doJumpVoice();
			//setPosition(position.x, position.y);
		}
		bowl.Tick(appleList);
		flipped = !turnedRight;
		super.Tick(collisionList);
			bowl.setPosition(position.x + rectangle.width / 2 - bowl.rectangle.width / 2, position.y + rectangle.height - 1);
		
			if(position.x <= 0)
				position.x = 0;
			if(position.x >= 640 - rectangle.width)
				position.x = 640  - rectangle.width;
	}
	
	@Override
	void onTopCollided(CollisionObject fromWho) {
		super.onTopCollided(fromWho);
		//if(bowl.getAppleCount() > 0)
			doOhNoVoice();
		bowl.clearApples();
	}
	
	void texture_tick()
	{
		current_texture_change++;
		if(current_texture_change > texture_change)
		{
			texture = texture==2?1:2;
			current_texture_change = 0;
		}
		if(texture==1)
			sprite = texture_i1;
		else
			sprite = texture_i2;
		if(attached == false)
			sprite = texture_j1;
	}
	
	@Override
	protected void onCollision(Point2D collisionPlace, CollisionObject other) {
	}
	
	public void doOhNoVoice()
	{
		sound_ohno[Game.rand.nextInt(sound_ohno.length)].play();
	}
	
	public void doGoodVoice()
	{
		sound_good[Game.rand.nextInt(sound_good.length)].play();
	}
	
	public void doJumpVoice()
	{
		sound_jump[Game.rand.nextInt(sound_jump.length)].play();
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		super.Draw(spriteBatch);
		bowl.Draw(spriteBatch);
	}
	
	

}
