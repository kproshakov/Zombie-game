
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Zombie
{
    private ImageIcon[][] pics = new ImageIcon[4][2];
    private ImageIcon imgZombie, imgDeadZombie;
    private boolean isDead;
    private Random rnd;
    private int xPos, yPos, width, height, direction, deathCounter;
    private double angle, storedAngle;
    private final int EAST = 1, WEST = 0;

    public Zombie()
    {
	imgDeadZombie = new ImageIcon(getClass().getResource("deadZombie.png"));
	for (int i = 0; i < 4; i++)
	{
	    for (int j = 0; j < 2; j++)
	    {
		if (j == 1)
		{
		    pics[i][j] = new ImageIcon(getClass().getResource("zombie" + i + "East.png"));
		} else
		{
		    pics[i][j] = new ImageIcon(getClass().getResource("zombie" + i + "West.png"));
		}

	    }
	}

	imgDeadZombie = new ImageIcon(getClass().getResource("deadZombie.png"));

	rnd = new Random();

	direction = rnd.nextInt(2);

	imgZombie = pics[rnd.nextInt(4)][direction];

	if (direction == WEST)
	{
	    imgZombie = pics[rnd.nextInt(4)][1];
	    xPos = 0;
	    width = imgZombie.getIconWidth();
	    height = imgZombie.getIconHeight();
	    yPos = rnd.nextInt(ZombieGame.imgBackground.getIconHeight() - height);

	} else
	{
	    imgZombie = pics[rnd.nextInt(4)][0];
	    xPos = ZombieGame.imgBackground.getIconWidth();
	    width = imgZombie.getIconWidth();
	    height = imgZombie.getIconHeight();
	    yPos = rnd.nextInt(ZombieGame.imgBackground.getIconHeight() - height);

	}

	angle = rnd.nextInt(100) / 100.00;
	if (rnd.nextBoolean())
	{
	    angle *= -1;
	}
	storedAngle = yPos;
	isDead = false;
	deathCounter = 0;
    }

    public void draw(Graphics2D g2)
    {
	if (!isDead)
	{
	    g2.drawImage(imgZombie.getImage(), xPos, yPos, null);
	} else
	{
	    g2.drawImage(imgDeadZombie.getImage(), xPos, yPos, null);
	}
    }

    public boolean incrementDC()
    {
	deathCounter++;
	return deathCounter == 50;
    }

    public Rectangle getRec()
    {
	return new Rectangle(xPos, yPos, width, height);
    }

    public void killZombie()
    {
	isDead = true;
    }

    public boolean isDead()
    {
	return isDead;
    }

    public double getAngle()
    {
	return angle;
    }

    public int getDirection()
    {
	return direction;
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

    public void move(int x)
    {
	if (!isDead)
	{
	    if (direction == WEST)
	    {
		xPos += x;
	    } else
	    {
		xPos -= x;
	    }
	    storedAngle += angle;
	    yPos = (int) storedAngle;
	}
    }

    public void setDirection(int dir)
    {
	if (dir == WEST)
	{
	    direction = WEST;
	} else if (dir == EAST)
	{
	    direction = EAST;
	}
    }

    public void setAngle(double a)
    {
	angle = a;
    }

}
