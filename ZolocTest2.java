
package com.uudaddy;

import java.util.*;

public class ZolocTest2 {
    public static int solution(String[] B){

        if(B==null || B.length<3) return 0;

        int size = B.length;
        int jrow=0;
        int jcol=0;

        //find the position of that white checker
        for(int i=0; i<size; i++){
            String str = B[i];
            boolean find = false;
            for(int j=0; j<str.length(); j++){
                char c = str.charAt(j);
                if(c=='O'){
                    jrow=i;
                    jcol=j;
                    break;
                }
            }
            if(find==true) break;
        }
        System.out.println("position O:" +jrow +", " + jcol);

        //I created a Position class to hold the checker's position with (x, y).
        Position jp = new Position(jrow, jcol);

        // The white checker can only goes up.
        if(jrow < 2) return 0;

        //data structure to hold all the Positions for each potential moves.
        List<List<Position>> listAll = new ArrayList<List<Position>>();

        //add the first white checker position into a list;
        List<Position> list = new ArrayList<Position>();
        list.add(jp);

        //then add this list into the listAll.
        listAll.add(list);

        for(int i=0; i< listAll.size(); i++){
            System.out.println("i=: "+i);
            ArrayList<Position> pre = (ArrayList<Position>) listAll.get(i);
            for(int j=0; j<pre.size(); j++){
                System.out.println("j=: "+j);
                Position p = pre.get(j);
                int r=p.row;
                int c=p.col;
                System.out.println("r and c: "+r+", " + c);
                List<Position> tmp = new ArrayList<Position>();

                if(r-2>=0 && c-2>=0){
                    char lc = B[r-1].charAt(c-1);
                    char lcu =B[r-2].charAt(c-2);
                    System.out.println("lc:lcu: "+lc+", "+lcu);
                    if(lc=='X' && lcu=='.'){
                        Position tmpp = new Position(r-2,c-2);
                        tmp.add(tmpp);
                        System.out.println("tmpp: "+tmpp.row + ", "+tmpp.col);
                    }

                }
                if(r-2>=0 && c+2<B[r-2].length()){
                    char lc = B[r-1].charAt(c+1);
                    char lcu =B[r-2].charAt(c+2);
                    System.out.println("lc2:lcu2: "+lc+", "+lcu);
                    if(lc=='X' && lcu=='.'){
                        Position tmpp = new Position(r-2,c+2);
                        tmp.add(tmpp);
                        System.out.println("tmpp: "+tmpp.row + ", "+tmpp.col);
                    }
                }
                if(tmp.size()>0){
                    listAll.add(tmp);
                }

            }
        }
        return listAll.size()-1;  // ???

    }

    static class Position{
        int row;
        int col;
        Position(int row, int col){
            this.row = row;
            this.col=col;
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] B = {  "...X...",".XX.XX.",".X.....","..X.X..","...X...","..X.X..","...O..."
                //        ".X.X..", "XX.X..", "...X..", ".X.X..", ".X.X..", "..O..."
        };
//                {"..........",
//		              "XXXXXXXXXX",
//		              "..........",
//		              "XXX...XXXX",
//		              ".....O.XXX"};
        int size = solution(B);
        System.out.println("the steps: " + size);


    }

}
