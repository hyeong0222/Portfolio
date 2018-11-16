package Assignment1;
////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 1 Part 1 
//  
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 2nd
//
//
//  Directions:  Implement toString method and distanceTo method 
//  Note:        Will need to change return values - added so code initially compiles 
//               
//////////////////////////////////////////////////////////////////////////////////


import java.lang.Math;

public class Point {
    
    private int x;
    private int y;
    
    // Constructor method 
    public Point(int x, int y) {
        // this.x is the x as an instance field 
        // x is just the x local as a parameter to this method 
        this.x = x; 
        this.y = y;
    }
    
    // return this points x value 
    public int x() {
     return this.x;
    }
    
    // return this points y value 
    public int y() {
     return this.y;   
    }
    
    // return distance from this point to other point  
    public double distanceTo(Point other) {
        double dist = Math.sqrt(Math.pow(other.x() - this.x, 2) + (Math.pow(other.y() - this.y, 2)));
        return (double) dist;
    }
    
    // returns the point as a String 
    public String toString() {
        return this.x() + "" + this.y();
    }
 
    // test client 
    public static void main(String[] args) {
        // Instantiating 3 Objects of type Point 
        // Type is a Point - Variable Name - Creating new Point with new keywork 
        Point center = new Point(0,0);
        Point p1 = new Point(5, 10);
        Point p2 = new Point(3, 7);
        
        // creating a variable d1 that is didstance from Point Center to Point p1
        // call objects or class methods with . operator
        // distanceTO takes a parameter of type Point, so we passed in p1
        Double d1 = center.distanceTo(p1);
        
        System.out.println(d1);
    }
}