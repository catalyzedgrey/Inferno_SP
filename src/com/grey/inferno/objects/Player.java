package com.grey.inferno.objects;

//import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.STATE;
import com.grey.inferno.framework.Save;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.Camera;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class Player extends GameObject {

	private float width = 32, height = 44;

	protected float gravity = 0.5f;
	private final float MAX_SPEED = 10;


	HUD hud;
	Save save;
	private boolean saved = false;
	private Handler handler;
	private Camera cam;
	Texture tex = Game.getInstance();

	private Animation playerWalk, playerWalkLeft;

	public Player(float x, float y, Handler handler, Camera cam, ObjectId id, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		this.hud = hud;

		playerWalk = new Animation(5, tex.player[1], tex.player[2], tex.player[3], tex.player[4]);

		playerWalkLeft = new Animation(5, tex.player[5], tex.player[6], tex.player[7], tex.player[8]);

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if (velX < 0)
			facing = -1;

		else if (velX > 0)
			facing = 1;

		if (falling || jumping) {

			velY += gravity;
			Collision(object);
			if (velY > MAX_SPEED)
				velY = MAX_SPEED;

			// Collision(object);

			playerWalk.runAnimation();
			playerWalkLeft.runAnimation();

		}
		
		Game.clamp(HUD.score,0, 1000);

		if (Game.gameState == STATE.Game) {
			if (HUD.HEALTH1 <= 0 || y > Game.HEIGHT + 2 && saved) {

				{
					try{
						isDead = true;

						save.deSerialize();
						this.x = save.getX();
						this.y = save.getY();
						if(HUD.score<0)
							HUD.score=0;
						else if(HUD.score>0)
							{HUD.score-=50; System.out.println(HUD.score);}

						// this.hud=save.getHUD();

						// handler.clearLevel();
						// Game.gameState = STATE.Menu;
						HUD.HEALTH1 = 100;
						Boss2.status = false;
						// System.out.println(isDead + "---------");

						System.out.println(Game.LEVEL + " saved");
					}
					catch (RuntimeException ex)
					{
						isDead = true;
						handler.clearLevel();
						Game.gameState = STATE.Menu;
						HUD.HEALTH1 = 100;
						Game.LEVEL = 1;
						// HUD.level=1;
						Boss2.status = false;

						System.out.println(Game.LEVEL + " !saved properly");
					}
					
				}

			} else if (HUD.HEALTH1 <= 0 || y > Game.HEIGHT + 2 && !saved) {
				isDead = true;
				handler.clearLevel();
				Game.gameState = STATE.Menu;
				HUD.HEALTH1 = 100;
				Game.LEVEL = 1;
				// HUD.level=1;
				Boss2.status = false;

				System.out.println(Game.LEVEL + " !saved");
			}

			/*
			 * if(isDead) { g.setColor(Color.black); g.drawRect(500, 200, 300,
			 * 50); g.fillRect(500, 200, 300, 50);
			 * 
			 * 
			 * }
			 */
		}
		if (Game.gameState == STATE.Multi && HUD.HEALTH1 <= 0 || y > Game.HEIGHT + 2) {
			isDead = true;
			handler.clearLevel();
			Game.gameState = STATE.Menu;
			HUD.HEALTH1 = 100;
			HUD.HEALTH1 = 100;
			Game.LEVEL = 1;
		}
	}

	@Override
	public void render(Graphics g) {
		/*
		 * g.setColor(Color.GRAY); g.drawRect((int)x,(int) y, 32, 50);
		 * g.fillRect((int)x, (int)y, 32, 50);
		 */

		if (velX != 0) {

			if (facing == 1) {
				playerWalk.drawAnimation(g, (int) x, (int) y, 32, 44);
				/*
				 * g2d.setColor(Color.GRAY); g2d.fillRect(90, 15, 200, 16);
				 * g2d.setColor(Color.GREEN); g2d.fillRect(90, 15,
				 * HUD.HEALTH1*2, 16); g2d.setColor(Color.WHITE);
				 * g2d.drawRect(90, 15, 200, 16); g2d.translate(90, 0);
				 */
			}

			else
				playerWalkLeft.drawAnimation(g, (int) x, (int) y, 32, 44);
		}

		else
			g.drawImage(tex.player[0], (int) x, (int) y, null);

		/*
		 * Graphics2D g2d = (Graphics2D) g; g.setColor(Color.RED);
		 * g2d.draw(getBounds()); g2d.draw(getBoundsRight());
		 * g2d.draw(getBoundsLeft()); g2d.draw(getBoundsTop());
		 */

	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block) {

				if (((Block) tempObject).getType() == 0 || ((Block) tempObject).getType() == 1) {

					if (getBoundsTop().intersects(tempObject.getBounds())) {
						y = tempObject.getY() + (44);
						velY = 0;
					}

					if (getBounds().intersects(tempObject.getBounds())) {
						y = tempObject.getY() - height;
						velY = 0;
						falling = false;
						jumping = false;
					} else
						falling = true;

					// Right
					if (getBoundsRight().intersects(tempObject.getBounds())) {

						// velX=0;
						x = tempObject.getX() - width;
						// canMoveRight=false;

						// System.out.println(canMove);
					}
					// Left
					if (getBoundsLeft().intersects(tempObject.getBounds())) {
						x = tempObject.getX() + width;
						// velX=0;
						// canMoveLeft=false;
					}
				}

				else if (((Block) tempObject).getType() == 2) {
					if (getBounds().intersects(tempObject.getBounds())) {
						// isDead = true;
						HUD.HEALTH1 -= 0.5;
						// object.remove(this);
					}
				}

				else if (((Block) tempObject).getType() == 4) {

					if (getBounds().intersects(tempObject.getBounds()) && this.saved != true) {
						this.saved = true;
						// save = new Save(HUD.score, HUD.level, ChooseWindow.s,
						// (int) x, (int) y, handler, hud);
						save = new Save((int) this.x, (int) this.y, hud);
						save.Serialize(save);

						// save.tick();

						/*
						 * details = new Details (ChooseWindow.s, HUD.score,
						 * HUD.level); save = new Save (details); save.tick();
						 */
						// save.deSer();

					}

				}

			}

			else if (tempObject.getId() == ObjectId.Flag) {
				// new level
				if (getBounds().intersects(tempObject.getBounds())) {
					// ++HUD.level;
					++Game.LEVEL;
					handler.switchLevel();

				}
				cam.setX(0);
				// cam.setY(0);
			} else if (tempObject.getId() == ObjectId.Bullet) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					if (Bullet.fire == 1) {
						HUD.HEALTH1 -= 0.5;
						System.out.println(HUD.HEALTH1);
						// hit = true;
					} else if (Bullet.fire == 2) {
						HUD.HEALTH1 -= 2.0f;
					}
				}

				else if (getBounds().intersects(tempObject.getBounds())) {
					if (Bullet.fire == 1) {
						HUD.HEALTH1 -= 0.00000005;
						// hit = true;
					}
				}
			}

			// Right

			else if (tempObject.getId() == ObjectId.BasicEnemy) {
				if ((getBounds().intersects(tempObject.getBounds()))
						|| (getBounds().intersects(tempObject.getBounds())))
				// ||(getBoundsRight().intersects(tempObject.getBounds()))
				// ||(getBoundsLeft().intersects(tempObject.getBounds())));
				{
					HUD.HEALTH1 -= 1.5f;
					System.out.println("hit!");
				}

				/*
				 * else if (tempObject.getId() == ObjectId.Projectile) {
				 * 
				 * Projectiles temp = (Projectiles)tempObject;
				 * 
				 * if((getBounds().intersects(tempObject.getBounds())))
				 * 
				 * { HUD.HEALTH1 -= 0.75f; }
				 * 
				 * 
				 * else if(getBoundsTop().intersects(tempObject.getBoundsTop()))
				 * { HUD.HEALTH1-=0.75; }
				 * 
				 * }
				 */

				// hit = false;
			}
		}
	}

	@Override
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

	public void initLoad() {
		// save.deSerialize(save);

		x = save.getX();
		y = save.getY();
		/*
		 * handler = save.getHandler(); cam = save.getCam(); hud =
		 * save.getHUD();
		 */

		handler.addObject(new Player(x + 5, y, this.handler, this.cam, ObjectId.Player, this.hud));

		System.out.println(x + " - " + y);

		HUD.HEALTH1 += 100;
		isDead = false;
	}

	public void setLoc(float x, float y) {
		this.x = x;
		this.y = y;
	}

}
