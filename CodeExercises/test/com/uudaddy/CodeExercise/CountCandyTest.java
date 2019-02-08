package com.uudaddy.CodeExercise;

import org.junit.Test;

import com.uudaddy.CodeExercise.CountCandy;

public class CountCandyTest {
	@Test
	public void testCase1()
	{
		CountCandy cc = new CountCandy();
		cc.countCandies(16,0);
	}
	
	@Test
	public void testCase2()
	{
		CountCandy cc = new CountCandy();
		cc.countCandies(32,0);
	}
}
