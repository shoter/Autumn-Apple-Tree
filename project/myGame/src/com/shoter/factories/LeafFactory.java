package com.shoter.factories;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.shoter.game_object.Leaf;

public class LeafFactory
{
	static int LEAFS_COUNT = 2;
	static Leaf[] leafs = new Leaf[LEAFS_COUNT];
	static float[] weight = new float[LEAFS_COUNT];
	static Random rand = new Random();
	
	public static void init()
	{
		/*for(int i = 0;i < LEAFS_COUNT; i++)
		{
			leafs_occurence[i] = 0;
		}*/
		
		leafs[0] = new Leaf("leaf", Vector2.Zero, 0.5f+(rand.nextInt(30))/100f, new Vector2(0f, -0.008f));
		leafs[1] = new Leaf("leaf2", Vector2.Zero, 0.6f+(rand.nextInt(30))/100f, new Vector2(0f, -0.007f));
		weight[0] = 0.7f;
		weight[1] = 0.6f;
	
	}
	
	public static Leaf createLeaf()
	{
		int baseLeaf = getBaseLeaf();
		Leaf base = leafs[baseLeaf];
		Leaf retLeaf = base.copy();
		retLeaf.setMass(weight[baseLeaf] + ((rand.nextInt(30))/100f));
		return retLeaf;
	}
	
	public static int getBaseLeaf()
	{
		return rand.nextInt(LEAFS_COUNT);
	}
}
