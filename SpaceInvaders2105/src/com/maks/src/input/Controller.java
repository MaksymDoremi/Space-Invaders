package com.maks.src.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Timer;

import com.maks.src.Game;
import com.maks.src.objects.Bullet;
import com.maks.src.objects.Enemy;
import com.maks.src.objects.Ship;

public class Controller {
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	LinkedList<Bullet> bullets = new LinkedList<Bullet>();

	Enemy TempEnemy;
	Bullet bullet;
	Ship ship;

	Random rnd;

	private int score = 0;
	private int lives = 5; // ONLY 5 ENEMY CAN REACH THE BORDER, OR YOU LOSE

	public int getScore() {
		return score;
	}
	
	public int getLives() {
		return lives;
	}

	public Controller() {

		rnd = new Random();

		addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT), 1));
		addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT), 1));
		addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT), 1));
		addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT), 1));
		addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT), 1));

	}

	// ENEMY DRAW
	public void draw(Graphics g2d) {
		for (int i = 0; i < enemies.size(); i++) {
			TempEnemy = enemies.get(i);

			TempEnemy.draw(g2d);
		}

	}

	// ENEMY MOVE
	public void update() {
		for (int i = 0; i < enemies.size(); i++) {
			TempEnemy = enemies.get(i);

			// REMOVE ENEMY IF TOUCHES BORDER
			if (TempEnemy.getY() > Game.HEIGHT - TempEnemy.getEnemyImage().getHeight(null)) {
				removeEnemy(TempEnemy);
				lives--;
			}

			TempEnemy.update();

			if (rnd.nextInt(180) == 1) {
				addBullet(new Bullet(TempEnemy.getX() + TempEnemy.getEnemyImage().getWidth(null) / 2,
						TempEnemy.getY() + TempEnemy.getEnemyImage().getHeight(null), 4, "/images/bulletEnemy.png"));
			}

		}

		if (enemies.size() == 0) {
			addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT) - 80, 1));
			addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT) - 80, 1));
			addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT) - 80, 1));
			addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT) - 80, 1));
			addEnemy(new Enemy(rnd.nextInt(Game.WIDTH - 90), -rnd.nextInt(Game.HEIGHT) - 80, 1));
		}

	}

	// BULLET DRAW
	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);

			bullet.render(g);
		}
	}

	// BULLET MOVE
	public void tick() {
		for (int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);

			if (bullet.getY() <= 10 || bullet.getY() >= Game.HEIGHT - 70) {
				removeBullet(bullet);
			}

			bullet.tick();
		}
	}

	// COLLISION FOR KILLING AN ENEMY
	public void killEnemy() {
		for (int i = 0; i < enemies.size(); i++) {
			TempEnemy = enemies.get(i);

			for (int j = 0; j < bullets.size(); j++) {
				bullet = bullets.get(j);

				if (bullet.getY() < TempEnemy.getY() + TempEnemy.getEnemyImage().getHeight(null)
						&& bullet.getX() >= TempEnemy.getX()
						&& bullet.getX() < TempEnemy.getX() + TempEnemy.getEnemyImage().getWidth(null)
						&& bullet.imageURL() != "/images/bulletEnemy.pngaa") {
					removeEnemy(TempEnemy);
					removeBullet(bullet);
					score++;
				}

			}

		}

	}

	// COLLISION FOR HURTING PLAYER SHIP
	public void hurt(Ship ship) {
		for (int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);
			if (bullets.get(i).imageURL() == "/images/bulletEnemy.png") {
				if (bullet.getX() >= ship.getX() && bullet.getX() <= ship.getX() + ship.getShipImage().getWidth(null)
						&& bullet.getY() > ship.getY()) {
					ship.health -= 1;
					removeBullet(bullet);
				}
			}
		}

	}

	// COLLISION FOR CRASHING ENEMIES BY SHIP
	public void crush(Ship ship) {
		Rectangle enemyRect = new Rectangle();
		Rectangle shipRect = new Rectangle(ship.getX(), ship.getY(), ship.getShipImage().getWidth(null),
				ship.getShipImage().getHeight(null));
		for (int i = 0; i < enemies.size(); i++) {
			TempEnemy = enemies.get(i);
			enemyRect = new Rectangle(TempEnemy.getX(), TempEnemy.getY(), TempEnemy.getEnemyImage().getWidth(null),
					TempEnemy.getEnemyImage().getHeight(null));

			if (enemyRect.intersects(shipRect)) {
				ship.health -= 1;
				removeEnemy(TempEnemy);
			}
		}
	}

	// IF HEALTH == 0 ===> Game Over
	public void gameOver(Ship ship, Graphics g, Timer timer) {
		Graphics2D g2d = (Graphics2D) g;

		if (ship.health <= 0 || lives == 0) {
			ship = null;
			enemies.clear();
			bullets.clear();
			timer.stop();

			g2d.setFont(new Font("Stencil", Font.PLAIN, 60));
			g2d.setPaint(Color.white);
			g2d.drawString("You lost", 180, 325);
		}
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}
}
