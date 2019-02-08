package com.uudaddy.CodeExercise;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MissingLetters {
	
	Set s = Collections.synchronizedSet(new HashSet());
	
	public MissingLetters()
	{
		char theLetterAfterLowerZ = (char) ("z".charAt(0)+1);
		for(String myChar="a"; 
				myChar.equalsIgnoreCase(Character.toString(theLetterAfterLowerZ) )!=true; 
				myChar= Character.toString( (char)(myChar.charAt(0)+1) ) )
		{
			s.add(myChar);
		}
	}
	
	public void printMissingLetters()
	{
		Iterator<String> itr = s.iterator(); 
		while(itr.hasNext())
		{ 
			System.out.print(itr.next()); 
		}

	}
	
    public String getMissingLetters(String inputString)
    {
    	boolean letterFlags[] = new boolean[26];
    	int index = 0;
    	for(index=0; index<=25; index++)
    	{
    		letterFlags[index]=false;
    	}
    	
    	for(index=0; index<inputString.length();index++)
    	{
    		String lowerCase = inputString.substring(index, index+1).toLowerCase();
    		if(s.contains(lowerCase))
    		{
    			// get  from the letter : a => 0, b => 1, etc
    			int ii = 0 ;
    			ii=lowerCase.charAt(0)-'a';
    			letterFlags[ii]=true;
    		}
    	}
    	
    	String returnString = "";
    	for(index=0; index<=25; index++)
    	{
    		if(letterFlags[index]==false)
    		{
    			String ss = Character.toString((char)('a'+index));
    			returnString=returnString+ss;
    		}
    	}
    	
    	return returnString;
    }
}
