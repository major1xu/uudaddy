// some background for chain code image processing here:
// http://www.ee.columbia.edu/~xlx/courses/ee4830-sp08/notes/lec10.pdf
// The idea is to traverse the 2d array (in various directions) to get the shape of the image (boundary).
// Each 1 is a pixel in the image
// and note the direction from 0 to 7 stands for:
// east, northeast, north, northwest, west, southwest, south, and southeast

package com.uudaddy;

import java.util.ArrayList;

public class ChainCode
{
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

    static ArrayList<Position> traversedLocations=new ArrayList<Position>();
    public static void main(String[] args)
    {
        // 6, 6, 0, 0, 6, 0, 6, 7, 7
        // 7, 7, 6
        int [][] image1 =
                {
                // col   0 1 2 3 4 5 6 7
                        {0,1,0,0,0,0,0,0}, // row 0
                        {0,1,0,0,0,1,0,0}, // row 1
                        {0,1,1,1,0,0,1,0}, // row 2
                        {0,0,0,1,1,0,0,1}, // row 3
                        {0,0,0,0,1,0,0,1}, // row 4
                        {0,0,0,0,0,1,0,0}, // row 5
                        {0,0,0,0,0,0,1,0}  // row 6
                };

        /*
        0, 0, 0, 6, 6, 4, 4, 6, 6
        6, 6, 6, 6, 6, 0
         */
        int [][] image2 =
                {// col  0 1 2 3 4 5 6 7
                        {0,0,0,0,0,0,0,0},// row 0
                        {0,1,1,1,1,0,1,0},// row 1
                        {0,0,0,0,1,0,1,0},// row 2
                        {0,0,1,1,1,0,1,0},// row 3
                        {0,0,1,0,0,0,1,0},// row 4
                        {0,0,1,0,0,0,1,0},// row 5
                        {0,0,0,0,0,0,1,1},// row 6
                        {0,0,0,0,0,0,0,0} // row 7
                };

        //findfirst1(image1);
        findfirst1(image2);
    }

    public static void findfirst1(int[][] array)
    {
        Position startingPosition = new Position();

        int col = 0;

        for(col = 0; col<array[0].length; col++) {
            findStartingPosition(array, col, startingPosition);
            traverse(array, startingPosition);
            System.out.println("");
        }
    }

    private static void traverse(int[][] array, Position startingPosition) {
        // k is for column, start from starting column and row (startingPosition.column / startingPosition.row)
        for(int k = startingPosition.column-1; k < array[0].length; k++)
        {
            // i is for row
            for(int i = startingPosition.row; i < array.length; i++)
            {
                Position position = new Position();
                position.column=k;
                position.row=i;
                if (array[i][k] ==1 && listContainsPosition(traversedLocations, position) == false )
                {
                    //System.out.println("k=" +k + ", i="+i);

                    if (i == startingPosition.row && k == startingPosition.column+1) {
                        System.out.print("0,");
                        moveToNext(startingPosition, k, i, position);
                    }
                    else if (i == startingPosition.row && k == startingPosition.column-1) {
                        // cannot walk left in current setup
                        System.out.print("4,");
                        moveToNext(startingPosition, k, i, position);
                    }
                    else if (k == startingPosition.column && i == startingPosition.row+1)
                    {
                        System.out.print("6,");
                        //System.out.println("k=" +k + ", i="+i);
                        moveToNext(startingPosition, k, i, position);
                        //System.out.println("startingPosition : col="+ startingPosition.column + ", row=" + startingPosition.row);

                        // check left
                        //System.out.println("i=" +i + ", k-1="+(k-1) + "array[i][k-1]:" + array[i][k-1]);
                        k = moveLeft1(array[i][k - 1], startingPosition, k, i);
                    }
                    else if (k == startingPosition.column && i == startingPosition.row-1) {
                        System.out.print("2,");
                        moveToNext(startingPosition, k, i, position);
                    }

                    else if (i == startingPosition.row+1 && k == startingPosition.column+1) {
                        System.out.print("7,");
                        moveToNext(startingPosition, k, i, position);
                    }

                    else if (i == startingPosition.row+1 && k == startingPosition.column-1) {
                        System.out.print("5,");
                        moveToNext(startingPosition, k, i, position);
                    }

                    else if (k==startingPosition.column-1 && i == startingPosition.row-1) {
                        System.out.print("1,");
                        moveToNext(startingPosition, k, i, position);
                    }

                    else if (k == startingPosition.column-1 && i == startingPosition.row-1) {
                        System.out.print("3,");
                        moveToNext(startingPosition, k, i, position);
                    }
                }
            }
        }
    }

    private static int moveLeft1(int i, Position startingPosition, int k, int i2) {
        if(i2 ==1 )
        {
            Position position_1 = new Position();
            position_1.column=k-1;
            position_1.row= i2;
            //System.out.println("k=" +k + ", i="+i);

            if(listContainsPosition(traversedLocations, position_1)==false)
            {
                //System.out.println("move to i=" +i + ", k-1="+(k-1) + ", array[i][k-1]:" + array[i][k-1]);
                System.out.print("4,");
                moveToNext(startingPosition, k-1, i2, position_1);

                k=k-1;

                k = moveLeft2(i2, startingPosition, k, i2, position_1);
            }
        }
        return k;
    }

    private static int moveLeft2(int i, Position startingPosition, int k, int i2, Position position_1) {
        if(i2 ==1 ) {
            Position position_2 = new Position();
            position_2.column = k - 1;
            position_2.row = i2;
            //System.out.println("k=" + k + ", i=" + i);

            if (listContainsPosition(traversedLocations, position_2) == false) {
                //System.out.println("move to i=" + i + ", k-1=" + (k - 1) + ", array[i][k-1]:" + array[i][k - 1]);
                System.out.print("4,");
                moveToNext(startingPosition, k - 1, i2, position_1);

                k = k - 1;
            }
        }
        return k;
    }

    private static void moveToNext(Position startingPosition, int k, int i, Position position) {
        startingPosition.row = i;
        startingPosition.column = k;
        traversedLocations.add(position);
    }

    static boolean listContainsPosition(ArrayList<Position> myList, Position postion)
    {
        for(Position mPosition : myList)
        {
            if(mPosition.row==postion.row && mPosition.column==postion.column)
                return true;
        }

        return false;
    }

    private static void findStartingPosition(int[][] array, int col, Position startingPosition) {
        int p = 0;
        // k is for column
        for(int k = col; k < array[0].length; k++)
        {
            // i is for row
            for(int i = 0; i < array.length; i++)
            {
                if (array[i][k] == 1 && p == 0)
                {
                    startingPosition.row = i;
                    startingPosition.column = k;
                    //p++;

                    Position position = new Position();
                    position.column=k;
                    position.row=i;
                    if(listContainsPosition(traversedLocations, position) == false ) {
                        p++;
                        traversedLocations.add(position);
                        System.out.println("startingPosition.row=" +position.row + ", startingPosition.column="+position.column);
                        break;
                    }
                }
            }

            if(p>0) {
                break;
            }
        }
    }
}

