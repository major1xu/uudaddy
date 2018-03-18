package com.uudaddy;


/**
 * Created on 3/17/18.
 * 
 * A child is located in the middle of a grid which is depicted as a rectangular A x B 2-dimensional array.
 * 
 * The items in the array represent the number of chocolate pieces that exist in each location in the array.
 * If the array does not contain a specific center, the child will begin in a location nearby the center with the highest number
 * of chocolate pieces. In each iteration, the child will consume the chocolate pieces on the square where he is located and then
 * moves up, down, left or right selecting the location which has the highest number of chocolate pieces. In case there are no
 * chocolate pieces in the adjacent cells, the child will sleep.
 * 
 Your task is to write a function which takes an array representing the locations and it should return the
 total number of chocolate pieces a child would eat. Test your program with the array below:

 [[6, 8, 9, 7, 4]
 [0, 0, 8, 0, 5]
 [5, 7, 4, 5, 10]
 [4, 2, 0, 6, 9]]
 */

import java.util.*;

public class Chocolates {
    // this is the original test case, 4 x 5
    //static int choArr[][] = {{6, 8, 9, 7, 4}, {0, 0, 8, 0, 5}, {5, 7, 4, 5, 10}, {4, 2, 0, 6, 9}};

    // test case 2, 5 x 6
    //static int choArr[][] = {{6, 8, 9, 7, 4, 1},{0, 0, 8, 0, 5, 2},{5, 7, 4, 5, 10, 3},{4, 2, 0, 6, 9, 4},{4, 2, 0, 6, 9, 4}};

    // test case 3, 5 x 5
    static int choArr[][] = {{6, 8, 9, 7, 4},{0, 0, 8, 0, 5},{5, 7, 4, 9, 10},{4, 2, 0, 6, 9},{4, 2, 0, 6, 9}};

    // test case 4, 6 x 6
    //static int choArr[][] = {{6, 8, 9, 7, 4, 1},{0, 0, 8, 0, 5, 2},{5, 7, 4, 5, 10, 3},{4, 2, 0, 6, 9, 4},{4, 2, 0, 6, 9, 4}, {4, 2, 0, 6, 9, 4}};
    static int row = choArr.length;      // number_of_rows
    static int col = choArr[0].length;   // number_of_columns

    static class Position {
        public int row;
        public int column;

        public void Position()
        {}

        public void Position(int a, int b)
        {
            row=a;
            column=b;
        }

        public void print()
        {
            System.out.println("row= "+row +" column= "+column);
        }

    }
    static Position baby = new Position();

    public static void main(String[] args) {

        System.out.println("ChoArr row# is "+ row);
        System.out.println("ChoArr col# is "+ col);

        System.out.println("The chocolate array values are:");

        print2DArray();

        //check chocalate Arr row and col length;
        System.out.println("-----------------------------");

        int total_chocolates_eaten = findStartingPoint(row, col);

        // move, left, right, up or down
        System.out.println("baby position: row=" + baby.row + "; column=" +baby.column + ". Counts of chocolates: " + choArr[baby.row][baby.column]);
        while(canMove())
        {
            // find largest number in the neighbor (left, right, up and down)
            // here I am going to compare the four numbers: left, right, up and down
            ArrayList<Integer> myIntList = new ArrayList<Integer>();
            if(baby.column-1>=0) {
                myIntList.add(new Integer(choArr[baby.row][baby.column - 1]));
            }
            else
            {
                myIntList.add(0);
            }

            if(baby.column+1<=col-1) {
                myIntList.add(new Integer(choArr[baby.row][baby.column + 1]));
            }
            else
            {
                myIntList.add(0);
            }

            if(baby.row-1>=0) {
                myIntList.add(new Integer(choArr[baby.row-1][baby.column]));
            }
            else
            {
                myIntList.add(0);
            }

            if(baby.row+1<=row-1) {
                myIntList.add(new Integer(choArr[baby.row+1][baby.column]));
            }
            else
            {
                myIntList.add(0);
            }
            
            Integer maxInt = Collections.max(myIntList);

            int position = -1;
            position = myIntList.indexOf(maxInt);
            
            // map from maxInt to the row and column numbers
            int counts_of_chocolates  = 0;

            switch (position)
            {
                case 0:
                    counts_of_chocolates =  moveLeft();
                    total_chocolates_eaten += counts_of_chocolates;
                    break;
                case 1:
                    counts_of_chocolates =  moveRight();
                    total_chocolates_eaten += counts_of_chocolates;
                    break;
                case 2:
                    counts_of_chocolates = moveUp();
                    total_chocolates_eaten +=  counts_of_chocolates;
                    break;
                case 3:
                    counts_of_chocolates = moveDown();
                    total_chocolates_eaten += counts_of_chocolates;
                    break;

                default:
            }

            System.out.println("baby position: row=" + baby.row + "; column=" +baby.column + ". Counts of chocolates: " + counts_of_chocolates);
            print2DArray();
        }

        System.out.println("Total_chocolates_eaten:" + total_chocolates_eaten);
    }    // end of main

    private static void print2DArray() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%5d", choArr[i][j]);
             }
             System.out.println();
        }
    }

    private static boolean canMove()
    {
        if(canMoveLeft() || canMoveRight() || canMoveUp() || canMoveDown())
        {
            return true;
        }
        return false;
    }

    private static boolean canMoveLeft()
    {
        if((baby.column-1)>=0) {
            if (choArr[baby.row][baby.column-1] > 0)
            {
                return true;
            }
        }
        return false;
    }

    // return the count of the chocolate
    private static int moveLeft()
    {
        int num_chocolates = choArr[baby.row][baby.column-1];
        // change both the choArr and baby position
        baby.column=baby.column-1;
        choArr[baby.row][baby.column]=0;
        return num_chocolates;
    }

    private static boolean canMoveRight()
    {
        if((baby.column+1)<=col-1) {
            if (choArr[baby.row][baby.column+1] > 0)
            {
                return true;
            }
        }
        return false;
    }

    private static int moveRight()
    {
        int num_chocolates = choArr[baby.row][baby.column+1];
        // change both the choArr and baby position
        baby.column=baby.column+1;
        choArr[baby.row][baby.column]=0;
        return num_chocolates;
    }

    private static boolean canMoveUp()
    {
        if((baby.row-1)>=0) {
            if (choArr[baby.row-1][baby.column] > 0)
            {
                return true;
            }
        }
        return false;
    }

    private static int moveUp()
    {
        int num_chocolates = choArr[baby.row-1][baby.column];
        // change both the choArr and baby position
        baby.row=baby.row-1;
        choArr[baby.row][baby.column]=0;
        return num_chocolates;
    }

    private static boolean canMoveDown()
    {
        if((baby.row+1)<=row-1) {
            if (choArr[baby.row+1][baby.column] > 0)
            {
                return true;
            }
        }
        return false;
    }

    private static int moveDown()
    {
        int num_chocolates = choArr[baby.row+1][baby.column];
        // change both the choArr and baby position
        baby.row=baby.row+1;
        choArr[baby.row][baby.column]=0;
        return num_chocolates;
    }

    private static int findStartingPoint(int row, int col) {
        if ( (row-1)%2 == 0 ) // check array has a specific center or not for row
        {
            baby.row = (row - 1) / 2;
            if( (col-1)%2 == 0) // check array has a specific center or not for column
            {
                baby.column=(col-1)/2;
            }
            else
            {
                System.out.println("left: " + choArr[baby.row][(col-2)/2]);
                System.out.println("right: " + choArr[baby.row][col/2]);
                if(choArr[baby.row][(col-2)/2] >= choArr[baby.row][col/2])
                {
                    baby.column=(col-2)/2;
                }
                else
                {
                    baby.column=col/2;
                }
            }
        }
        else {  // this applies to the test case 1 here, when we have an even number of rows
            int baby_row_1 = (row-2)/2;
            int baby_row_2 = row/2;

            if( (col-1)%2 == 0) // check array has a specific center for column
            {
                baby.column=(col-1)/2;
                if(choArr[baby_row_1][baby.column] >= choArr[baby_row_2][baby.column])
                {
                    baby.row=baby_row_1;
                }
                else
                {
                    baby.row=baby_row_2;
                }
            }
            else {
                int baby_column_1 = 0;
                int baby_column_2 = 0;
                 baby_column_1=(col-2)/2;
                 baby_column_2=col/2;
                 // here I am going to compare the four numbers
                 // combination of baby_row_1 / baby_row_2 and  baby_column_1 / baby_column_2
                ArrayList<Integer> myIntList = new ArrayList<Integer>();
                myIntList.add(new Integer(choArr[baby_row_1][baby_column_1]));
                myIntList.add(new Integer(choArr[baby_row_1][baby_column_2]));
                myIntList.add(new Integer(choArr[baby_row_2][baby_column_1]));
                myIntList.add(new Integer(choArr[baby_row_2][baby_column_2]));
                Integer maxInt = Collections.max(myIntList);

                int position = -1;
                position = myIntList.indexOf(maxInt);
                // map from maxInt to the row and column numbers
                switch (position)
                {
                    case 0:
                        baby.row=baby_row_1;
                        baby.column=baby_column_1;
                        break;
                    case 1:
                        baby.row=baby_row_1;
                        baby.column=baby_column_2;
                        break;
                    case 2:
                        baby.row=baby_row_2;
                        baby.column=baby_column_1;
                        break;
                    case 3:
                        baby.row=baby_row_2;
                        baby.column=baby_column_2;
                        break;

                    default:
                }

            }
        }    // end of big if

        int baby_start = choArr[baby.row][baby.column]; // this is the number of chocolate on the baby initial location
        System.out.println("Found center: row: " + baby.row + "; column: " + baby.column +". Counts of chocolates at beginning point:" + baby_start);
        return baby_start;
    }
}
