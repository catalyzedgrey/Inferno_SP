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

public class BasicEnemy extends GameObject
{
	private int width = 52, height = 52;
	private Handler handler;
	
	Texture tex = Game.getInstance();

	private Animation enemyWalk;
	private Animation enemyWalkLeft;

	
	private int type; // 0 soldier, 1// flying
	
	//private boolean isDead = false;;
	
	public BasicEnemy(float x, float y, Handler handler, ObjectId id, int type) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		
		if(type ==0) // soldier
		{
			enemyWalk = new Animation (7,tex.enemyarr[1],tex.enemyarr[2],tex.enemyarr[3]);
			
			enemyWalkLeft = new Animation (7,tex.enemyarr[4],tex.enemyarr[5],tex.enemyarr[6]);

		}
		else if (type ==1) //flying
		{
			enemyWalk = new Animation (4, tex.flyingEnemy[0], tex.flyingEnemy[1]);//, tex.flyingEnemy[2], tex.flyingEnemy[3]);
			enemyWalkLeft = new Animation (4, tex.flyingEnemy[2], tex.flyingEnemy[3]);//, tex.flyingEnemy[6], tex.flyingEnemy[7]);
		}
		
		velX=5;
		//velY=5;
		

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x+=velX;
	//	y+=velY;
		
		if(velX<0)
			facing =-1;
		
		else if(velX>0) 
			facing =1;

		if(type==0)
		{
			
			enemyWalk.runAnimation();
			enemyWalkLeft.runAnimation();
			
		}
			
		else if (type ==1)
		{
			/*if(x<=0 || x>=Game.WIDTH-96)
				velX*=-1;*/
			
			enemyWalk.runAnimation();
			enemyWalkLeft.runAnimation();
		}
		
		Collision(object);
		
		//System.out.println(isDead());
	}

	@Override
	public void render(Graphics g) {
		
		if(type ==0)
		{
			if(facing ==1)
			{
				enemyWalk.drawAnimation(g, (int)x, (int)y-20, 52, 52);
			}
			
			else
				enemyWalkLeft.drawAnimation(g, (int)x, (int)y-20, 52, 52);
		}	
		else if(type==1)
		{
			if(facing ==1)
			{
				enemyWalk.drawAnimation(g, (int)x, (int)y-20, 92, 100);
			}
			
		else
			enemyWalkLeft.drawAnimation(g, (int)x, (int)y-20, 92, 100);
		}
		
		else if(type ==3)
		{
			
		}
			
		
	}


	private void Collision(LinkedList <GameObject> object)
	{
		for(int i =0; i <handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ObjectId.Block)
			{	Block b1 = (Block) tempObject;
				
				if(b1.getType()==3)
				{
					if (getBounds().intersects(tempObject.getBounds()))
					{
						velX*=-1;
					}
					//Left
					/*if (getBoundsLeft().intersects(tempObject.getBounds()))
					{
						x = tempObject.getX()+width;
					}*/
				}
				
				/*if (getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY();//+(44);
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
				}
				*/
				//Right
				

			}

			 if(tempObject.getId() == ObjectId.Bullet)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{	
					isDead = true;
					HUD.score+=50;
					handler.removeObject(this);
					handler.removeObject(tempObject);
					//object.remove(this);
					
					
				}
					

			}
	
		}
	}
	
	
	
	public boolean isDead(){
		if(isDead) 
			return true;
		return false;
	}
	

	
	public Rectangle getBounds() 
	{
		return new Rectangle ((int) ((int)x+(width/2)-(width/2)/2), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
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
