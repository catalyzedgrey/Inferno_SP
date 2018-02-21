package com.grey.inferno.framework;

import java.io.Serializable;

import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;

public class Score implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Handler handler;
	@SuppressWarnings("unused")
	private HUD hud;
	
	public Score (Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud=hud;
	}
	
	
	
	
}
