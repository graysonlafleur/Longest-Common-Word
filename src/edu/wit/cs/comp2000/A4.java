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
		
		List<String>[] temp1 = new ArrayList[42];
		List<String>[] temp = new ArrayList[42];
		ArrayList<String> finalAL = new ArrayList<String>();
		
		for(int i = 0; i<42; i++) {
			temp1[i] = new ArrayList<String>();
			temp[i] = new ArrayList<String>();
		}
		
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
							temp1[word.length()-8].add(word.toLowerCase());
						}
					}
					l=br.readLine();
				}
				
				for(int j = 0; j<41; j++) {
					if(temp1[j].size()>0) temp1[j] = temp1[j].stream().distinct().collect(Collectors.toList());
				}
				
				for(int j = 0; j < 41; j++) {
					if(temp1[j].size()>0) temp[j].addAll(temp1[j]);
					temp1[j].clear();
				}
			}
			catch (IOException ioe){
				System.out.println("Error: File inputted that does not exist");
				System.exit(0);
			}
		}
		
		int total = 0;
		
		for(int i = 41; i>=0; i--) {
			if(total <= 10) {
				Collections.sort(temp[i]);
				for(int j = 0; j<temp[i].size(); j++) {
					if((j+fileNames.length-1 < temp[i].size()) && (temp[i].get(j).equals(temp[i].get(j+fileNames.length-1)))) {
						finalAL.add(temp[i].get(j));
						j+=fileNames.length-1;
					}
				}
				total += finalAL.size();
			}
			else break;
		}
		
		String[] longestWords = new String[finalAL.size()];
		
		Collections.sort(finalAL);
		
		for(int i = 0; i<finalAL.size(); i++) {
			longestWords[i] = finalAL.get(i);
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
