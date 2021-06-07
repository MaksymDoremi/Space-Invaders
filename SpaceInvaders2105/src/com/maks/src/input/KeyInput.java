package com.maks.src.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.maks.src.Game;
import com.maks.src.objects.*;

public class KeyInput extends KeyAdapter {

	Ship ship;
	Game game;

	public KeyInput(Ship s, Game game) {
		this.ship = s;
		
		//STRELBA NABOJU S HRACOVSKEHO LETADLA
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		ship.keyPressed(e);
		game.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		ship.keyReleased(e);
	}
}
