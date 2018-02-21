package com.grey.inferno.framework;

/*
import java.io.*;
import java.util.*;

	public class Sort {

	    public static void sort() throws Exception {
	        BufferedReader reader = new BufferedReader(new FileReader("sss.txt"));
	        Map<String, String> map=new TreeMap<String, String>();
	        String line="";
	        while((line=reader.readLine())!=null){
	        	map.put(getField(line),line);
	        }
	        reader.close();
	        FileWriter writer = new FileWriter("sss.txt");
	        for(String val : map.values()){
	        	writer.write(val);	
	        	writer.write('\n');
	        }
	        writer.close();
	    }

	    private static String getField(String line) {
	    	return line.split(" ")[0];//extract value you want to sort on
	    }
	}

*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
 
 
public class Sort {
	public static void sort() throws IOException {
		File fin = new File("sss.txt");
		File fout = new File("ss.txt");
 
 
		FileInputStream fis = new FileInputStream(fin);
		FileOutputStream fos = new FileOutputStream(fout);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
 
		String aLine;
		ArrayList<String> al = new ArrayList<String> ();
 
 
		@SuppressWarnings("unused")
		int i = 0;
		while ((aLine = in.readLine()) != null) {
			//get the lines you want, here I don't want something starting with - or empty
			if (!aLine.trim().startsWith("-") && aLine.trim().length() > 0) {
				al.add(aLine);
				i++;
			}
		}
 
		Collections.sort(al);
		//output sorted content to a file
		for (String s : al) {
		    //System.out.println(s);
		    out.write(s);
			//out.newLine();
			out.newLine();
		}
 
		in.close();
		out.close();
	}
}