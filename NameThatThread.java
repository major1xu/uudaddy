// NameThatThread.java
class NameThatThread
{
    public static void main (String [] args)
    {
        MyThread1 mt;
        if (args.length == 0)
            mt = new MyThread1 ();
        else
            mt = new MyThread1 (args [0]);
        mt.start ();
    }
}
class MyThread1 extends Thread
{
    MyThread1 ()
    {
        // The compiler creates the byte code equivalent of super ();
    }
    MyThread1 (String name)
    {
        super (name); // Pass name to Thread superclass
    }
    public void run ()
    {
        System.out.println ("My name is: " + getName ());
    }
}