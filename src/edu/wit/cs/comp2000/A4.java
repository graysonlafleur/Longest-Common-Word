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
		
		
		List<String> filteredWordList = new ArrayList<String>();
		List<String> unfilteredWordList = new ArrayList<String>();
		ArrayList<String> finalWordList = new ArrayList<String>();
		
		for(int i = 0; i<fileNames.length; i++) {
			try { 
				String l;
				String[] words;
				BufferedReader br = new BufferedReader(new FileReader(fileNames[i]));
				
				l = br.readLine();
				
				while(l!=null) {
					words = l.split("[^a-zA-Z]");
					for(String word : words) {
						if(word.length() >= 8 && word.length() <= 50) {
							unfilteredWordList.add(word.toLowerCase());
						}
					}
					l=br.readLine();
				}
				
				unfilteredWordList = unfilteredWordList.stream().distinct().collect(Collectors.toList());
					
				filteredWordList.addAll(unfilteredWordList);
				
				unfilteredWordList = new ArrayList<String>();
				
				br.close();
			}
			catch (IOException ioe){
				System.out.println("Error: File inputted that does not exist");
				System.exit(0);
			}
		}
		
		int total = 0;
		int lastLength = 0;
		
		Collections.sort(filteredWordList, new alphAndLength());
		
		for(int j = 0; j<filteredWordList.size(); j++) {
			if(total<10 || lastLength==filteredWordList.get(j).length()) {
				if(j+fileNames.length-1<filteredWordList.size() && filteredWordList.get(j).equals(filteredWordList.get(j+fileNames.length-1))) {
					finalWordList.add(filteredWordList.get(j));
					total++;
					lastLength = filteredWordList.get(j).length();
					j+= fileNames.length-1;
				}
			}
			else break;
		}
		
		Collections.sort(finalWordList);
		
		String[] longestWords = new String[finalWordList.size()];
		
		for(int i = 0; i<finalWordList.size(); i++) {
			longestWords[i] = finalWordList.get(i);
			System.out.println(longestWords[i]);
		}
		
		return longestWords;
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
