
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Player
{
	private Random rnd;
	private ImageIcon imgEast, imgWest, imgDead;
	private boolean isDead;
	private int xPos, yPos, width, height, direction, movement;
	public static final int EAST = 1, WEST = 0, NORTH = 3, SOUTH = 4, NORTHWEST = 5, NORTHEAST = 6, SOUTHWEST = 7, SOUTHEAST = 8, STOP = -1;
	
	public Player()
	{
		
		isDead = false;
		rnd = new Random();
		direction = rnd.nextInt(2);
		
		imgEast = new ImageIcon(getClass().getResource("shooterEast.png"));
		imgWest = new ImageIcon(getClass().getResource("shooterWest.png"));
		imgDead = new ImageIcon(getClass().getResource("deadPlayer.png"));
		
		width = imgEast.getIconWidth();
		height = imgEast.getIconHeight();

		xPos = ZombieGame.imgBackground.getIconWidth()/2 - width/2;
		yPos = ZombieGame.imgBackground.getIconHeight()/2 - height/2;
		
		
		
	}
	
	public Player(int x, int y)
	{
		
		isDead = false;
		rnd = new Random();
		direction = rnd.nextInt(2);
		
		imgEast = new ImageIcon(getClass().getResource("shooterEast.png"));
		imgWest = new ImageIcon(getClass().getResource("shooterWest.png"));
		imgDead = new ImageIcon(getClass().getResource("deadSPlayer.png"));
		
		xPos = x;
		yPos = y;
		
		width = imgEast.getIconWidth();
		height = imgEast.getIconHeight();
	}
	
	public Player(int x, int y, int dir)
	{
		
		isDead = false;
		rnd = new Random();
		direction = dir;
		
		imgEast = new ImageIcon(getClass().getResource("shooterEast.png"));
		imgWest = new ImageIcon(getClass().getResource("shooterWest.png"));
		imgDead = new ImageIcon(getClass().getResource("deadSPlayer.png"));
		
		xPos = x;
		yPos = y;
		
		width = imgEast.getIconWidth();
		height = imgEast.getIconHeight();
	}
	
	public void draw(Graphics2D g2)
	{
		if (!isDead)
		{
			if (direction == WEST)
			{
				g2.drawImage(imgWest.getImage(), xPos, yPos, null);
			}
			else
			{
				g2.drawImage(imgEast.getImage(), xPos, yPos, null);
			}
		}
		else
		{
			g2.drawImage(imgDead.getImage(), xPos, yPos, null);
		}
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public int getMovement()
	{
		return movement;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public Integer getY()
	{
		return yPos;
	}
	
	public int getCX()
	{
		return xPos + width/2;
	}
	
	public int getCY()
	{
		return yPos + height/2;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public void killPlayer()
	{
		isDead = true;
	}
	
	public void move()
	{
		if (!isDead)
		{
			if(movement == EAST)
			{
				direction = EAST;
				xPos += 3;
			}
			else if (movement == WEST)
			{
				direction = WEST;
				xPos -= 3;
			}
			else if (movement == NORTH)
			{
				yPos -= 3;
			}
			else if (movement == SOUTH)
			{
				yPos += 3;
			}
			else if (movement == NORTHWEST)
			{
				direction = WEST;
				xPos -= 2;
				yPos -= 2;
			}
			else if (movement == NORTHEAST)
			{
				direction = EAST;
				xPos += 2;
				yPos -= 2;
			}
			else if (movement == SOUTHWEST)
			{
				direction = WEST;
				xPos -= 2;
				yPos += 2;
			}
			else if (movement == SOUTHEAST)
			{
				direction = EAST;
				xPos += 2;
				yPos += 2;
			}
		}
	}
	
	public Rectangle getRec()
	{
	    return new Rectangle(xPos + 5, yPos + (height / 3), width - 10, 
		    height / 3);
	}

	public void setDirection(int dir)
	{
		direction = dir;
	}

	public void setMovement(int mov)
	{
		movement = mov;
	}
	
	public void setX(int x)
	{
		xPos = x;
	}
	
	public void setY(int y)
	{
		yPos = y;
	}

	public void setLocation(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
}
