package com.maks.src;

import java.awt.Toolkit;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setTitle("Space Invaders 2077");
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\kinto\\JavaDev\\SpaceInvaders2105\\src\\images\\icon.png"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new Game());
		frame.setVisible(true);
	}

}
