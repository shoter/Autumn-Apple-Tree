package com.shoter.aat.gfx;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import com.shoter.aat.types.Position;

 
public class Sprite
{
	Position position;
	public float rotation;
	int drawOrder;
	Image image;
	
	/**
	 * creates new Sprite
	 * 
	 * @param position starting sprite position. Value is being copied
	 * @param imageFile path to image in gfx folder
	 * @param drawOrder draw order in which sprite will be drawn by drawQueue
	 * 
	 */
	public Sprite(Position position, File imageFile, int drawOrder)
	{
		File imageResource = new File("res\\gfx\\" + imageFile.getPath());
        ImageIcon ii = new ImageIcon(imageResource.getAbsolutePath());
        this.image = ii.getImage();
        this.drawOrder = drawOrder;
        this.position = position.copy();
	}
	
	public void Draw(Graphics2D g2d)
	{
		g2d.drawImage(image,(int) position.x,(int) position.y, null); 
	}
	
	public void addToDrawQueue()
	{
		Graphic.get().addToQueue(this);
	}
	
}
