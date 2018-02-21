package com.grey.inferno.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.Game;

public class Flag extends GameObject  
{
	
	
	//private BufferedImage portal = null;
	
	Animation portal;
	
	Texture tex = Game.getInstance();
	

	public Flag(float x, float y, ObjectId id)// int velX, int key) 
	{
		
		super(x, y, id);
/*
		BufferedImageLoader loader = new BufferedImageLoader();
		portal = loader.loadImage("/portal.png");*/
		
		portal = new Animation(5, tex.movingPortal[0],tex.movingPortal[1],tex.movingPortal[2],tex.movingPortal[3] );
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		portal.runAnimation();

	}

	@Override
	public void render(Graphics g) {

		portal.drawAnimation(g, (int)x, (int)y-60);
		//g.drawImage(portal, (int)x, (int)y-30, null);

	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle ((int)x, (int) y, 80, 80);
	}
	
}
