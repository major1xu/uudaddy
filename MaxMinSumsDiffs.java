package com.uudaddy;

/**
 * This is inspired by my daughter Serenity's homework
 * Given number 0, 1, 2, 3, 4, 5, 6 and 7, use each number only once, find the largest difference
 * and smallest difference of the two 4 digits numbers: for example 7654 - 3210 (if the difference is largest)
 * Obviously in this case 0 cannot be in the thousandth position.
 *
 * I expand it a bit to make the numbers configurable (as input parameters), as well as calculate
 * the max sum and min sum
 *
 * Note it's still possible a 3rd grader or older / younger person to get the answer via some thinking
 * and hand / mind calculation. Here I am just using a program to validate the results (not try to be as
 * creative as human beings)
 *
 * Created by minjiexu on 10/17/18.
 */

import java.util.*;

public class MaxMinSumsDiffs {

    static ArrayList <Integer>checkerBoard=new ArrayList<Integer>();

    public static void main (String[] args)
    {
        System.out.println("Hello world");

        // use an array to parse the arguments from command line (for example, 0, 1, 2,...7)
        int numberOfArgs = args.length;
        System.out.println("numberOfArgs=" + numberOfArgs);
        ArrayList<Integer> remainingIntegers = new ArrayList<Integer>(numberOfArgs);

        for(int ii=0; ii<numberOfArgs;ii++) {
            checkerBoard.add(Integer.parseInt( args[ii]) );
            System.out.println("Integer.parseInt( args[ii])=" + Integer.parseInt( args[ii]));
            remainingIntegers.add(Integer.parseInt( args[ii]));
        }


        // Come up with the number combinations
        // sort the numbers in descending order, pick number from neighboring numbers for thousandth digits
        // compose the rest of numbers so that the big number gets small numbers, and put them in small to largest
        // vice versa (the other way) for the small number, e.g, below it should be 7012 - 6543
        // 
        // 7 5 3 1
        // 6 4 2 0

        // 5 012
        // 4 753

        // the thousand digit can be 1 to 7 (except 0)
        int number_1 = 0;
        int number_2 = 0;
        int difference = 0;
        int number_1_thousand;
        int number_1_hundred;

        int ii=0;
        for(ii=0; ii<numberOfArgs; ii++) {
            Integer intNum = remainingIntegers.get(ii);
            if( intNum.intValue() !=0) {
                number_1_thousand = intNum;
                remainingIntegers.remove(intNum);

                if( remainingIntegers.isEmpty() == false ) {
                    number_1_hundred = remainingIntegers.get(0);  // use remainingIntegers?
                    remainingIntegers.remove(args[ii]);
                }

            }
        }
    }

}
