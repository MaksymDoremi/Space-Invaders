package com.maks.src.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.maks.src.GlobalPosition;

public class Enemy extends GlobalPosition {
	private String enemyImage = "/images/susenemy.png";
	private ImageIcon enemy = new ImageIcon(getClass().getResource(enemyImage));

	int velY = 0;
	Enemy TempEnemy;

	public int getY() {
		return Enemy.super.y;
	}

	public int getX() {
		return Enemy.super.x;
	}

	public Enemy(int x, int y, int velY) {
		super(x, y);
		this.velY = velY;
	}

	public void update() {
		y += velY;
	}

	public void draw(Graphics g2d) {
		g2d.drawImage(getEnemyImage(), getX(), y, null);
	}

	public Image getEnemyImage() {
		return enemy.getImage();
	}

}
