// ThreadDemo.java

package com.uudaddy;


class ThreadDemo
{
    public static void main (String [] args)
    {
        MyThread0 mt = new MyThread0 ();
        mt.start ();
        for (int i = 0; i < 50; i++)
            System.out.println ("i = " + i + ", i * i = " + i * i);
    }
}
class MyThread0 extends Thread
{
    public void run ()
    {
        for (int count = 1, row = 1; row < 20; row++, count++)
        {
            for (int i = 0; i < count; i++)
                System.out.print ('*');
            System.out.print ('\n');
        }
    }
}