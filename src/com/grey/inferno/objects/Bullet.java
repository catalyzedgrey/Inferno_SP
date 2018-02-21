package com.grey.inferno.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.BufferedImageLoader;
import com.grey.inferno.window.Game;
import com.grey.inferno.window.Handler;

public class Bullet extends GameObject 
{
	
	private Animation shootFL;
	private Animation shootFR;
	private int key;
	private float tempx;
	public static int fire;

	
	private Animation shootL;
	private Animation shootR;
	
	private BufferedImage sp1, sp2, sp3;
	
	Handler handler;
	
	Texture tex = Game.getInstance();
	
	BufferedImageLoader loader;


	public Bullet(float x, float y, ObjectId id, int velX, int velY, int key, Handler handler) {
		super(x, y, id);
		this.velX = velX;
		this.velY=velY;
		this.key = key;
		this.handler=handler;
		
		if(key ==0x20)
		{
			
			shootFR = new  Animation (5,tex.rightflame[0],tex.rightflame[1],tex.rightflame[2],tex.rightflame[3],tex.rightflame[4], tex.rightflame[5]);
			shootFL = new Animation (5,tex.leftflame[0],tex.leftflame[1],tex.leftflame[2],tex.leftflame[3],tex.leftflame[4], tex.leftflame[5]);
			fire=0;

		}
		else if(key == KeyEvent.VK_ENTER)
		{
			System.out.println("Button Pressed");	
			shootR = new Animation (5,tex.rightbullet[0],tex.rightbullet[1],tex.rightbullet[2],tex.rightbullet[3],tex.rightbullet[4], tex.rightbullet[5]);
			shootL = new Animation (5,tex.leftbullet[0],tex.leftbullet[1],tex.leftbullet[2],tex.leftbullet[3],tex.leftbullet[4], tex.leftbullet[5]);
			fire=1;
		}
		else fire = 2;
		
		loader = new BufferedImageLoader();
	
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
		
		
		for (int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player)
			{
				tempx = tempObject.getX();
			}
		}
		
		
		if(key ==0x20)
		{
			if(this.velX==5)
				{
					shootFR.runAnimation();
					if(x>tempx*5)
						object.remove(this);
				}
			else if(this.velX==-5)
				{
					shootFL.runAnimation();

				}
			//Collision(object);
			
		}
		
		else if(key == KeyEvent.VK_ENTER)
		{
			if(this.velX==5)
				shootR.runAnimation();
			else if(this.velX==-5)
				shootL.runAnimation();
			//Collision(object);
		}
		else
		{
			//shootFL.runAnimation();
			//x+=velX;
			//y+=velY;
		}
		
		if(x<0 || y<0 || y>Game.HEIGHT+5 || x<tempx -150)// || x > Game.WIDTH*2)
			object.remove(this);
		
		
	}

	@Override
	public void render(Graphics g) {

		if(key ==0x20)  //ASCII Space
		{
			
			if(this.velX==5)
				shootFR.drawAnimation(g, (int)x, (int)y, 128, 48);
			else if(this.velX == -5)
				shootFL.drawAnimation(g, (int)x, (int)y-5, 128, 48);
		}
		
		else if(key == KeyEvent.VK_ENTER)
		{
			
			if(this.velX==5)
				shootR.drawAnimation(g, (int)x, (int)y, 128, 48);
			else if(this.velX == -5)
				shootL.drawAnimation(g, (int)x, (int)y-5, 128, 48);
		}
		else if(Game.LEVEL==3)
		{
			sp1 = loader.loadImage("/spike1.png");
			sp2 = loader.loadImage("/spike2.png");
			sp3 = loader.loadImage("/spike3.png");
			switch((int)velY)
			{
			case 0:
				g.drawImage(sp1, (int)x, (int)y, null);
				break;
			case -5:
				g.drawImage(sp2, (int)x, (int)y, null);
				break;
				
			case 5:
				g.drawImage(sp3, (int)x, (int)y, null);
				break;
				
				
			//g.drawRect((int)x, (int)y, 10, 10);
				}
		}
		 
			
	}
	
	
	
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle ((int)x, (int) y, 128, 48);
	}
	
}
