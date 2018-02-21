package com.grey.inferno.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class Projectiles extends GameObject {

	int width = 40, height = 93;
	int width2 = 79, height2 = 186;
	Handler handler;
	ObjectId id;
	Texture tex = Game.getInstance();
	private int type = 1;
	private Animation ice;
	private Player player;

	public Projectiles(float x, float y, Handler handler, ObjectId id, int type) {

		super(x, y, id);

		this.handler = handler;

		this.type = type;

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				player = (Player) tempObject;
				System.out.println(player.getX());
			}

		}

		if (type == 1) {
			velY = 5;
		}

		/*else if (type == 2) {

			
			ice = new Animation(3, tex.icearr[0], tex.icearr[1], tex.icearr[2], tex.icearr[3], tex.icearr[4],
					tex.icearr[5], tex.icearr[7], tex.icearr[8], tex.icearr[9], tex.icearr[10], tex.icearr[11],
					tex.icearr[12], tex.icearr[13], tex.icearr[14], tex.icearr[15], tex.icearr[16], tex.icearr[17],
					tex.icearr[18], tex.icearr[19], tex.icearr[20], tex.icearr[21], tex.icearr[22], tex.icearr[23],
					tex.icearr[24], tex.icearr[25], tex.icearr[26], tex.icearr[27], tex.icearr[28], tex.icearr[29],
					tex.icearr[30], tex.icearr[31], tex.icearr[32], tex.icearr[33], tex.icearr[34], tex.icearr[35],
					tex.icearr[36], tex.icearr[37], tex.icearr[38], tex.icearr[39], tex.icearr[40], tex.icearr[41],
					tex.icearr[42], tex.icearr[43], tex.icearr[44], tex.icearr[45], tex.icearr[46], tex.icearr[47],
					tex.icearr[48], tex.icearr[49], tex.icearr[50], tex.icearr[51]);

			
			  for(int i =0; i < 52;i++) { ice = new Animation(5,tex.icearr[i]); }
			 
		}*/
		else if (type == 3) {

			ice = new Animation(5, tex.icicarr[0], tex.icicarr[1], tex.icicarr[2], tex.icicarr[3], tex.icicarr[4],
					tex.icicarr[5]);

			// velX=player.getX();
			// velY=player.getY();
			velY = 3;
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		/*
		 * x+=velX; y+=velY;
		 */

		
		
		switch(type)
		{
		case 1:
			x += velX;
			y += velY;
			if (y <= 0 || y >= Game.HEIGHT)
				velY *= -1;
			break;
			
		case 2:
			float diffX = this.x - player.getX();
			float diffY = this.x - player.getY();

			// float distance = (float)Math.sqrt((Math.pow((x-player.getX()),
			// 2))+ (Math.pow(y-player.getY(), 2)));
			float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
			velX = (float) ((-1.0 / distance) * diffX);
			velY = (float) ((1.0 / distance) * diffY);
			x += velX;
			y += velY;

			ice.runAnimation();
			break;
			
		case 3:
			
			x += velX;
			y += velY;
			
			ice.runAnimation();
			
			
			
			break;
		}
		
		


		Collision(object);
		
	}



	public void render(Graphics g) {
		if (type == 1 && Game.LEVEL!=3) {
			if (velY < 0)
				g.drawImage(tex.fireball[0], (int) x, (int) y, null);
			if (velY > 0)
				g.drawImage(tex.fireball[1], (int) x, (int) y, null);
			
			
		} 
		else if(type == 1 && Game.LEVEL==3)
		{
			if (velY < 0)
				g.drawImage(tex.spikearr[0], (int) x, (int) y, null);
			if (velY > 0)
				g.drawImage(tex.spikearr[1], (int) x, (int) y, null);
		}
		
		/*else if (type == 2) {

			ice.drawAnimation(g, (int) x, (int) y);
		} else if (type == 3)
			 {
				g.drawImage(tex.icicarr[0], (int)x, (int)y, 46, 37, null);
				g2=g;
			 }*/
		//	ice.drawAnimation(g, (int) x, (int) y);
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Block)
			{	Block b1 = (Block) tempObject;
				
				if(b1.getType()==1)
				{
					if (getBounds().intersects(tempObject.getBounds()))
					{
						//ice.runAnimation();
						//ice.drawAnimation(g2, (int)x, (int)y);
						//object.remove(this);
					}
					
				}

			}
			
			
			else if(tempObject.getId() == ObjectId.Player)
			{
				Player p = (Player) tempObject;
				
				if(Game.LEVEL!=3)
				{
					if((getBounds().intersects(p.getBounds()))
							|| (getBoundsTop().intersects(p.getBounds()))
							//||
							//||
							)
						
					{
						HUD.HEALTH1 -= 0.75f;
					}
				}
				else if(Game.LEVEL==3)
				{
					if((getBounds2().intersects(p.getBounds()))
							|| (getBoundsTop().intersects(p.getBounds())))
					{
						HUD.HEALTH1-=1.2;
					}
				}
				
				
				
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return (new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2));
	}
	public Rectangle getBounds2() {
		return (new Rectangle((int) ((int) x + (width2 / 2) - (width2 / 2) / 2), (int) ((int) y + (height2 / 2)),
				(int) width2 / 2, (int) height2 / 2));
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

}
