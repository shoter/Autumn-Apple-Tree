package com.shoter.gfx;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.shoter.logger.Logger;

public class TextureAtlas
{
	static Map<String, Texture> textureMap = new HashMap<String, Texture>();
	
	public static void init()
	{
		Texture.setEnforcePotImages(false);
		addTexture("ground.png", "ground");
		addTexture("apple_bad.png", "bad_apple");
		addTexture("apple_red.png", "good_apple");
		addTexture("apple_good.png", "normal_apple");
		addTexture("tree.png", "tree");
		addTexture("bowl.png", "bowl");
		addTexture("leaf.png", "leaf");
		addTexture("leaf2.png", "leaf2");
		addTexture("leaf3.png", "leaf3");
		addTexture("cloud1.png", "cloud1");
		addTexture("cloud2.png", "cloud2");
		addTexture("cloud3.png", "cloud3");
		addTexture("cloud4.png", "cloud4");
		addTexture("branch.png", "branch");
		addTexture("hero1_i1.png", "hero1_i1");
		addTexture("hero1_i2.png", "hero1_i2");
		addTexture("hero1_j1.png", "hero1_j1");
		addTexture("hero2_i1.png", "hero2_i1");
		addTexture("hero2_i2.png", "hero2_i2");
		addTexture("hero2_j1.png", "hero2_j1");
		
		addTexture("envi_f1.png", "envi_f1");
		addTexture("envi_f2.png", "envi_f2");
		addTexture("envi_f3.png", "envi_f3");
		
		addTexture("envi_b1.png", "envi_b1");
		addTexture("envi_b2.png", "envi_b2");
		addTexture("envi_b3.png", "envi_b3");
		
		addTexture("envi_d1.png", "envi_d1");
		
		addTexture("play.png", "play");
		addTexture("play_on.png", "play_on");
		
		addTexture("quit.png", "quit");
		addTexture("quit_on.png", "quit_on");

	}
	
	
	public static void addTexture(String path, String name)
	{
		name = name.toLowerCase();
		path = "gfx/" + path;
		Logger.d("GFX", Gdx.files.internal(path).file().getAbsolutePath());
		Texture texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		textureMap.put(name, texture);
	}
	
	public static Texture getTexture(String name)
	{
		name = name.toLowerCase();
		Texture texture =  textureMap.get(name);
		if(texture == null)
		Logger.e("Texture_Atlas", "Texture " + name + " not found");
		return texture;
	}
	
	public static void destroy()
	{
		for(Texture texture : textureMap.values())
		{
			texture.dispose();
		}
	}

}
