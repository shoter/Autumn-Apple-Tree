package com.shoter.gfx;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
	}
	
	
	public static void addTexture(String path, String name)
	{
		name = name.toLowerCase();
		path = "gfx/" + path;
		File file = new File(path);
		//file = file.getAbsoluteFile();

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
