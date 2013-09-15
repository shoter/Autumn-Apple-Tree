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
		addSound("hero1_good1.mp3", "hero1_good1");
		addSound("hero1_good2.mp3", "hero1_good2");
		addSound("hero1_ohno1.mp3", "hero1_ohno1");
		addSound("hero1_ohno2.mp3", "hero1_ohno2");
		addSound("hero1_jump1.mp3", "hero1_jump1");
		addSound("hero1_jump2.mp3", "hero1_jump2");
		
		addSound("hero2_good1.mp3", "hero2_good1");
		addSound("hero2_good2.mp3", "hero2_good2");
		addSound("hero2_ohno1.mp3", "hero2_ohno1");
		addSound("hero2_ohno2.mp3", "hero2_ohno2");
		addSound("hero2_jump1.mp3", "hero2_jump1");
		addSound("hero2_jump2.mp3", "hero2_jump2");
		addSound("AAT_music.wav", "music1");
		
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
