package com.shoter.gfx;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.shoter.logger.Logger;

public class TextureAtlas
{
	Map<String, Texture> textureMap = new HashMap<String, Texture>();
	
	public TextureAtlas()
	{
		initTextures();
	}
	
	void initTextures()
	{
		Texture.setEnforcePotImages(false);
		addTexture("ground.png", "ground");
		addTexture("apple_bad.png", "bad apple");
		addTexture("tree.png", "tree");
	}
	
	
	public void addTexture(String path, String name)
	{
		name = name.toLowerCase();
		path = "res\\gfx\\" + path;
		File file = new File(path);
		file = file.getAbsoluteFile();
		
		if(file.exists())
		{
			FileHandle fileHandle = new FileHandle(file.getPath());
			Texture texture = new Texture(fileHandle);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textureMap.put(name, texture);
		}
		else
			Logger.e("TextureAtlass", "File : "  + file.getPath() + " was not found");
	}
	
	public Texture getTexture(String name)
	{
		name = name.toLowerCase();
		return textureMap.get(name);
	}
	
	void destroy()
	{
		for(Texture texture : textureMap.values())
		{
			texture.dispose();
		}
	}

}
