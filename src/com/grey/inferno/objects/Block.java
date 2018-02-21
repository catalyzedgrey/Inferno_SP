package com.grey.inferno.objects;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.grey.inferno.framework.GameObject;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.Texture;
import com.grey.inferno.window.Animation;
import com.grey.inferno.window.BufferedImageLoader;
import com.grey.inferno.window.Game;

public class Block extends GameObject 
{
	
	private int type;
	private Animation lavAnim, campAnim;
	Texture tex = Game.getInstance();
	private BufferedImage icetile = null, rightice = null, leftice = null;
	private BufferedImageLoader loader;
	public Block(float x, float y, int type, ObjectId id) 
	{
		super(x, y, id);
		this.type = type;
		lavAnim = new Animation(5, tex.block[2], tex.block[3], tex.block[4]);//, tex.block[5], tex.block[6], tex.block[7]);
		campAnim = new Animation (5, tex.bonfire[0], tex.bonfire[1], tex.bonfire[2], tex.bonfire[3], tex.bonfire[4], tex.bonfire[5], tex.bonfire[6], tex.bonfire[7], tex.bonfire[8] );
		loader = new BufferedImageLoader();
		icetile = loader.loadImage("/icetile.png");
		rightice = loader.loadImage("/rightice.png");
		leftice = loader.loadImage("/leftice.png");
	}

	@Override
	public void tick(LinkedList<GameObject> object) 
	{
	
		lavAnim.runAnimation();
		campAnim.runAnimation();
	}

	@Override
	public void render(Graphics g)
	{
		/*g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 64);*/
		
		
		switch(type)
		{
		case 0:		//dirt
			g.drawImage(tex.block[0], (int)x, (int)y, null);
			break;
		case 1:		//ice
			g.drawImage(icetile, (int)x, (int)y, null);
			break;
		case 2:		//lava
			lavAnim.drawAnimation(g, (int) x, (int) y, 32, 32);
			break;
		case 3:		//enemy boundaries
			/*g.drawRect((int)x, (int)y, 32, 32);
			g.setColor(new Color(182, 25,0));
			g.fillRect((int)x,(int) y, 32, 32);*/
			break;
		case 4:
			/*g.drawRect((int)x, (int)y, 32, 32);
			g.setColor(new Color(72, 0,255));
			g.fillRect((int)x,(int) y, 32, 32);*/
			
			campAnim.drawAnimation(g, (int)x, (int)y-20, 43,59);
			break;
		case 5:
			g.drawImage(tex.icicarr[0], (int) x-5, (int)y-7, null);
			break;
		case 6:
			g.drawImage(rightice, (int) x, (int)y, null);
			break;
		case 7:
			g.drawImage(leftice, (int) x, (int)y, null);
			break;
		}
		
		
	}
	
	
	public int getType()
	{
		return type;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 44);
	}

	

}
