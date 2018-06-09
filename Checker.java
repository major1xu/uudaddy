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
 * this one won't expose the limitation of current algorithm (it returns 1 because it cannot go left the beginning)
 * XXXXX
 * ...X.
 * .X.X.
 * O....
 * .X...
 *
 * 6x6
 * 1) left 1; right 2
 *  .X.X..
 *  XX.X..
 *  ...X..
 *  .X.X..
 *  .X.X..
 *  ..O...
 * 2) left 0; right 2
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  .X.X..
 *  O.....
 *
 * 7x7  left 3; right 3
 *  ...X...
 *  .XX.XX.
 *  .X.....
 *  ..X.X..
 *  ...X...
 *  ..X.X..
 *  ...O...
 */

import java.util.*;

public class Checker {
    // use an arrayList (two dimensional array)
    static ArrayList <char[]>checkerBoard=new ArrayList<char[]>();

    static class Location {
        public int row;
        public int column;

        public void Location()
        {}

        public void Location(int a, int b)
        {
            row=a;
            column=b;
        }

        public void print()
        {
            System.out.println("row= "+row +" column= "+column);
        }

    }
    static Location aPlayer = new Location();
    static ArrayList<Location> bPlayers=new ArrayList<Location>();

    // future extension: possible to try all the routes, although the maximum is predetermined due to the rules
    static ArrayList <char[]>currentBoard=new ArrayList<char[]>();

    // ".X.X" "...." "X.X." ".O.."                       4x4      =>1
    // "XXXXX", "...X.", ".X.X.", "O....", ".X..."       5x5       => 1
    // "XXXXX", "...X.", ".X.X.", "X....", ".O..."       5x5 modify from above a bit =>  0
    // ".X.X..", "XX.X..", "...X..", ".X.X..", ".X.X..", "..O..."  2 (right)  This expose the limitation of the algorithm, stuck in left
    // ".X.X..", "XX.X..", "...X..", ".X.X..", "..O...", "..X..."      6x6   =>2
    // "...X...", ".XX.XX.", ".X.....", "..X.X..", "...X...", "..X.X..", "...O..." 7x7 =>3
    public static void main (String[] args){
        // initialize board
        int numberOfArgs = args.length;
        System.out.println("numberOfArgs=" + numberOfArgs);
        for(int ii=0; ii<numberOfArgs;ii++) {
            checkerBoard.add(args[ii].toCharArray());
            currentBoard.add(args[ii].toCharArray());
        }
        System.out.println("checkerBoard.size()=" + checkerBoard.size());   // assume this is square, if not we need more handling

        // note the algorithm below did not try all the options. But it does check whether we get the maximum steps.
        int steps=0;
        initializePlayers();

        int max_steps=(aPlayer.row)/2;
        System.out.println("max_steps: "+ (max_steps) ) ;
        if(steps<max_steps)
        {
            // how to make sure we always try the largest capture...besides the max_steps below...the issue here is the aPlayer
            // got stuck in one path (did not have opportunity to try other paths)
            // the canMoveLeft and canMoveRight will make aPlayer stuck at one spot, and not necessarily try all the other paths
            while(steps<max_steps && (canMoveTopLeft(aPlayer, bPlayers, checkerBoard) || canMoveTopRight(aPlayer, bPlayers, checkerBoard)))
            {

                 if (canMoveTopRight(aPlayer, bPlayers, checkerBoard)) {
                    moveRight(aPlayer, bPlayers, checkerBoard);
                    aPlayer.print();
                    steps++;

                    if (canMoveTopLeft(aPlayer, bPlayers, checkerBoard)) {
                        moveLeft(aPlayer, bPlayers, checkerBoard);
                        aPlayer.print();
                        steps++;
                    }
                }
                else if (canMoveTopLeft(aPlayer, bPlayers, checkerBoard)) {
                    moveLeft(aPlayer, bPlayers, checkerBoard);
                    aPlayer.print();
                    steps++;

                    if (canMoveTopRight(aPlayer, bPlayers, checkerBoard)) {
                        moveRight(aPlayer, bPlayers, checkerBoard);
                        aPlayer.print();
                        steps++;
                    }
                }
                
            }   // end of while
        }  // end of if
        System.out.println("Steps: "+ steps ) ;
    }

    private static void initializePlayers() {
        Iterator it = checkerBoard.iterator();
        int row=0;
        while(it.hasNext()) {
            char[] obj = (char[]) it.next();
            System.out.println(obj);     // {{'.','X','.','X'},{'.','.','.','.'},{'X','.','X','.'},{'.','O','.','.'}}

            //populate aPlayer and bPlayers
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
    }

    static boolean canMoveTopLeft(Location aPlayer, ArrayList<Location> bPlayers, ArrayList <char[]>checkerBoard)
    {
        // if top left of aPlayer is in boundary (after jump over);
        // if there is an 'X' in the top left
        // if the spot after jump over is . (empty)
        //
        if( (aPlayer.row-2)>=0 && (aPlayer.column-2)>=0 )
        {
            if( ((currentBoard.get(aPlayer.row-1))[aPlayer.column-1])=='X')
            {
                if( ((currentBoard.get(aPlayer.row-2))[aPlayer.column-2])=='.')
                {
                     return true;
                }
            }
        }
        return  false;
    }

    // My mistake : there was some confusion between x and y, then the direction of y
    static boolean canMoveTopRight(Location aPlayer, ArrayList<Location> bPlayers, ArrayList <char[]>checkerBoard)
    {
        // if top right of aPlayer is in boundary (after jump over);
        // if there is an 'X' in the top right
        // if the spot after jump over is . (empty)
        //
        if( (aPlayer.row-2)>=0 && (aPlayer.column+2)<=(checkerBoard.size()-1) )
        {
            if( ((currentBoard.get(aPlayer.row-1))[aPlayer.column+1])=='X')
            {
                if( ((currentBoard.get(aPlayer.row-2))[aPlayer.column+2])=='.')
                {
                    return true;
                }
            }
        }
        return  false;
    }

    private static void moveLeft(Location aPlayer, ArrayList<Location> bPlayers, ArrayList<char[]> checkerBoard) {
        Location loc=new Location();
        loc.row=aPlayer.row-1;
        loc.column=aPlayer.column-1;
        if(bPlayers.contains(loc)) {
            currentBoard.get(loc.row)[loc.column]='.';
            bPlayers.remove(loc);
        }
        currentBoard.get(aPlayer.row)[aPlayer.column]='.';
        aPlayer.row=aPlayer.row-2;
        aPlayer.column=aPlayer.column-2;
        currentBoard.get(aPlayer.row)[aPlayer.column]='O';
    }

    private static void moveRight(Location aPlayer, ArrayList<Location> bPlayers, ArrayList<char[]> checkerBoard) {
        Location loc=new Location();
        loc.row=aPlayer.row-1;
        loc.column=aPlayer.column+1;
        if(bPlayers.contains(loc)) {
            currentBoard.get(loc.row)[loc.column]='.';
            bPlayers.remove(loc);
        }
        currentBoard.get(aPlayer.row)[aPlayer.column]='.';
        aPlayer.row=aPlayer.row-2;
        aPlayer.column=aPlayer.column+2;
        currentBoard.get(aPlayer.row)[aPlayer.column]='O';
    }
}
