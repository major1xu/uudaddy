package com.uudaddy;

// UserDaemonThreadDemo.java
class UserDaemonThreadDemo
{
    public static void main (String [] args)
    {
        if (args.length == 0)
            new MyThread3 ().start ();
        else
        {
            MyThread3 mt = new MyThread3 ();
            mt.setDaemon (true);
            mt.start ();
        }
        try
        {
            Thread.sleep (100);
        }
        catch (InterruptedException e)
        {
        }
    }
}
class MyThread3 extends Thread
{
    public void run ()
    {
        System.out.println ("Daemon is " + isDaemon ());
        while (true);
    }
}