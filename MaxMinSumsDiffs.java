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
 * Note the symmetry in the results
 *
 * number1=7012
 number2=6543
 min_diff=469
 ======
 number1=6012
 number2=5743
 min_diff=269
 ======
 number1=5012
 number2=4763
 min_diff=249
 ======
 number1=4012
 number2=3765
 min_diff=247
 ======
 number1=3014
 number2=2765
 min_diff=249
 ======
 number1=2034
 number2=1765
 min_diff=269
 ======
 number1=1234
 number2=765
 min_diff=469
 ======
 */

import java.util.*;

public class MaxMinSumsDiffs {

    //static ArrayList <Integer>checkerBoard=new ArrayList<Integer>();

    public static void main (String[] args)
    {
        //System.out.println("Hello world");

        // use an array to parse the arguments from command line (for example, 0, 1, 2,...7)
        int numberOfArgs = args.length;
        //System.out.println("numberOfArgs=" + numberOfArgs);
        ArrayList<Integer> remainingIntegers = new ArrayList<Integer>(numberOfArgs);
        ArrayList<Integer> remainingIntegersCopy = new ArrayList<Integer>(numberOfArgs);

        //Digit[] remainingDigits = new Digit[numberOfArgs];

        for(int ii=0; ii<numberOfArgs;ii++) {
            //checkerBoard.add(Integer.parseInt( args[ii]) );
            //System.out.println("Integer.parseInt( args[ii])=" + Integer.parseInt( args[ii]));
            remainingIntegers.add(Integer.parseInt( args[ii]));
            remainingIntegersCopy.add(Integer.parseInt( args[ii]));
            //remainingDigits[ii]=new Digit(Integer.parseInt(args[ii]));
        }
        
        // Come up with the number combinations
        // sort the numbers in descending order, pick number from neighboring numbers for thousandth digits.

        // https://stackoverflow.com/questions/14475556/how-to-sort-arraylist-of-objects
        // descending order  - better way
        // 7, 6, 5, 4, 3, 2, 1, 0
        Collections.sort(remainingIntegers, new IntegerComparator());
        Collections.sort(remainingIntegersCopy, new IntegerComparator());

        // http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
        // descending order
        //Arrays.sort(remainingDigits);

       // System.out.println("After sorting:");
        int ii = 0;
        for(ii=0; ii<numberOfArgs;ii++) {
            //System.out.println("remainingIntegers[ii]=" + remainingIntegers.get(ii));
            //System.out.println("remainingDigits[ii]=" + remainingDigits[ii].getQuantity());
        }
        //System.out.println("remainingIntegers.size()=" + remainingIntegers.size());
        
        boolean found_min_diff=false;
        int min_diff=0;

        // here I need to rotate the digits, maybe I need to make copies for the list, so that every time I have fresh list

        min_diff = findDiff(remainingIntegers, found_min_diff);

        // use remainingIntegersCopy: move first to the last
        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        //System.out.println("3: remainingIntegers.size()=" + remainingIntegers.size());
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_2 = findDiff(remainingIntegers, found_min_diff);

        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_3 = findDiff(remainingIntegers, found_min_diff);

        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_4 = findDiff(remainingIntegers, found_min_diff);

        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_5 = findDiff(remainingIntegers, found_min_diff);

        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_6 = findDiff(remainingIntegers, found_min_diff);

        remainingIntegers = reCreateRemainingIntegerList(numberOfArgs, remainingIntegersCopy);
        remainingIntegersCopy = reCreateRemainingIntegerListCopy(numberOfArgs, remainingIntegers, remainingIntegersCopy);
        int min_diff_7 = findDiff(remainingIntegers, found_min_diff);
    }

    private static ArrayList<Integer> reCreateRemainingIntegerListCopy(int numberOfArgs, ArrayList<Integer> remainingIntegers, ArrayList<Integer> remainingIntegersCopy) {
        int ii;
        remainingIntegersCopy.clear();
        remainingIntegersCopy = new ArrayList<Integer>(numberOfArgs);
        for(ii=0; ii<numberOfArgs;ii++) {
            remainingIntegersCopy.add(remainingIntegers.get(ii));
            //System.out.println("remainingIntegersCopy[ii]=" + remainingIntegersCopy.get(ii));
        }
        return remainingIntegersCopy;
    }

    private static ArrayList<Integer> reCreateRemainingIntegerList(int numberOfArgs, ArrayList<Integer> remainingIntegersCopy) {
        ArrayList<Integer> remainingIntegers;
        int ii;
        remainingIntegers = new ArrayList<Integer>(numberOfArgs);
        //System.out.println("2: remainingIntegers.size()=" + remainingIntegers.size());

        for(ii=1; ii<numberOfArgs;ii++) {
            remainingIntegers.add(remainingIntegersCopy.get(ii));
        }
        remainingIntegers.add(remainingIntegersCopy.get(0));
        return remainingIntegers;
    }

    private static int findDiff(ArrayList<Integer> remainingIntegers, boolean found_min_diff) {
        //
        // compose the rest of numbers so that the big number gets small numbers, and put them in small to largest
        // vice versa (the other way) for the small number, e.g, below it should be 7012 - 6543
        //
        // 7 5 3 1
        // 6 4 2 0

        // 5 012
        // 4 753
        // the thousand digit can be 1 to 7 (except 0)

        int number_1_thousand;
        int number_2_thousand=0;
        int number_1_hundred=0;
        int number_2_hundred=0;
        int number_1_tenth=0;
        int number_2_tenth=0;
        int number_1_single=0;
        int number_2_single=0;
        int min_diff = 0;
        int ii = 0;
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

                // This is key
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

                // This is key
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
            System.out.println("======");
        }
        return min_diff;
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
