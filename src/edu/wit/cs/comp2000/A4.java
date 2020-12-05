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
		
		Set<String> unfilteredWordList = new LinkedHashSet<String>();
		Set<String> filteredWordList = new LinkedHashSet<String>();
		
		for(int i = 0; i<fileNames.length; i++) {
			try {
				String l;
				String[] words;
				BufferedReader br = new BufferedReader(new FileReader(fileNames[i]));
				
				l = br.readLine();
				
				while(l!=null) {
					words = l.split("[^a-zA-Z]");
					for(String word : words) {
						if(word.length() >= 8 && word.length() <= 50) unfilteredWordList.add(word);
					}
					
					l = br.readLine();
				}
				
				if(i==0) filteredWordList = unfilteredWordList;
				else filteredWordList = CheckSameWords(filteredWordList, unfilteredWordList);
				
				unfilteredWordList = new LinkedHashSet<String>();
				
				br.close();
			}
			catch (IOException ioe){
				System.out.println("Error: File inputted that does not exist");
				System.exit(0);
			}
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