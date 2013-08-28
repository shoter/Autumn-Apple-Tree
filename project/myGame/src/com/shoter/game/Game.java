package com.shoter.game;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.shoter.wind.Wind;

public class Game
{
	static public Wind wind = new Wind();
	static public Random rand = new Random();
	public static BitmapFont gameFont;
	public static List<Point2D> debugPoints = new ArrayList<Point2D>();
	
	public static void tick()
	{
		wind.tick();
	}
	
	public static void init()
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("gfx/lgsb.ttf"));
		gameFont = generator.generateFont(40);
	}
}
