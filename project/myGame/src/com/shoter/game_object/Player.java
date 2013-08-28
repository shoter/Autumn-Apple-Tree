package com.shoter.game_object;

import java.awt.geom.Point2D;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
	
	Sprite texture_i1, texture_i2, texture_j1, texture_l2;
	
	boolean turnedRight = true;
	
	public Player(String texture_i1, String texture_i2, String texture_j1, Vector2 position)
	{
		super(texture_i1, position, CollisionType.DYNAMIC);
		
		this.texture_i1 = sprite;
		this.texture_i2 = new Sprite(TextureAtlas.getTexture(texture_i2));
		this.texture_j1 = new Sprite(TextureAtlas.getTexture(texture_j1));
		
		this.score = 0;
		setAcceleration(new Vector2(0f, -0.2f));
		bowl = new Bowl(this);
		this.mass = 1000f;
		//this.collisionSides = EnumSet.of( CollisionSides.TOP);
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
			speed.y += 7.5f;
			cancelAttachment();
			//setPosition(position.x, position.y);
		}
		bowl.Tick(appleList);
		flipped = !turnedRight;
		super.Tick(collisionList);
			bowl.setPosition(position.x + rectangle.width / 2 - bowl.rectangle.width / 2, position.y + rectangle.height - 1);

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
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		super.Draw(spriteBatch);
		bowl.Draw(spriteBatch);
	}
	
	

}
