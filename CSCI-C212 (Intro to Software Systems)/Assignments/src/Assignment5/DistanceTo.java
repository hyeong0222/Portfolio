////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;


public class DistanceTo implements Comparable<DistanceTo> {
	
	private String target;
	private int distance;
	
	// Constructor
	public DistanceTo (String city, int dist) { 
		target = city; 
		distance = dist;
	}
	
	// get target value
	public String getTarget () { 
		return target;
	} 
	// get distance value
	public int getDistance () {
		return distance;
	} 
	
	// returns the distance between two cities.
	public int compareTo (DistanceTo other) {
        return distance - other.distance;
    }
	
}