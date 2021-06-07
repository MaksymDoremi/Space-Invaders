package com.maks.src;

import java.awt.Toolkit;

import javax.swing.*;


/*
 * Je to napodobování hry Space Invaders
 * Ovládá se to přes W,A,S,D a střílí přes SPACE
 * Letadlo má 10 životů a je možnost pustit jeno 5 nepřátelů na svoje území
 * Když zemře neboli bude hodně nepřátelů = Game Over
 */
public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setTitle("Space Invaders 2077");
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\kinto\\git\\repository\\SpaceInvaders2105\\src\\images\\icon.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new Game());
		frame.setVisible(true);
	}

}
