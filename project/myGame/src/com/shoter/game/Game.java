package com.shoter.game;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.shoter.wind.Wind;

public class Game
{
	static public Wind wind = new Wind();
	static public Random rand = new Random();
	public static BitmapFont gameFont;
	public static List<Point2D> debugPoints = new ArrayList<Point2D>();
	
	public static void tick()
	{
	//	gameFont = new BitmapFont(Gdx.files.internal("gfx/lgsb.ttf"),Gdx.files.internal("gfx/lgsb.png"),false);
		wind.tick();
	}
	
	public static void init()
	{
		
	}
}
