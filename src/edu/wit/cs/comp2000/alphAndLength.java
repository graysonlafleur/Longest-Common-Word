package edu.wit.cs.comp2000;

import java.util.Comparator;

public class alphAndLength implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {             
        if (o1.length()!=o2.length()) {
            return o2.length()-o1.length(); //overflow impossible since lengths are non-negative
        }
        return o1.compareTo(o2);
    }
	
}
