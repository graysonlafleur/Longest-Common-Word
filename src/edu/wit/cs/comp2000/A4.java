package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.Scanner;

public class A4 {

	/**
	 * Finds the 10 longest words that appear in all the text files given
	 * in fileNames.
	 * 
	 * @param fileNames Names of text files to read
	 * @return Alphabetized array of longest overlapping words
	 */
	public static String[] FindLongestCommonWords(String[] fileNames) {
		// TOOD: Implement
		return null;
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
