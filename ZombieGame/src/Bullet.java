
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.*;

public class Bullet
{
	private ImageIcon imgWest, imgEast;
	private int xPos, yPos, width, height;
	private boolean isFired, direction; // TRUE = right ; FALSE = left
	
			
	
	public Bullet(int x, Integer y, boolean dir)
	{
		imgWest = new ImageIcon(getClass().getResource("bulletWest.png"));
		imgEast = new ImageIcon(getClass().getResource("bulletEast.png"));
		
		xPos = x;
		yPos = y;
		
		height = imgWest.getIconHeight();
		width = imgWest.getIconWidth();
		direction = dir;
		
		isFired = true;
	}
	
	public void draw(Graphics2D g2)
	{
		if (isFired)
		{
		
			//g2.rotate(Math.toRadians(angle), xInitial, yInitial);
			g2.drawImage(!direction ? imgWest.getImage() : imgEast.getImage(), xPos, yPos, null);
			
			//g2.rotate(Math.toRadians(360 - angle), xInitial, yInitial);
			
//			g2.rotate(Math.toRadians(angle), xPos, yPos);
//			g2.drawImage(imgEast.getImage(), xPos, yPos, null);
//			b = new Rectangle(xPos, yPos, width, height);
//			g2.rotate( -1*Math.toRadians(angle), ZombieGame.player.getCX(), ZombieGame.player.getCY());
			
		}
	}
	
	public void move()
	{
//		int side_1, side_2;
		
//		side_1 = (int)(Math.abs(Math.cos(angle)*2) + 0.5);
//		side_2 = -1*(int)(Math.sin(angle)*2 + 0.5);
////		
//		
//		side_1 = (int)(Math.cos(angle)*2 + 0.5);
//		side_2 = -1*(int)(Math.abs(Math.sin(angle)*2 + 0.5));
		
//		System.out.println(side_1 + " " + side_2);
		
//		if (angle > 0 && angle < 90)
//		{
//			side_1 = (int)(Math.cos(angle)*2 + 0.5);
//			side_2 = -1*(int)(Math.abs(Math.sin(angle)*2 + 0.5));
//		}
//		else if (angle > 90 && angle < 180)
//		{
//			side_1 = (int)(Math.cos(angle)*2 + 0.5);
//			side_2 = -1*(int)(Math.abs(Math.sin(angle)*2 + 0.5));
//		}
//		else if (angle > 180 && angle > 270)
//		{
//			side_1 = -1*(180 + a);
//		}
//		else if (angle > 270 && angle < 360)
//		{
//			a = -1*(180 + Math.abs(a));
//		}
//		
		
//		xOnField += side_1;
//		yOnField += side_2;
		
		xPos += direction ? 5 : -5;
//		this.b = new Rectangle(xOnField, yOnField, width, height);
//		xPos += side_1;
//		yPos += side_2;
		
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public Rectangle getRec()
	{
		return new Rectangle(xPos, yPos, width, height);
	}
	
	public void setFired(boolean f)
	{
		isFired = f;
	}
	
	public boolean isFired()
	{
		return isFired;
	}
	
}
