package com.uudaddy.CodeExercise;

// given an amount of dollar, assume each candy costs $2 a piece; also 3 candy wrapper can be used to 
// exchange a new candy.
// If there is $16 at the beginning, what's the total number of candies can be bought
//

public class CountCandy {
	public int countCandies(int dollarAmount, int numberOfWrappers)
	{
		if(dollarAmount/2 > 0 || numberOfWrappers/3 > 0)
		{
			System.out.println("dollarAmount: " + dollarAmount + " ;numberOfWrappers: " + numberOfWrappers);
			int candyFromDollar = dollarAmount/2; 
			int candyFromWrapper = numberOfWrappers/3;
			System.out.println("candyFromDollar: " + candyFromDollar + " ;candyFromWrapper: " + candyFromWrapper);

			int newDollarAmount = dollarAmount - candyFromDollar * 2;
			int numberOfWrappers2 = candyFromDollar + candyFromWrapper;
			return countCandies(newDollarAmount, numberOfWrappers2);
		}
		else // dollarAmount/2 == 0 && numberOfWrappers/3 == 0
		{
		    return 0;
		}
	}

}
