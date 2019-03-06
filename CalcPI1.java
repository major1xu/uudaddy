/**
 * https://www.javaworld.com/article/2074217/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html
 * Created by minjiexu on 11/18/17.
 *
 * To demonstrate sleep(long millis), I've written a CalcPI1 application. That application starts a new
 * thread that uses a mathematic algorithm to calculate the value of the mathematical constant pi.
 * While the new thread calculates, the starting thread pauses for 10 milliseconds by calling sleep
 * (long millis). After the starting thread awakes, it prints the pi value, which the new thread stores
 * in variable pi.
 */
// CalcPI1.java

package com.uudaddy;


class CalcPI1
{
    public static void main (String [] args)
    {
        MyThread2 mt = new MyThread2 ();
        mt.start ();
        //while (mt.isAlive ()) {
            try {
                Thread.sleep(10); // Sleep for 10 milliseconds
            } catch (InterruptedException e) {
            }
            System.out.println("pi = " + mt.pi);
        //}
    }
}
class MyThread2 extends Thread
{
    boolean negative = true;
    double pi; // Initializes to 0.0, by default
    public void run ()
    {
        for (int i = 3; i < 100000; i += 2)
        {
            if (negative)
                pi -= (1.0 / i);
            else
                pi += (1.0 / i);
            negative = !negative;
        }
        pi += 1.0;
        pi *= 4.0;
        System.out.println ("Finished calculating PI");
    }
}

