package com.grey.inferno.window;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ChooseWindow extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private Container c;
	private JLabel label;
	public static String s;
	@SuppressWarnings("unused")
	private boolean entered = false;
	
	public ChooseWindow() throws IOException{
		frame = new JFrame();
		frame.setBounds(0, 0, 300, 100);
	
		txt = new JTextField(10);
		c = frame.getContentPane();
		btn = new JButton("Enter");
		label = new JLabel("Enter Name:");
		frame.setLayout(new BorderLayout());
	    this.pack();
		
	    
		init();
		frame.setVisible(true);
	}
	
	public void init(){
	
		c.add(label, BorderLayout.WEST);
		c.add(txt, BorderLayout.CENTER);
		c.add(btn, BorderLayout.EAST);
		txt.setEditable(true);
		btn.addActionListener(this);
		s = txt.getText();
	}				
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		btn = (JButton)e.getSource();
		s = txt.getText();
		System.out.println(s);
		//txt.setText("");
		entered = true;
		frame.setVisible(false);
		
	}
	
		
	public String getName()
	{
		return s;
	}

		

		
}


