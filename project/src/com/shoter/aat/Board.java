package com.shoter.aat;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	Image bardejov;
	Timer timer;
	int x = 0;
	
	Graphic graphic;
	Logic logic;
	
    public Board() {
    	File test = new File("res/GFX/ground.png");
        ImageIcon ii = new ImageIcon(test.getAbsolutePath());
        bardejov = ii.getImage();
        
        timer = new Timer(25, this);
        timer.start();
        
        logic = new Logic(this);
        graphic = new Graphic(this);
        Game.Create();
    }

    public void paint(Graphics g) {
    	super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bardejov, x, 10, null); 
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint(); 
	}

}
