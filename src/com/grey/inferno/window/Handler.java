package com.grey.inferno.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;


import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.STATE;
import com.grey.inferno.objects.BasicEnemy;
import com.grey.inferno.objects.Block;
import com.grey.inferno.objects.Boss2;

import com.grey.inferno.objects.Flag;
import com.grey.inferno.objects.Player;
import com.grey.inferno.objects.Player2;
import com.grey.inferno.objects.Projectiles;

public class Handler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	private BufferedImage level2 = null, level3 = null;
	private BufferedImage background = null;
	private Camera cam;
	private Game game;
	private HUD hud;
	private BufferedImageLoader loader;

	public Handler(Game game, Camera cam, HUD hud) {
		this.game = game;
		this.cam = cam;
		this.hud = hud;

		loader = new BufferedImageLoader();

		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		
	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick(object);//tempObject = object.get(i);

			//tempObject.tick(object);

		}

	}

	public void render(Graphics g) {

		
		/*for (int i = 0; i < object.size(); i++) {
		tempObject = object.get(i);

		tempObject.render(g);
	}*/
		
		
		

		switch (Game.LEVEL) {
		
		case 1:
			//System.out.println(Game.LEVEL);
			background = loader.loadImage("/background.png");
			
			
			for (int ii = 0; ii < background.getWidth() * 100; ii += background.getWidth())
				g.drawImage(background, ii, 0, null);
			

			for (int k = 0; k < object.size(); k++) {
				tempObject = object.get(k);

				tempObject.render(g);
			}


			break;

		case 2:
			background = loader.loadImage("/backgroundlv2.png");
			for (int ii = 0; ii < background.getWidth() * 100; ii += background.getWidth())
				g.drawImage(background, ii, 0, null);

			for (int k = 0; k < object.size(); k++) {
				tempObject = object.get(k);

				tempObject.render(g);
			}

			break;

		case 3:

			background = loader.loadImage("/bglevel3.png");
			for (int iii = 0; iii < background.getWidth() * 100; iii += background.getWidth())
				g.drawImage(background, iii, 0, null);

			for (int k = 0; k < object.size(); k++) {
				tempObject = object.get(k);

				tempObject.render(g);
			}

			break;

		}
		//g.dispose();
	}

	// }

	public void mulrender(Graphics g) {
		
		BufferedImage forest = loader.loadImage("/forest.png");
		for (int iii = 0; iii < forest.getWidth() * 100; iii += forest.getWidth())
		g.drawImage(forest, iii, 0, null);
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void LoadImageLevel(BufferedImage image) {

		int w = image.getWidth();
		int h = image.getHeight();
	
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 & blue == 255) // white pixel -  dirt
					addObject(new Block(i * 32, j * 32, 0, ObjectId.Block));

				if (red == 0 && green == 255 & blue == 255) // cyan - ice
					addObject(new Block(i * 32, j * 32, 1, ObjectId.Block));

				if (red == 255 && green == 106 & blue == 0) // fireball
					addObject(new Block(i * 32, j * 32, 2, ObjectId.Block));

				if (red == 182 && green == 255 & blue == 0) // boundaries
					addObject(new Block(i * 32, j * 32, 3, ObjectId.Block));

				if (red == 72 && green == 0 & blue == 255) // bonfire
					addObject(new Block(i * 32, j * 32, 4, ObjectId.Block));
				
				if (red == 0 && green == 148 & blue == 255) // roof spikes level3
					addObject(new Block(i * 32, j * 32, 5, ObjectId.Block));
				
				if (red == 0 && green == 74 & blue == 127) // right ice tiles level3
					addObject(new Block(i * 32, j * 32, 6, ObjectId.Block));
				
				if (red == 0 && green == 127 & blue == 127) // left ice tiles level3
					addObject(new Block(i * 32, j * 32, 7, ObjectId.Block));
				
				
				//---------------------------------------------------------------------------------------
				if (red == 255 && green == 216 & blue == 0)
					addObject(new Projectiles(i * 32, j * 32, this, ObjectId.Projectile, 1));

				if (red == 127 && green == 0 & blue == 55)
					addObject(new Projectiles(i * 32, j * 32, this, ObjectId.Projectile, 2));
				if (red == 0 && green == 127 & blue == 70)
					addObject(new Projectiles(i * 32, j * 32, this, ObjectId.Projectile, 3));
				//----------------------------------------------------------------------------------------
				if (red == 0 && green == 0 & blue == 255) // player 1
					addObject(new Player(i * 32, j * 32, this, cam, ObjectId.Player, hud));
				//----------------------------------------------------------------------------------------
				if (red == 255 && green == 178 & blue == 229) // Basic Enemy
					addObject(new BasicEnemy(i * 32, j * 32, this, ObjectId.BasicEnemy, 0));
				
				if (red == 127 && green == 0 & blue == 0) // Basic Enemy
					addObject(new BasicEnemy(i * 32, j * 32, this, ObjectId.BasicEnemy, 1));
				//----------------------------------------------------------------------------------------
				/*if (red == 0 && green == 255 & blue == 255) // Boss
					addObject(new Boss(i * 32, j * 32, this, ObjectId.Boss));
*/
				if (red == 33 && green == 0 & blue == 127) // Boss2
						addObject(new Boss2(i * 32, j * 32, this, ObjectId.Boss2));
				//----------------------------------------------------------------------------------------
				if (red == 255 && green == 0 & blue == 0) // Victory Flag
					addObject(new Flag(i * 32, j * 32, ObjectId.Flag));

				if (Game.gameState == STATE.Multi) {
					multLevel(game.multlevel);
				}

			}
		}
	}

	public void multLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 & blue == 255)
					addObject(new Block(i * 32, j * 32, 0, ObjectId.Block));

				if (red == 64 && green == 64 & blue == 64)
					addObject(new Block(i * 32, j * 32, 1, ObjectId.Block));

				if (red == 0 && green == 0 & blue == 255) // player 1
					addObject(new Player(i * 32, j * 32, this, cam, ObjectId.Player, hud));

				if (red == 255 && green == 178 & blue == 229) // Basic Enemy
					addObject(new BasicEnemy(i * 32, j * 32, this, ObjectId.BasicEnemy, 0));

				if (red == 255 && green == 0 & blue == 0) // Victory Flag
					addObject(new Flag(i * 32, j * 32, ObjectId.Flag));

				if (red == 255 && green == 0 & blue == 220) // player 2
				{
					addObject(new Player2(i * 32, j * 32, this, ObjectId.Player2));
					System.out.println("called");
				}
			}

		}
	}

	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		//System.out.println(HUD.level);
		switch (Game.LEVEL) {
		case 2:
			LoadImageLevel(level2);
			break;
		case 3:
			LoadImageLevel(level3);
			break;
		case 4:
			Game.gameState=STATE.Menu;
		default:
			break;

		}


	}

	public void clearLevel() {

		object.clear();
		
		//object.removeAll(object);

	}

	public void addObject(GameObject object) {
		this.object.add(object);

	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}



}
