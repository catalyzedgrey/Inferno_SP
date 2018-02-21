package com.grey.inferno.objects;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.STATE;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class Player2 extends GameObject 
{
	
	
	
	private float width = 32, height = 44;
	
	protected float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	
	
	private Handler handler;

	Texture tex = Game.getInstance();

	private Animation player2Walk, player2WalkLeft;
	
	
	public Player2(float x, float y, Handler handler, ObjectId id) 
	{
		super(x, y, id);
		this.handler = handler;
		
		player2Walk = new Animation (5,tex.player2[1],tex.player2[2],tex.player2[3],tex.player2[4] );
		
		player2WalkLeft = new Animation (5,tex.player2[5],tex.player2[6],tex.player2[7],tex.player2[8] );

	}

	@Override
	public void tick(LinkedList<GameObject> object) 
	{
		x+=velX;
		y+=velY;
		
		if(velX<0)facing =-1;
		
		else if(velX>0) facing =1;
		
			if(falling || jumping)
			{
					
			velY+=gravity;
			
			if(velY>MAX_SPEED)
				velY = MAX_SPEED;	


		Collision(object);
		
		player2Walk.runAnimation();
		player2WalkLeft.runAnimation();
		
		}
			if(HUD.HEALTH2 <= 0 || y > Game.HEIGHT + 2)
			{
				isDead = true;
				handler.clearLevel();
				Game.gameState = STATE.Menu;
				HUD.HEALTH1 = 100;
				Game.LEVEL=1;
				//HUD.level=1;
			}

	}
	

	@Override
	public void render(Graphics g)
	{
		
		//g.setColor(Color.GRAY);
		
		if(velX !=0)
		{
			if(facing ==1)
			player2Walk.drawAnimation(g, (int)x, (int)y, 48, 44);
			else
				player2WalkLeft.drawAnimation(g, (int)x, (int)y, 48, 44);
		}
		else
			g.drawImage(tex.player2[0], (int)x, (int)y, null);
		
		/*Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());*/
		
	}
	
	private void Collision(LinkedList <GameObject> object)
	{
		for(int i =0; i <handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY()+(44);
					velY=0;
				}
					

				if (getBounds().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY()-height;
					velY=0;
					falling = false;
					jumping = false;
				}else
					falling = true;
				
				//Right
				if (getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX()-width;
				}
				//Left
				if (getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX()+width;
				}
				
				

			}	
			
		
			else if(tempObject.getId() == ObjectId.Bullet)
			{
				if (getBoundsTop().intersects(tempObject.getBounds()))
				{	if(Bullet.fire==0)
					{
						HUD.HEALTH2-=0.5;

					}
				}
					

				else if (getBounds().intersects(tempObject.getBounds()))
				{	if(Bullet.fire==0)
					{
						HUD.HEALTH2-=0.5;

					}
				}
				

			}
	
		}
	}





	

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle((int) ((int)x+(width/2)-(width/2)/2), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() 
	{
		return new Rectangle((int) ((int)x+(width/2)-(width/2)/2), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() 
	{
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() 
	{
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	

}
