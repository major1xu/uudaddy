package com.uudaddy;

/**
 * Created by minjiexu on 2/28/18.
 */


public class Location {
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

};
