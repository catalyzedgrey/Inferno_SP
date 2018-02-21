package com.grey.inferno.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

import com.grey.inferno.window.Camera;
import com.grey.inferno.window.HUD;
import com.grey.inferno.window.Handler;



public class Save implements Serializable {

	private static final long serialVersionUID = 1L;


	//private HallofFame hall;
	
	
	private Handler handler;
	private Camera cam;
	private HUD hud;
	@SuppressWarnings("unused")
	private Details details;
	//private int [] scoresList = new int[3];
	int score, level, x, y;
	//public LinkedList<Details> detList = new LinkedList<Details>();
	//private Save save;
	//private String name;
	
	private Save tempSave;

	
	File f;
	
	

	
	public Save(int x, int y, HUD hud) {
		this.x=x;
		this.y=y;
		this.hud = hud;

	}
	
	public Save (Details details)
	{
		this.details = details;
	}
	
	
/*	public Save(int score, int level, String name, int x, int y, Handler handler, HUD hud) {
		this.x=x;
		this.y=y;
		
		this.handler=handler;
		this.hud=hud;

		

	}*/
	

	
	
	
	
	
	public void tick(Details deets) throws Exception
	{
		
		
		
		
		PrintStream output = new PrintStream(new FileOutputStream("sss.txt", true)); 
		String s = deets.toString();
		//output.println(deets);
		output.append(s);
		output.println();
		output.flush();
		output.close();
		
		

		//Serialize();

		//hall = new HallofFame(save);
		
	/*	SortLinkedList.sort(Game.detailList, deets);
		SortLinkedList.printList(Game.detailList);
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter("/score.txt", true));
			
			for(int i =0; i <Game.detailList.size();i++){
				
				
				
				out.println(Game.detailList);
				Sort.sort();
				out.close();
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//SortLinkedList.printList(detList);
		
		//SerializeHall();
		
	//	hall.deSerialize();
		
		
	}
	
	
	public static void render(Graphics g) throws FileNotFoundException
	{
		for(int i =0; i <800;i+=133)
		{
			for(int j =0; j <600;j+=35)
			{
				g.setColor(Color.white);
				g.drawRect(i, j, 133, 35);
				
				
				
				//g.drawString(, x, y);
			}
		}
		

		try {
			Sort.sort();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File file = new File("ss.txt");
		BufferedReader br = new BufferedReader (new FileReader(file));
		String line;

		try {
			
			for(int i =0; i <800;i+=133)
			{
				for(int j =0; j <600;j+=35)
				{
			
					if((line = br.readLine()) != null){
						//g.setColor(Color.blue);
					//	g.drawRect(i, j, 20, 20);
						
						
						g.drawString(line, i+5, j+15);
					}

				}
			}
				
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		/*try {
			Scanner input = new Scanner(new File("sss.txt"));
			
			while (input.hasNextLine())
			{
				for(int i =0; i <411;i+=137)
				{
					for(int j =0; j <600;j+=35)
					{
						String s = input.nextLine();
						g.drawString(s, i, j);
						

					}
				}
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/*for(int i =0; i<Game.detailList.size();i++)
		{
			g.drawString(Game.detailList.get(i).name, 137, 35);
		}*/
			
		
		
	}
	
	
/*	public void SerializeHall()
	{
		try {
			FileOutputStream fileOut = new FileOutputStream("/hall.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(details);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /hall.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		
		
	}*/
	

	public void Serialize(Save tsave) {
		try {
			FileOutputStream fileOut = new FileOutputStream("tempSave.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tsave);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in tempSave.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public void deSerialize() {
		
			

		try {
			FileInputStream fileIn = new FileInputStream("tempSave.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tempSave = (Save) in.readObject();
			
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		
		this.x = tempSave.x;
		this.y = tempSave.y;
	/*	
		System.out.println("Deserialized Player info...");
		System.out.println("Name: " + s.name);
		System.out.println("score " + s.score);*/

		
		

	}
	

	
	

	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	public Handler getHandler(){
		return handler;
	}
	
	public Camera getCam(){
		return cam;
	}
	public HUD getHUD(){
		return hud;
	}

}