package com.maks.src.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.maks.src.GlobalPosition;

public class Bullet extends GlobalPosition {
	private String bulletImage = "/images/bullet.png";
	private ImageIcon bullet = new ImageIcon(getClass().getResource(bulletImage));

	private int velocity;

	public Bullet(int x, int y, int velocity) {
		super(x, y);
		this.velocity = velocity;
	}

	public Bullet(int x, int y, int velocity, String image) {
		super(x, y);
		this.velocity = velocity;
		bulletImage = image;
		bullet = new ImageIcon(getClass().getResource(bulletImage));
	}

	public int getY() {
		return super.y;
	}

	public int getX() {
		return super.x;
	}

	public void tick() {
		y += velocity;
	}

	public void render(Graphics g2d) {
		g2d.drawImage(getBulletImage(), getX(), y, null);

	}

	public Image getBulletImage() {
		return bullet.getImage();
	}

	public String imageURL() {
		return bulletImage;
	}

}
