package com.shoter.aat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.shoter.logger.Logger;

public class SoundAtlas
{
	static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	
	public static void init()
	{
		addSound("wind.mp3", "wind1");
	}
	
	
	public static void addSound(String path, String name)
	{
		name = name.toLowerCase();
		path = "sfx/" + path;
		
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		soundMap.put(name, sound);
	}
	
	public static Sound getSound(String name)
	{
		name = name.toLowerCase();
		Sound sound =  soundMap.get(name);
		if(sound == null)
		Logger.e("Sound_Atlas", "Sound " + name + " not found");
		return sound;
	}
	
	public static void destroy()
	{
		for(Sound sound : soundMap.values())
		{
			sound.dispose();
		}
	}

}
