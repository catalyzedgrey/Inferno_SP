package com.grey.inferno.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.grey.inferno.framework.STATE;
import com.grey.inferno.framework.Save;
import com.grey.inferno.framework.Texture;

public class Menu extends MouseAdapter {

	BufferedImage image = null;
	BufferedImage single = null;
	BufferedImage multi = null;
	BufferedImage hall = null;
	BufferedImage quit = null;
	BufferedImage temphell = null;
	BufferedImage lava = null;

	Animation movingLava;
	Texture tex = Game.getInstance();

	Camera cam;
	private Game game;
	private Handler handler;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;

		movingLava = new Animation(7, tex.movingLava[0], tex.movingLava[1], tex.movingLava[2], tex.movingLava[3],
				tex.movingLava[4], tex.movingLava[5], tex.movingLava[6], tex.movingLava[7]);

		BufferedImageLoader loader = new BufferedImageLoader();

		image = loader.loadImage("/menu.png");
		single = loader.loadImage("/single.png");
		multi = loader.loadImage("/multi.png");
		hall = loader.loadImage("/hall.png");
		quit = loader.loadImage("/quit.png");
		temphell = loader.loadImage("/temphell.png");

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.gameState == STATE.Menu) 
		{
			if ((mouseOver(mx, my, 280, 100, 300, 50) && Game.gameState == STATE.Menu)) 
			{
				Game.gameState = STATE.Game;
				handler.LoadImageLevel(game.level);
			}

			if ((mouseOver(mx, my, 280, 210, 300, 50) && Game.gameState == STATE.Menu)) {
				Game.gameState = STATE.Multi;
				handler.multLevel(game.multlevel);
			}
			
			if ((mouseOver(mx, my, 280, 320, 300, 50) && Game.gameState == STATE.Menu)) 
			{
				Game.gameState = STATE.High;
				
			}

			if ((mouseOver(mx, my, 280, 430, 300, 50) && Game.gameState == STATE.Menu)) {
				System.exit(1);
			}
		}

	}

	public void mousereleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height)
				return true;
			return false;
		} else
			return false;
	}

	public void tick() {
		movingLava.runAnimation();
	}

	public void render(Graphics g) {
		if (Game.gameState == STATE.Menu) {

			movingLava.drawAnimation(g, (int) 0, (int) 0);

			g.setColor(Color.DARK_GRAY);
			// g.drawImage(image,0,0,null);

			g.drawRect(280, 100, 300, 50); // single player
			g.drawImage(single, 285, 100, null);

			g.drawRect(280, 210, 300, 50); // multi player
			g.drawImage(multi, 285, 210, null);

			g.drawRect(280, 320, 300, 50); // high score
			g.drawImage(hall, 285, 320, null);

			g.drawRect(280, 430, 300, 50); // quit
			g.drawImage(quit, 285, 430, null);

		}

		else if (Game.gameState == STATE.Multi) {
			handler.multLevel(game.level);
		}
		
		else if (Game.gameState == STATE.High) {
			
			
			//g.setColor(Color.white);
			//g.fillRect(0, 0, 826, 610);
			g.drawImage(temphell, 0, 0, null);
			
			try {
				Save.render(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
