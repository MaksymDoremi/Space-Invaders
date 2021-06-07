package com.maks.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.maks.src.Game;
import com.maks.src.GlobalPosition;
import com.maks.src.input.Controller;

public class Ship extends GlobalPosition {

	private String shipImage = "/images/ship.png";
	private ImageIcon ship = new ImageIcon(getClass().getResource(shipImage));

	int velX = 0;
	int velY = 0;

	public int health = 10;

	Controller c;

	public Ship(int x, int y) {
		super(x, y);

	}

	public int getX() {
		return super.x;
	}

	public int getY() {
		return super.y;
	}

	public void update() {

		x += velX;
		y += velY;

		// COLLISIONS WITH BORDERS

		if (y < 0) {
			y = 0;
		}

		if (y > Game.HEIGHT - ship.getIconHeight() - 30) {
			y = Game.HEIGHT - ship.getIconHeight() - 30;
		}

		if (x < 0) {
			x = 0;
		}

		if (x > Game.WIDTH - ship.getIconWidth() - 14) { // 14 pro symetrii s obou stran
			x = Game.WIDTH - ship.getIconWidth() - 14;
		}

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			velY = -3;
		} else if (key == KeyEvent.VK_S) {
			velY = 3;
		} else if (key == KeyEvent.VK_A) {
			velX = -3;
		} else if (key == KeyEvent.VK_D) {
			velX = 3;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			velY = 0;
		} else if (key == KeyEvent.VK_S) {
			velY = 0;
		} else if (key == KeyEvent.VK_A) {
			velX = 0;
		} else if (key == KeyEvent.VK_D) {
			velX = 0;
		} else if (key == KeyEvent.VK_SPACE) {

		}
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getShipImage(), super.x, super.y, null);

		// RED HP LINE
		g2d.setPaint(Color.red);
		g2d.fillRect(x - 2, y + getShipImage().getHeight(null), 90, 10);

		// GREEN HP LINE
		g2d.setPaint(Color.green);
		g2d.fillRect(x - 2, y + getShipImage().getHeight(null), 9 * health, 10);
	}

	public Image getShipImage() {
		return ship.getImage();
	}

}
