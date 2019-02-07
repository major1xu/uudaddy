package com.uudaddy.CodeExcercise;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.uudaddy.CodeExcercise.MissingLetters;


public class MissingLettersTest {
	
//	@Test
//	public void testPrintMissingLetters()
//	{
//		MissingLetters missingLetters = new MissingLetters();
//		
//		missingLetters.printMissingLetters();
//		//System.out.println("");
//	}
	
	@Test
	public void testCase1()
	{
		MissingLetters missingLetters = new MissingLetters();
        String myString = "A quick brown fox jumps over the lazy dog";
        String emptyString = "";
        assert(emptyString.equalsIgnoreCase(missingLetters.getMissingLetters(myString)));
	}
	
	@Test
	public void testCase2()
	{
		MissingLetters missingLetters = new MissingLetters();
        String myString = "A slow yellow fox crawls under the proactive dog";
        String returnString0 = "bjkmqz";
        String returnString = missingLetters.getMissingLetters(myString);
        System.out.println("");
        System.out.println(returnString);
        assert(returnString0.equals(returnString));
	}
	
	@Test
	public void testCase3()
	{
		MissingLetters missingLetters = new MissingLetters();
        String myString = "Lions, and tigers, and bears, oh my!";
        String returnString0 = "cfjkpquvwxz";
        String returnString = missingLetters.getMissingLetters(myString);
        System.out.println("");
        System.out.println(returnString);
        assert(returnString0.equals(returnString));
	}
	
	@Test
	public void testCase4()
	{
		MissingLetters missingLetters = new MissingLetters();
        String myString = "";
        String returnString0 = "abcdefghijklmnopqrstuvwxyz";
        String returnString = missingLetters.getMissingLetters(myString);
        System.out.println("");
        System.out.println(returnString);
        assert(returnString0.equals(returnString));
	}
}


