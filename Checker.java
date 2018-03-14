package com.uudaddy;

/**
 * Created by minjiexu on 2/26/18.
 * rules
 * A game played by 2 people on a square board. One player (player A) have only one piece "O", the opposing player has
 * multiple pieces "X".
 *
 * move
 * player A can move either top right or top left, and has to jump over a piece 'X', by jumping over, A is capturing 'X'
 *
 * Also the landing spot has to be empty (no 'X')
 * If there is no move for 'O' to make, the game end.
 *
 * The goal is find the route/way for 'O' to capture most 'X' pieces
 *
 * test cases
 *
 * 4x4 right 1; left 0
 * .X.X
 * ....
 * X.X.
 * .O..
 *
 * 5x5  left 0; right 1
 * ...X.
 * .X.X.
 * O....
 * .X...
 *
 * 6x6
 * 1) left 2; right 2
 *  .X.X..
 *  XX.X..
 *  ...X..
 *  .X.X..
 *  ..O...
 *  ..X...
 * 2) left 0; right 2
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  O.....
 */

import java.util.*;

public class Checker {
    // use an arrayList (two dimensional array)
    static ArrayList <char[]>checkerBoard=new ArrayList<char[]>();
    static Location aPlayer = new Location();
    static ArrayList<Location> bPlayers=new ArrayList<Location>();

    // {{'.','X','.','X'},{'.','.','.','.'},{'X','.','X','.'},{'.','O','.','.'}}
    public static void main (String[] args){
        // initialize board
        int numberOfArgs = args.length;
        System.out.println("numberOfArgs=" + numberOfArgs);
        for(int ii=0; ii<numberOfArgs;ii++) {
            checkerBoard.add(args[ii].toCharArray()); // this does not work...
        }
        
        // read the board   => populate aPlayer and bPlayers
        System.out.println("checkerBoard.size()=" + checkerBoard.size());
        
        Iterator it = checkerBoard.iterator();

        int row=0;
        while(it.hasNext()) {
            char[] obj = (char[]) it.next();
            System.out.println(obj);     // {{'.','X','.','X'},{'.','.','.','.'},{'X','.','X','.'},{'.','O','.','.'}}
            //populate aPlayer and bPlayers

            //System.out.println(obj.length);     // ??? 73 ???
            for(int column=0; column<obj.length;column++)
            {
                if(obj[column]=='O')
                {
                    aPlayer.row=row;
                    aPlayer.column=column;
                }
                else if(obj[column]=='X')
                {
                    Location bPlayer = new Location();
                    bPlayer.row=row;
                    bPlayer.column=column;
                    bPlayers.add(bPlayer);
                }
            }
            row++;
        }

        aPlayer.print();
        // find the maximum steps aPlayer can go
        int steps_1=0;
        while(  canMoveTopLeft(aPlayer, bPlayers, checkerBoard) || canMoveTopRight(aPlayer, bPlayers, checkerBoard) ) {
            if (canMoveTopLeft(aPlayer, bPlayers, checkerBoard)) {
                moveLeft(aPlayer, bPlayers, checkerBoard);
                aPlayer.print();
                steps_1++;
            }
            if (canMoveTopRight(aPlayer, bPlayers, checkerBoard)) {
                moveRight(aPlayer, bPlayers, checkerBoard);
                aPlayer.print();
                steps_1++;
            }
        }

        int steps_2=0;
        while(  canMoveTopLeft(aPlayer, bPlayers, checkerBoard) || canMoveTopRight(aPlayer, bPlayers, checkerBoard) ) {
            if (canMoveTopRight(aPlayer, bPlayers, checkerBoard)) {
                moveRight(aPlayer, bPlayers, checkerBoard);
                aPlayer.print();
                steps_2++;
            }
            if (canMoveTopLeft(aPlayer, bPlayers, checkerBoard)) {
                moveLeft(aPlayer, bPlayers, checkerBoard);
                aPlayer.print();
                steps_2++;
            }
        }
        System.out.println("Steps: "+ (steps_1>=steps_2?steps_1:steps_2) ) ;
    }

    static boolean canMoveTopLeft(Location aPlayer, ArrayList<Location> bPlayers, ArrayList <char[]>checkerBoard)
    {
        // if top left of aPlayer is in boundary (after jump over);
        // if there is an 'X' in the top left
        // if the spot after jump over is . (empty)
        //
        if( (aPlayer.row-2)>=0 && (aPlayer.column-2)>=0 )
        {
            if( ((checkerBoard.get(aPlayer.row-1))[aPlayer.column-1])=='X')
            {
                if( ((checkerBoard.get(aPlayer.row-2))[aPlayer.column-2])=='.')
                {
                     System.out.println("canMoveTopLeft");
                     return true;
                }
            }
        }
        return  false;
    }

    // My mistake : there was some confusion between x and y, then the direction of y
    static boolean canMoveTopRight(Location aPlayer, ArrayList<Location> bPlayers, ArrayList <char[]>checkerBoard)
    {
        // if top left of aPlayer is in boundary (after jump over);
        // if there is an 'X' in the top left
        // if the spot after jump over is . (empty)
        //
        //System.out.println("canMoveTopRight: beginning, aPlayer: ");
        //System.out.println((checkerBoard.get(aPlayer.row))[aPlayer.column]);
        if( (aPlayer.row-2)>=0 && (aPlayer.column+2)<=(checkerBoard.size()-1) )
        {
            //System.out.println("canMoveTopRight: in boundary");
            if( ((checkerBoard.get(aPlayer.row-1))[aPlayer.column+1])=='X')
            {
                //System.out.println("canMoveTopRight: find X, checking . ");
                //System.out.println((checkerBoard.get(aPlayer.row-2))[aPlayer.column+2]);
                if( ((checkerBoard.get(aPlayer.row-2))[aPlayer.column+2])=='.')
                {
                    //System.out.println("canMoveTopRight: find .");
                    return true;
                }
            }
        }
        return  false;
    }

    private static void moveLeft(Location aPlayer, ArrayList<Location> bPlayers, ArrayList<char[]> checkerBoard) {
        System.out.println("canMoveTopLeft: ");
        Location loc=new Location();
        loc.row=aPlayer.row-1;
        loc.column=aPlayer.column-1;
        if(bPlayers.contains(loc)) {
            checkerBoard.get(loc.row)[loc.column]='.';
            bPlayers.remove(loc);
        }
        checkerBoard.get(aPlayer.row)[aPlayer.column]='.';
        aPlayer.row=aPlayer.row-2;
        aPlayer.column=aPlayer.column-2;
        checkerBoard.get(aPlayer.row)[aPlayer.column]='O';
    }

    private static void moveRight(Location aPlayer, ArrayList<Location> bPlayers, ArrayList<char[]> checkerBoard) {
        System.out.println("canMoveTopRight: ");
        Location loc=new Location();
        loc.row=aPlayer.row-1;
        loc.column=aPlayer.column+1;
        if(bPlayers.contains(loc)) {
            checkerBoard.get(loc.row)[loc.column]='.';
            bPlayers.remove(loc);
        }
        checkerBoard.get(aPlayer.row)[aPlayer.column]='.';
        aPlayer.row=aPlayer.row-2;
        aPlayer.column=aPlayer.column+2;
        checkerBoard.get(aPlayer.row)[aPlayer.column]='O';
    }
}
