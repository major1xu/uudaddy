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
        ArrayList<Integer> remainingIntegersCopy = new ArrayList<Integer>(numberOfArgs);

        //Digit[] remainingDigits = new Digit[numberOfArgs];

        for(int ii=0; ii<numberOfArgs;ii++) {
            checkerBoard.add(Integer.parseInt( args[ii]) );
            //System.out.println("Integer.parseInt( args[ii])=" + Integer.parseInt( args[ii]));
            remainingIntegers.add(Integer.parseInt( args[ii]));
            //remainingDigits[ii]=new Digit(Integer.parseInt(args[ii]));
        }
        
        // Come up with the number combinations
        // sort the numbers in descending order, pick number from neighboring numbers for thousandth digits.

        // https://stackoverflow.com/questions/14475556/how-to-sort-arraylist-of-objects
        // descending order  - better way
        Collections.sort(remainingIntegers, new IntegerComparator());

        // http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
        // descending order
        //Arrays.sort(remainingDigits);

        System.out.println("After sorting:");
        for(int ii=0; ii<numberOfArgs;ii++) {
            //System.out.println("remainingIntegers[ii]=" + remainingIntegers.get(ii));
            //System.out.println("remainingDigits[ii]=" + remainingDigits[ii].getQuantity());
        }
        System.out.println("remainingIntegers.size()=" + remainingIntegers.size());

        //
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
        int number_1_thousand=0;
        int number_2_thousand=0;
        int number_1_hundred=0;
        int number_2_hundred=0;
        int number_1_tenth=0;
        int number_2_tenth=0;
        int number_1_single=0;
        int number_2_single=0;
        boolean found_min_diff=false;
        int min_diff=0;

        int ii=0;
        while(remainingIntegers.isEmpty()==false && found_min_diff==false) {
            Integer intNum = remainingIntegers.get(ii);
            number_1_thousand = intNum;
            //System.out.println("number_1_thousand=" + number_1_thousand);
            remainingIntegers.remove(new Integer(intNum));

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                intNum = remainingIntegers.get(ii);
                number_2_thousand = intNum;
                //System.out.println("number_2_thousand=" + number_2_thousand);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                Collections.sort(remainingIntegers);

                intNum = remainingIntegers.get(ii);
                number_1_hundred = intNum;
                //System.out.println("number_1_hundred=" + number_1_hundred);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                intNum = remainingIntegers.get(ii);
                number_1_tenth = intNum;
                //System.out.println("number_1_tenth=" + number_1_tenth);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                intNum = remainingIntegers.get(ii);
                number_1_single = intNum;
                //System.out.println("number_1_single=" + number_1_single);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                Collections.sort(remainingIntegers, new IntegerComparator());

                intNum = remainingIntegers.get(ii);
                number_2_hundred = intNum;
                //System.out.println("number_2_hundred=" + number_2_hundred);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                intNum = remainingIntegers.get(ii);
                number_2_tenth = intNum;
                //System.out.println("number_2_tenth=" + number_2_tenth);
                remainingIntegers.remove(new Integer(intNum));
            }

            if( remainingIntegers.isEmpty() == false && found_min_diff==false) {
                intNum = remainingIntegers.get(ii);
                number_2_single = intNum;
                //System.out.println("number_2_single=" + number_2_single);
                remainingIntegers.remove(new Integer(intNum));
            }

            int number1=number_1_thousand*1000+number_1_hundred*100+number_1_tenth*10+number_1_single;
            int number2=number_2_thousand*1000+number_2_hundred*100+number_2_tenth*10+number_2_single;
            min_diff=number1-number2;
            System.out.println("number1=" + number1);
            System.out.println("number2=" + number2);
            System.out.println("min_diff=" + min_diff);
        }
    }

    private static class IntegerComparator implements Comparator<Integer> {
        @Override public int compare(Integer p1, Integer p2) {
            int returnValue = 0;

            if ((p1.intValue() - p2.intValue()) > 0) {
                return -1;
            } else if ((p1.intValue() - p2.intValue()) == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
