package com.grey.inferno.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.grey.inferno.framework.STATE;

public class HUD extends Canvas  implements Serializable{

	private static final long serialVersionUID = 1L;
	private float x;
	public static int velX;
	public static float HEALTH1 = 100, HEALTH2 = 100, HEALTH3 = 100;

	public static int score = 0;
	//public static int level = 1;
	@SuppressWarnings("unused")
	private Game game;
	private Graphics g2;
	@SuppressWarnings("unused")
	private Handler handler;

	public HUD(Game game, Handler handler) {

		this.game = game;
	}

	public void tick() {
		HEALTH1 = Game.clamp(HEALTH1, 0, 100);

		HEALTH2 = Game.clamp(HEALTH2, 0, 100);
		
		HEALTH3 = Game.clamp(HEALTH3, 0, 100);

		x = velX;

	}

	public void render(Graphics g) {

		if (Game.gameState == STATE.Game) {
			g.setColor(Color.GRAY);

			g.fillRect((int) x + 20, 30, 200, 16);

			g.setColor(Color.GREEN);

			g.fillRect((int) x + 20, 30, (int) HEALTH1 * 2, 16);

			g.setColor(Color.WHITE);

			g.drawRect((int) x + 20, 30, 200, 16);

			g.drawString(ChooseWindow.s, (int) x + 20, 62);
			g.drawString("Score: " + score, (int) x + 20, 74);
			g.drawString("Level: " + Game.LEVEL, (int) x + 20, 86);

		}

		else if (Game.gameState == STATE.Multi) {

			g2 = g;

			g.setColor(Color.GRAY);

			g.fillRect((int) x + 20, 30, 200, 16);

			g.setColor(Color.GREEN);

			g.fillRect((int) x + 20, 30, (int) HEALTH1 * 2, 16);

			g.setColor(Color.WHITE);

			g.drawRect((int) x + 20, 30, 200, 16);

			/*
			 * g.drawString(ChooseWindow.s, (int)x+20, 62); g.drawString(
			 * "Score: " + score, (int)x+20, 74); g.drawString("Level: " +
			 * level, (int)x+20, 86);
			 */

			g2.setColor(Color.GRAY);
			g2.fillRect(600, 15, 200, 16);
			g2.setColor(Color.CYAN);
			g2.fillRect(600, 15, (int) HEALTH2 * 2, 16);
			g2.setColor(Color.WHITE);
			g2.drawRect(600, 15, 200, 16);

		}

		g.dispose();

	}

	/*public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;

	}*/

}
