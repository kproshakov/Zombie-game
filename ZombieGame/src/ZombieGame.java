import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;

public class ZombieGame extends JPanel
{

    static ImageIcon imgBackground;
    Timer tmr, tmrKey, tmrBullets;
    ArrayList<Zombie> zombies;
    Random rnd;
    Player player;
    Bullet[] ammo;
    int round = 0, bulletCounter = 10, reloadCountdown = 5000;
    boolean reloading;

    public ZombieGame()
    {

	imgBackground = new ImageIcon(getClass().getResource("zombie_game_background.png"));

	tmr = new Timer(30, new tmrListener());
	tmrKey = new Timer(10, new tmrListener());
	tmrBullets = new Timer(5, new tmrListener());

	rnd = new Random();

	player = new Player();
	reloading = false;

	zombies = new ArrayList<Zombie>();

	ammo = new Bullet[10];

	for (int i = 0; i < 5; i++)
	{
	    zombies.add(new Zombie());

	}

	addKeyListener(new btnListener());
	setLayout(null);
	setFocusable(true);

	JFrame frame = new JFrame();

	frame.setContentPane(this);
	frame.setSize(imgBackground.getIconWidth(), imgBackground.getIconHeight());
	frame.setLocationRelativeTo(null);
	frame.setTitle("Zombie Attack");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setVisible(true);

	tmr.start();

	tmrBullets.start();
    }

    public void paint(Graphics g)
    {

	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(imgBackground.getImage(), 0, 0, this);
	g2.setColor(Color.RED);
	Collections.sort(zombies, new Comparator<Zombie>() {
	    public int compare(Zombie z1, Zombie z2)
	    {
		return z1.getY().compareTo(z2.getY());
	    }
	});
	player.draw(g2);
	for (int i = 0; i < zombies.size(); i++)
	{
	    zombies.get(i).draw(g2);
	    if (zombies.get(i).isDead() && zombies.get(i).incrementDC())
	    {
		zombies.set(i, new Zombie());
	    }
	}

	for (int i = 0; i < 10 - bulletCounter; i++)
	{
	    ammo[i].draw(g2);
	}

	if (reloading)
	{
	    g2.drawString(
		    "Reloading: " + reloadCountdown / 1000 + "."
			    + (reloadCountdown - (reloadCountdown / 1000) * 1000) / 100,
		    player.getX() + player.getWidth(), player.getY());

	}

    }

    private class btnListener extends KeyAdapter
    {
	Set<Character> keys = new HashSet<Character>();

	public void keyPressed(KeyEvent e)
	{
	    if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    {
		return;
	    }
	    char key = e.getKeyChar();

	    keys.add(key);

	    if (keys.size() == 1)
	    {
		// System.out.println("KEY1");
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
		    player.setMovement(Player.NORTH);
		} else if (e.getKeyCode() == KeyEvent.VK_S)
		{
		    player.setMovement(Player.SOUTH);
		} else if (e.getKeyCode() == KeyEvent.VK_A)
		{
		    player.setMovement(Player.WEST);
		} else if (e.getKeyCode() == KeyEvent.VK_D)
		{
		    player.setMovement(Player.EAST);
		}
		tmrKey.start();
	    } else
	    {

		char c = e.getKeyChar();
		// System.out.println(c);

		if (c == 'w')
		{

		    if (keys.contains('d'))
		    {
			// System.out.println("W + D");
			player.setMovement(Player.NORTHEAST);
		    } else if (keys.contains('a'))
		    {
			player.setMovement(Player.NORTHWEST);
		    }
		} else if (c == 'a')
		{
		    if (keys.contains('w'))
		    {
			player.setMovement(Player.NORTHWEST);
		    } else if (keys.contains('s'))
		    {
			player.setMovement(Player.SOUTHWEST);
		    }
		} else if (c == 's')
		{
		    if (keys.contains('a'))
		    {
			player.setMovement(Player.SOUTHWEST);
		    } else if (keys.contains('d'))
		    {
			player.setMovement(Player.SOUTHEAST);
		    }
		} else if (c == 'd')
		{
		    if (keys.contains('s'))
		    {
			player.setMovement(Player.SOUTHEAST);
		    } else if (keys.contains('w'))
		    {
			player.setMovement(Player.NORTHEAST);
		    }
		}

	    }
	}

    

    public void keyReleased(KeyEvent e)
    {
	if (e.getKeyCode() != KeyEvent.VK_SPACE)
	{
	    Character c = e.getKeyChar();
	    keys.remove(c);
	    if (keys.size() == 0)
	    {
		player.setMovement(Player.STOP);
		tmrKey.stop();
	    } else
	    {
		if (c == 'w')
		{
		    if (keys.contains('a'))
		    {
			player.setMovement(Player.WEST);
		    } else if (keys.contains('s'))
		    {
			player.setMovement(Player.SOUTH);
		    } else if (keys.contains('d'))
		    {
			player.setMovement(Player.EAST);
		    }
		} else if (c == 'a')
		{
		    if (keys.contains('w'))
		    {
			player.setMovement(Player.NORTH);
		    } else if (keys.contains('s'))
		    {
			player.setMovement(Player.SOUTH);
		    } else if (keys.contains('d'))
		    {
			player.setMovement(Player.EAST);
		    }
		} else if (c == 's')
		{
		    if (keys.contains('w'))
		    {
			player.setMovement(Player.NORTH);
		    } else if (keys.contains('a'))
		    {
			player.setMovement(Player.WEST);
		    } else if (keys.contains('d'))
		    {
			player.setMovement(Player.EAST);
		    }
		} else if (c == 'd')
		{
		    if (keys.contains('w'))
		    {
			player.setMovement(Player.NORTH);
		    } else if (keys.contains('a'))
		    {
			player.setMovement(Player.WEST);
		    } else if (keys.contains('s'))
		    {
			player.setMovement(Player.SOUTH);
		    }
		}

	    }
	} else 
	{
	    shootBullet();
	}
    }

}

private class tmrListener implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
	if (e.getSource() == tmr)
	{
	    for (int i = 0; i < zombies.size(); i++)
	    {
		zombies.get(i).move(rnd.nextInt(3) + 2);

		isWithin();
		touchedZombie();
		// repaint();
	    }
	} else if (e.getSource() == tmrKey)
	{
	    if (player.getX() >= imgBackground.getIconWidth() - player.getWidth())
	    {

		if (player.getY() <= 0)
		{
		    if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.SOUTH);
		    } else if (player.getMovement() == Player.NORTHEAST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.NORTH)
		    {
			player.setMovement(Player.STOP);
		    }
		} else if (player.getY() >= imgBackground.getIconHeight() - player.getHeight())
		{

		    if (player.getMovement() == Player.NORTHEAST)
		    {
			player.setMovement(Player.NORTH);
		    } else if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.SOUTH)
		    {
			player.setMovement(Player.STOP);
		    }
		} else
		{

		    if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.SOUTH);
		    } else if (player.getMovement() == Player.NORTHEAST)
		    {
			player.setMovement(Player.NORTH);
		    }
		}

		if (player.getMovement() == Player.EAST)
		{
		    player.setMovement(Player.STOP);
		}

	    } else if (player.getX() <= 0)
	    {

		if (player.getY() <= 0)
		{
		    if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.SOUTH);
		    } else if (player.getMovement() == Player.NORTH)
		    {
			player.setMovement(Player.STOP);
		    }
		} else if (player.getY() >= imgBackground.getIconHeight() - player.getHeight())
		{
		    if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setMovement(Player.NORTH);
		    } else if (player.getMovement() == Player.SOUTH)
		    {
			player.setMovement(Player.STOP);
		    }
		} else
		{

		    if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setMovement(Player.NORTH);
		    } else if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.SOUTH);
		    }
		}

		if (player.getMovement() == Player.WEST)
		{
		    player.setMovement(Player.STOP);
		}
	    } else if (player.getY() >= imgBackground.getIconHeight() - player.getHeight())
	    {

		if (player.getX() >= imgBackground.getIconWidth() - player.getWidth())
		{
		    if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.WEST);
		    }
		} else if (player.getX() <= 0)
		{
		    if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.STOP);
		    } else if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.EAST);
		    }
		} else
		{
		    if (player.getMovement() == Player.SOUTHEAST)
		    {
			player.setMovement(Player.EAST);
		    } else if (player.getMovement() == Player.SOUTHWEST)
		    {
			player.setMovement(Player.WEST);
		    }
		}

		if (player.getMovement() == Player.SOUTH)
		{
		    player.setMovement(Player.STOP);
		}
	    } else if (player.getY() <= 0)
	    {

		if (player.getX() >= imgBackground.getIconWidth() - player.getWidth())
		{
		    if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setDirection(Player.STOP);
		    } else if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setDirection(Player.NORTHWEST);
		    }
		} else if (player.getX() <= 0)
		{
		    if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setDirection(Player.WEST);
		    } else if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setDirection(Player.STOP);
		    }
		} else
		{
		    if (player.getMovement() == Player.NORTHEAST)
		    {
			player.setMovement(Player.EAST);
		    } else if (player.getMovement() == Player.NORTHWEST)
		    {
			player.setMovement(Player.WEST);
		    }
		}

		if (player.getMovement() == Player.NORTH)
		{
		    player.setMovement(Player.STOP);
		}
	    }
	    player.move();
	    // repaint();
	} else if (e.getSource() == tmrBullets)
	{
	    if (reloading)
	    {
		reloadCountdown -= 10;
		if (reloadCountdown <= 0)
		{
		    reloading = false;
		    reloadCountdown = 5000;
		    ammo = new Bullet[10];
		    bulletCounter = 10;
		}

	    }

	    for (int i = 0; i < 10 - bulletCounter; i++)
	    {
		ammo[i].move();
		if (ammo[i].getX() > imgBackground.getIconWidth() || ammo[i].getX() <= 0)
		{
		    if (ammo[i].isFired())
		    {
			ammo[i].setFired(false);
		    }

		} else if (ammo[i].getY() > imgBackground.getIconHeight() || ammo[i].getY() <= 0)
		{
		    if (ammo[i].isFired())
		    {
			ammo[i].setFired(false);
		    }

		}

		if (ammo[i].isFired())
		{
		    for (int j = 0; j < zombies.size(); j++)
		    {

			if (zombies.get(j).getRec().intersects(ammo[i].getRec()))
			{
			    zombies.get(j).killZombie();
			}
		    }
		}

	    }
	    repaint();
	}

    }

    }

    public void shootBullet()
    {
	if (!player.isDead())
	{

	    if (bulletCounter > 0)
	    {
		ammo[ammo.length - bulletCounter] = new Bullet(player.getCX(), player.getCY(),
			player.getDirection() == 1);
		ammo[ammo.length - bulletCounter].move();
		repaint();
		bulletCounter--;

	    } else
	    {// OUT OF AMMO
		reloading = true;
	    }
	}
    }

    public void isWithin()
    {
	for (int i = 0; i < zombies.size(); i++)
	{
	    if (zombies.get(i).getX() > imgBackground.getIconWidth() || zombies.get(i).getX() < 0)
	    {
		zombies.set(i, new Zombie());
	    } else if (zombies.get(i).getY() > (imgBackground.getIconHeight() - zombies.get(i).getHeight())
		    || zombies.get(i).getY() < 0)
	    {
		zombies.get(i).setAngle(zombies.get(i).getAngle() * -1);
	    }
	}
    }

    public void touchedZombie()
    {
	Rectangle p = new Rectangle(player.getX() + 5, player.getY() + (player.getHeight() / 3) * 2,
		player.getWidth() - 10, player.getHeight() / 3);
	Rectangle z;

	for (int i = 0; i < zombies.size(); i++)
	{
	    if (!zombies.get(i).isDead())
	    {
		z = new Rectangle(zombies.get(i).getX(), zombies.get(i).getY() + (zombies.get(i).getHeight() / 3) * 2,
			zombies.get(i).getWidth(), zombies.get(i).getHeight() / 3);
		if (p.intersects(z))
		{
		    player.killPlayer();
		    tmr.stop();
		    tmrBullets.stop();
		    tmrKey.stop();
		    JOptionPane.showMessageDialog(null, "You are dead!");
		    System.exit(0);
		}
	    }
	}

    }

    public static void main(String[] args)
    {

	new ZombieGame();
    }
}
