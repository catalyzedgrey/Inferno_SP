package com.grey.inferno.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.grey.inferno.framework.KeyInput;
import com.grey.inferno.framework.ObjectId;
import com.grey.inferno.framework.STATE;
import com.grey.inferno.framework.Score;
import com.grey.inferno.framework.Texture;


public class Game extends Canvas implements Runnable 
{
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	
	Menu menu;
	
	public static int WIDTH, HEIGHT;
	
	public   BufferedImage level = null, background = null, multlevel = null;;

	//public static LinkedList<Details> detailList = new LinkedList<Details>();
	
	
	Handler handler;
	Camera cam;
	static Texture tex;
	private HUD hud;
	
	private Score score;
	
	public static int LEVEL =1;
	
	public static STATE gameState = STATE.Menu;
	
	public void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();

/*		if(gameState == STATE.Game)
		{*/
			
			BufferedImageLoader loader = new BufferedImageLoader();
			level = loader.loadImage("/level.png");
			multlevel = loader.loadImage("/multlevel.png");
			//background = loader.loadImage("/background.png");
			
			cam = new Camera(0, 0);			
			
			handler = new Handler(this, cam, hud);
			
			hud = new HUD(this, handler);
			
			score = new Score(handler, hud);
			menu = new Menu(this, handler);
			//handler.LoadImageLevel(level);
			
			this.addKeyListener(new KeyInput(this, handler));
			this.addMouseListener(menu);
			//handler.addObject(new Player(100, 100, handler, ObjectId.Player));

			//handler.createLevel();
			
	}
	
	
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() 
	{
		init();
		
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;  //1000000000
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}		
		
	}
	
	private void tick()
	{
		
		handler.tick();
		
		if(gameState==STATE.Game)
		{
			hud.tick();
			for(int i =0; i <handler.object.size();i++)
			{
				if (handler.object.get(i).getId() == ObjectId.Player )//|| handler.object.get(i).getId() == ObjectId.Player2)
					cam.tick(handler.object.get(i));
				
			}
		}
		
		else if(gameState==STATE.Menu) 
		{
			menu.tick();
		}
		else if(gameState == STATE.Multi)
		{
			hud.tick();
			for(int i =0; i <handler.object.size();i++)
			{
				if (handler.object.get(i).getId() == ObjectId.Player )//|| handler.object.get(i).getId() == ObjectId.Player2)
					cam.tick(handler.object.get(i));
				
			}
		}

		
		
		

	}
	
	private void render()
	{
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		////////////////////////////////////////////////
		
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		

		
		
		
		if(gameState == STATE.Game)
		{
			
			g2d.translate(cam.getX(), cam.getY());   //Starts Shooting from here

			//for(int i =0; i<background.getWidth()*100;i+=background.getWidth())
			//g.drawImage(background, i,0, null);
			

			handler.render(g);	
			hud.render(g);
			
			
			//g.dispose();
			bs.show();
			g2d.translate(-cam.getX(), -cam.getY()); // Ends shooting here
		}
		
		else if(gameState==STATE.Menu) 
		{
			menu.render(g);
			//g.dispose();
			bs.show();
		}
		
		else if(gameState == STATE.High)
		{
			menu.render(g);
			//g.dispose();
		}
		
		else if(gameState == STATE.Multi)
		{
			//g2d.translate(cam.getX(), cam.getY());   //Starts Shooting from here

			/*for(int i =0; i<background.getWidth()*100;i+=background.getWidth())
			g.drawImage(background, i,0, null);*/
			

			handler.mulrender(g);	
			hud.render(g);
			
			//g2d.translate(-cam.getX(), -cam.getY());  //
			//g.dispose();
			bs.show();
		}
		
		
		
		
		
		
		///////////////////////////////////////////////
		g.dispose();
		bs.show();

		
	}
	
	
	
	public static float clamp (float var, float min, float max)
	{
		if(var>=max)
			return max;
		else if(var<=min)
			return min;
		else
			return var;
	}
	

	
	public static Texture getInstance()
	{
		return tex;
	}
	

	
	public static void main(String [] args)
	{
		new Window(800, 591, "Inferno", new Game());
		
		
		
	}


	public Score getScore()
	{
		return score;
	}
}
