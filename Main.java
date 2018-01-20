package com.uudaddy;
// https://www.javaworld.com/article/2074217/java-concurrency/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html
// ThreadDemo.java
class Main
{
    public static void main (String [] args)
    {
        MyThread mt = new MyThread ();
        mt.start ();
        for (int i = 0; i < 50; i++)
            System.out.println ("i = " + i + ", i * i = " + i * i);
    }
}
class MyThread extends Thread
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
