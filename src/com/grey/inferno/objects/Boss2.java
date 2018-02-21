package com.grey.inferno.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.Timer;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.BufferedImageLoader;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class Boss2 extends GameObject implements ActionListener {
	private int width = 176, height = 128;
	private Handler handler;

	Texture tex = Game.getInstance();

	private Bullet b1, b2, b3;
	private BufferedImage chimera = null;
	Timer timer;
	public static boolean status = true;
	// private boolean isDead = false;;
	ActionListener fireBullet;

	public Boss2(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		velX = 3;
		velY = 8;
		
		chimera = tex.chimeraarr[0];
		
		/*BufferedImageLoader loader = new BufferedImageLoader();
		chimera = loader.loadImage("/chimera.gif");*/
		System.out.println(Game.LEVEL + "----");

		/*
		 * fireBullet = new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { b1 = new
		 * Bullet(x, y, ObjectId.Bullet, -3, 1, handler);
		 * 
		 * g2.fillRect((int) x, (int) y, 20, 20);
		 * 
		 * 
		 * } };
		 */

		/*
		 * breathe = new Animation(7, tex.bossarr[0], tex.bossarr[1],
		 * tex.bossarr[2], tex.bossarr[3], tex.bossarr[4], tex.bossarr[5],
		 * tex.bossarr[6], tex.bossarr[7], tex.bossarr[8]);//, tex.bossarr[9],
		 * tex.bossarr[10]);
		 */

		/*
		 * if(type ==0) // soldier { enemyWalk = new Animation
		 * (7,tex.enemyarr[1],tex.enemyarr[2],tex.enemyarr[3]);
		 * 
		 * enemyWalkLeft = new Animation
		 * (7,tex.enemyarr[4],tex.enemyarr[5],tex.enemyarr[6]);
		 * 
		 * } else if (type ==1) //flying { enemyWalk = new Animation (4,
		 * tex.flyingEnemy[0], tex.flyingEnemy[1]);//, tex.flyingEnemy[2],
		 * tex.flyingEnemy[3]); enemyWalkLeft = new Animation (4,
		 * tex.flyingEnemy[2], tex.flyingEnemy[3]);//, tex.flyingEnemy[6],
		 * tex.flyingEnemy[7]); }
		 * 
		 * velX=5; //velY=5;
		 */
		// status = true;
		temp();

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;

		y += velY;
		if (y <= 100 || y >= Game.HEIGHT - 25)
			velY *= -1;

		if (x >= 1480)
			velX = 0;
		
		if(HUD.HEALTH3<=0)
			{
				object.remove(this);
				timer.stop();
			}

		Collision(object);

	}

	@Override
	public void render(Graphics g) {


		// breathe.drawAnimation(g, (int)x, (int)y);

		g.drawImage(chimera, (int) x, (int) y - 80, null);

		g.setColor(Color.GRAY);

		g.fillRect((int) x, (int) y - 100, 400, 16);

		g.setColor(Color.BLUE);

		g.fillRect((int) x, (int) y - 100, (int) HUD.HEALTH3 * 4, 16);

		g.setColor(Color.WHITE);

		g.drawRect((int) x, (int) y - 100, 400, 16);

	}

	public void temp() {
		System.out.println("called -------------------");

		timer = new Timer(1500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				b1 = (new Bullet(x, y, ObjectId.Bullet, -5, 3, 4, handler));
				;
				b2 = (new Bullet(x, y, ObjectId.Bullet, -5, -3, 4, handler));
				b3 = (new Bullet(x, y, ObjectId.Bullet, -5, 0, 4, handler));

				handler.addObject(b1);
				handler.addObject(b2);
				handler.addObject(b3);

				/*
				 * handler.addObject(new Bullet(x, y, ObjectId.Bullet, -5, 5, 1,
				 * handler)); handler.addObject(new Bullet(x, y,
				 * ObjectId.Bullet, -5, -5, 1, handler)); handler.addObject(new
				 * Bullet(x, y, ObjectId.Bullet, -5, 0, 1, handler));
				 */

			}
		});
		timer.setInitialDelay(900);

		timer.start();

	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId()==ObjectId.Bullet)
			{
				if(Bullet.fire==0)
				{
					if(getBounds().intersects(tempObject.getBounds()))
						HUD.HEALTH3-=1.5F;
				}
			}


		}
	}

	public void remove() {
		System.out.println("called");
		handler.removeObject(b1);
		
		handler.removeObject(b2);
		
		handler.removeObject(b3);
		
		timer.restart();
		timer.stop();

	}

	public boolean isDead() {
		if (isDead)
			return true;
		return false;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
