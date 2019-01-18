package com.uudaddy;

/**
 * Created by minjiexu on 11/23/18.
 * Use a comparator so that I can customize the sorting order
 * http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
 *
 */
public class Digit implements Comparable<Digit>{

    private int quantity;

    public Digit(int quantity) {
        super();
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int compareTo(Digit compareFruit) {

        int compareQuantity = ((Digit) compareFruit).getQuantity();

        //ascending order
        //return this.quantity - compareQuantity;

        //descending order
        return compareQuantity - this.quantity;

    }
}
