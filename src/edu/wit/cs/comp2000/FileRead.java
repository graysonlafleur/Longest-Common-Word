package edu.wit.cs.comp2000;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

public class FileRead implements Runnable{
	
	public LinkedHashSet<String> s;
	public String fileName;
	
	public FileRead(String fileName) {
		this.fileName = fileName;
		s = new LinkedHashSet<String>();
	}
	
	public void run() {
		try {
			String l = "";
			try {
				l = new String(Files.readAllBytes(Paths.get(fileName)));
			}
			catch(IOException e) {
				System.out.println("File not found");
			}
			String[] words = l.split("[^a-zA-Z]");
			
			for(String word : words) {
				if(word.length() >= 8 && word.length() <= 50) s.add(word);
			}
			
		}
		catch(Exception e){
			System.out.println("Caught an exception");
		}
	}
}
