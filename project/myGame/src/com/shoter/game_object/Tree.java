package com.shoter.game_object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.shoter.aat.Window;

public class Tree extends GameObject
{
	Rectangle branchRectangle;
	static int BRANCH_COUNT = 5;
	TreeBranch[] branches = new TreeBranch[5];
	
	public Tree(Rectangle branchRectangle, Window owner)
	{
		super("tree", new Vector2(320, 280));
		owner.addToQueue(this, 4);
		this.branchRectangle = branchRectangle;
		initBranches(owner);
	}
	
	void initBranches(Window owner)
	{
			//TreeBranch branch = new TreeBranch(MyGame.getCoordinatesInsideRectangle(branchRectangle).add(position));
		    branches[0] = new TreeBranch(new Vector2(400-48, 400-80).add(position));
			owner.addToQueue(branches[0], 7);
			
			branches[1] = new TreeBranch(new Vector2(400-198, 400-60).add(position));
			owner.addToQueue(branches[1], 7);
			
			branches[2] = new TreeBranch(new Vector2(400-126, 400-134).add(position));
			owner.addToQueue(branches[2], 7);
			
			branches[3] = new TreeBranch(new Vector2(400-355, 400-134).add(position));
			owner.addToQueue(branches[3], 7);
			
			branches[4] = new TreeBranch(new Vector2(400-355, 400-4).add(position));
			owner.addToQueue(branches[4], 7);
	}
	
	@Override
	public void Draw(SpriteBatch spriteBatch) {
		super.Draw(spriteBatch);
	}

}
