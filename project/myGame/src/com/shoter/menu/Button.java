package com.shoter.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game.Mouse;
import com.shoter.game_object.GameObject;
import com.shoter.gfx.TextureAtlas;

public class Button extends GameObject
{
	Sprite onGraphic;
	Sprite offGraphic;
	ButtonListener buttonListener;
	
	public Button(String texture, String onTexture, Vector2 position)
	{
		super(texture,position, 0f, 1f, new Rectangle(0,0,0,0));
		onGraphic = new Sprite(TextureAtlas.getTexture(onTexture));
		offGraphic = sprite;
		onGraphic.setPosition(sprite.getX(), sprite.getY());
	}
	
	@Override
	public void Tick() {
		int x = Mouse.x;
		int y = Mouse.y;
		Rectangle mouseRect = new Rectangle(x,480-y,1,1);
		if(mouseRect.overlaps(rectangle))
		{
			sprite = onGraphic;
			if(Gdx.input.isButtonPressed(Buttons.LEFT))
				if(buttonListener != null)
					buttonListener.onButtonClick(this);
		}
		else
			sprite = offGraphic;
		super.Tick();
	}
	
	public void setButtonListener(ButtonListener listener)
	{
		buttonListener = listener;
	}
	
}
