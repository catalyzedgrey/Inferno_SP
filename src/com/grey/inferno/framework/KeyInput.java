package com.grey.inferno.framework;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.grey.inferno.objects.Bullet;
import com.grey.inferno.window.ChooseWindow;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler;
	//private HUD hud;
	private boolean isShooting = false;
	Save save;
	Game game;
	Details details;
	private boolean[] keyDown = new boolean[4];
	private boolean[] keyDown2 = new boolean[4];
	Graphics g;

	int saveType = 0;

	public KeyInput(Game game, Handler handler) {
		this.handler = handler;
		this.game = game;

		for (int i = 0; i < 4; i++)
			keyDown[i] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_BACK_SPACE) {

			// HUD.level=1;
			Game.LEVEL = 1;

			details = new Details(ChooseWindow.s, HUD.score);
			save = new Save(details);
			try {
				save.tick(details);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// save.deSerialize();

			/*
			 * for (int i = 0; i < handler.object.size(); i++) { //GameObject
			 * tempObject = handler.object.get(i); //handler.clearLevel();
			 * handler.object.clear();
			 * 
			 * 
			 * 
			 * }
			 */
			Game.gameState = STATE.Menu;

			HUD.score = 0;
			HUD.HEALTH1 = 100;
			handler.clearLevel();

		}

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				//Player p1 = (Player) tempObject;
				if (key == KeyEvent.VK_D)// && Player.canMoveRight) {
				{
					tempObject.setVelX(5);
					// HUD.velX=(int)tempObject.getVelX();
					keyDown[2] = true;
					// Player.canMoveLeft = true;
				} //

				if (key == KeyEvent.VK_A)// && Player.canMoveLeft)
				{
					tempObject.setVelX(-5);
					keyDown[3] = true;
					// HUD.velX=(int)tempObject.getVelX();
					// Player.canMoveRight = true;
				}

				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1] = true;
				}
				if ((key == KeyEvent.VK_W && !tempObject.isJumping())) {

					keyDown[0] = true;

					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_SPACE && !isShooting) {
					isShooting = true;
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 12, ObjectId.Bullet,
							tempObject.getFacing() * 5, 0, key, handler));
					// System.out.println("bullet fired");
				}
				
			}

			else if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT)
					{
						tempObject.setVelX(5);
						keyDown2[2] = true;
					}
				if (key == KeyEvent.VK_LEFT)
					{
						tempObject.setVelX(-5);
						keyDown2[3] = true;
					}
				if (key == KeyEvent.VK_DOWN)
					tempObject.setVelY(5);
				if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_ENTER && !isShooting) {
					isShooting=true;
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 12, ObjectId.Bullet,
							tempObject.getFacing() * 5, 0, key, handler));
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					// tempObject.setVelX(0);
					keyDown[2] = false;
				} // {
				if (key == KeyEvent.VK_A) {
					// tempObject.setVelX(0);
					keyDown[3] = false;
				} // {}
				if (key == KeyEvent.VK_SPACE) {
					isShooting = false;
				}
				if (!keyDown[2] && !keyDown[3])
					tempObject.setVelX(0);

			}

			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT)
					{
						tempObject.setVelX(0);
						keyDown2[2] = false;

					}
				if (key == KeyEvent.VK_LEFT)
					{
						tempObject.setVelX(0);
						keyDown2[3] = false;
					}
				if (key == KeyEvent.VK_ENTER) 
				{
					isShooting = false;
				}
				if (!keyDown2[2] && !keyDown2[3])
					tempObject.setVelX(0);
			}

		}

	}

}
