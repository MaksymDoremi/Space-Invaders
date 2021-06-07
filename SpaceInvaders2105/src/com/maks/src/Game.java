package com.maks.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.maks.src.input.Controller;
import com.maks.src.input.KeyInput;
import com.maks.src.objects.Bullet;
import com.maks.src.objects.Ship;

public class Game extends JPanel implements ActionListener {

	// WIDTH and HEIGHT
	public static final int WIDTH = 650;
	public static final int HEIGHT = 650;

	// WIDTH and HEIGHT for Ship location
	public static final int WIDTHSHIP = (new Ship(0, 0).getShipImage().getWidth(null));
	public static final int HEIGHTSHIP = (new Ship(0, 0).getShipImage().getHeight(null));

	// BACKGROUND
	private String background = "/images/space.jpg";

	private static final long serialVersionUID = 1L;

	Timer timer;

	Ship ship;

	Random rnd;

	Controller c;

	public Game() {
		this.setFocusable(true);

		timer = new Timer(10, this);
		timer.start();

		rnd = new Random();

		// Creating a Ship
		ship = new Ship(WIDTH / 2 - WIDTHSHIP / 2, HEIGHT - HEIGHTSHIP - 50);

		c = new Controller();

		addKeyListener(new KeyInput(ship, this));
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		// Draw background
		g2d.drawImage(getBackgroundImage(), 0, 0, this);

		// Draw a Ship
		ship.draw(g2d);
		c.draw(g2d);
		c.render(g2d);
		c.gameOver(ship, g2d, timer);
	}

	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			c.addBullet(new Bullet(ship.x + ship.getShipImage().getWidth(null) / 2, ship.y - 30, -7));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		ship.update();
		c.update();
		c.tick();
		c.killEnemy();
		c.hurt(ship);
		c.crush(ship);
	}
}
