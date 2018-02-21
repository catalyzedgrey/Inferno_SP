package com.grey.inferno.window;

import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;


import javax.swing.JFrame;



public class Window 
{
	ChooseWindow window;
	
	@SuppressWarnings("unused")
	public Window(int w, int h, String title, Game game)
	{
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		
		
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Container c = frame.getContentPane();
		

		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try {
			window = new ChooseWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (window.getName()!=null)
			game.start();									//System.out.println(window.s);
		
		

	}

}
