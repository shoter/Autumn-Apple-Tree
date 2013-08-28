package com.shoter.factories;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.MyGame;
import com.shoter.game.Game;
import com.shoter.game_object.Cloud;

public class CloudFactory
{
	static int CLOUD_COUNT = 4;
	static Cloud[] clouds = new Cloud[CLOUD_COUNT];
	
	public static void init()
	{
		clouds[0] = new Cloud("cloud1", Vector2.Zero, Vector2.Zero );
		clouds[1] = new Cloud("cloud2", Vector2.Zero, Vector2.Zero );
		clouds[2] = new Cloud("cloud3", Vector2.Zero, Vector2.Zero );
		clouds[3] = new Cloud("cloud4", Vector2.Zero, Vector2.Zero );
	}
	
	public static Cloud create_cloud()
	{

		Rectangle spawnRectangle = new Rectangle(-500, 160, 300, 330);
		if(Game.rand.nextBoolean())
			spawnRectangle.x = 840;

		return create_cloud(spawnRectangle);
	}
	
	public static Cloud create_cloud(Rectangle spawnRectangle)
	{
		Cloud base = clouds[Game.rand.nextInt(CLOUD_COUNT)];
		Cloud copy = base.copy();
		
		
		copy.setPosition(MyGame.getCoordinatesInsideRectangle(spawnRectangle));
		return copy;
	}
}
