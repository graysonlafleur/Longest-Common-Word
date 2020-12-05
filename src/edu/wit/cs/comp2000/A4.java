package edu.wit.cs.comp2000;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class A4 {

	/**
	 * Finds the 10 longest words that appear in all the text files given
	 * in fileNames.
	 * 
	 * @param fileNames Names of text files to read
	 * @return Alphabetized array of longest overlapping words
	 */
	public static String[] FindLongestCommonWords(String[] fileNames) {
		
		ArrayList<LinkedHashSet<String>> unfilteredLists = new ArrayList<LinkedHashSet<String>>();
		LinkedHashSet<String> filteredWordList = new LinkedHashSet<String>();
		
		Thread[] thread = new Thread[fileNames.length];
		FileRead[] readFiles = new FileRead[fileNames.length];
		
		final long startTime = System.currentTimeMillis();
		for(int i = 0; i<fileNames.length; i++) {
			readFiles[i] = new FileRead(fileNames[i]);
			thread[i] = new Thread(readFiles[i]);
			thread[i].start();
		}
		
		for(Thread t : thread)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		final long endTime = System.currentTimeMillis();
		System.out.println("Multithread file reading time is: " + (endTime - startTime));
		
		if(fileNames.length>1)
		{
			for(int i = 0; i<readFiles.length; i++) {
				for(int j = i+1; j<readFiles.length; j++) {
					if(readFiles[i].s.size() > readFiles[j].s.size()) {
						FileRead t = readFiles[i];
						readFiles[i] = readFiles[j];
						readFiles[j] = t;
					}
				}
			}
			
			for(int i = 0; i<fileNames.length; i++) unfilteredLists.add(readFiles[i].s);
		
			filteredWordList = unfilteredLists.get(0);
			for(int i = 1; i<unfilteredLists.size(); i++) filteredWordList = CheckSameWords(filteredWordList, unfilteredLists.get(i));
		}
		else {
			filteredWordList=readFiles[0].s;
		}
		
		
		TreeSet<String> orderedWordList = new TreeSet<String>(new alphAndLength());
		orderedWordList.addAll(filteredWordList);
		
		TreeSet<String> finalWordList = new TreeSet<String>();
		
		int total = 0;
		int lastLength = 0;
		
		for(String word : orderedWordList) {
			if(total <= 10 || word.length() == lastLength) {
				finalWordList.add(word);
				total++;
				lastLength = word.length();
			}
			else break;
		}
		
		String[] longestWords = new String[finalWordList.size()];
		
		int counter = 0;
		
		for(String word : finalWordList) longestWords[counter++] = word;
		final long endTime2 = System.currentTimeMillis();
		return longestWords;
	}
	
	public static LinkedHashSet<String> CheckSameWords(Set<String> o1, Set<String> o2) {
		LinkedHashSet<String> q = new LinkedHashSet<String>();
		
		for(String word : o1) {
			if(o2.contains(word)) q.add(word);
		}
		
		return q;
	}

	public static void main(String[] argv) {

		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<>();

		System.out.printf("Enter text files, one per line (e.g. texts/test1.txt)\nHit enter an extra time to end the list: \n");

		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.equals(""))
				break;
			nameList.add(s);
		}
		sc.close();

		String[] results = FindLongestCommonWords(nameList.toArray(new String[0]));

		for (String s : results) {
			System.out.println(s);
		}
	}

}