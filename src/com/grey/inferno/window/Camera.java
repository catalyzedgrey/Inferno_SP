package com.grey.inferno.window;


import com.grey.inferno.framework.GameObject;

public class Camera
{
	private float x, y;

	
	public Camera(float x, float y)
	{
		this.x = x; 
		this.y = y;

	}

	public void tick(GameObject player) {
		
		
		
		x=-player.getX() + Game.WIDTH/2;
		HUD.velX = (int) player.getX();
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}


	



}
