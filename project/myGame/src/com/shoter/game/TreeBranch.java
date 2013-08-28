package com.shoter.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shoter.game_object.GameObject;

public class TreeBranch extends GameObject
{
	Vector2 basePosition;
	float resilience = 0.5f;
	public TreeBranch(Vector2 position)
	{
		super("branch", position);
		this.basePosition = new Vector2(position.x, position.y);
		resilience += (Game.rand.nextFloat() * 1);
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		setPosition(basePosition.add(new Vector2(Game.wind.direction.x * resilience , Game.wind.direction.y * resilience)));
		super.Draw(spriteBatch);
	}
}
