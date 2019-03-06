/**
 * https://www.javaworld.com/article/2074217/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html
 * Created by minjiexu on 11/18/17.
 */
// CalcPI1.java
class CalcPI1
{
    public static void main (String [] args)
    {
        MyThread mt = new MyThread ();
        mt.start ();
        //while (mt.isAlive ()) {
            try {
                Thread.sleep(2); // Sleep for 10 milliseconds
            } catch (InterruptedException e) {
            }
            System.out.println("pi = " + mt.pi);
        //}
    }
}
class MyThread extends Thread
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

