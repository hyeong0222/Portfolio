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
//  Directions:  provide code for unimplemented methods
//
//               ** The fractions do not need to be in a simplified form **
//               ** without being in simplified form it makes adding and subtracting easier **
//
//////////////////////////////////////////////////////////////////////////////////

public class Fraction {
    // Instance Fields declarations
    private int num; 
    private int denom;
    
    // Constructror - method that initializes class 
    // Paramaters
    // num   - numerator of fraction 
    // denom - denomenator of fraction
    
    public Fraction(int num, int denom) {
         // throw Exception if denominator is 0 
        if (denom == 0) { 
            throw new ArithmeticException("Cannot divide by zero");
        }
        
        this.num = num;
        this.denom = denom; 
    }
    
    // @return value of numerator 
    public int num() {
        return num;
    }
    
    // @return value of denominator 
    public int denom() {
        return denom; 
    }
    
    // add 2 fractions
    public Fraction add(Fraction other) {
        int addNum = (this.num * other.denom) + (other.num * this.denom);
        int addDenom = (this.denom * other.denom);
        return new Fraction(addNum, addDenom); // return statements add initially so file compiles
                                   			  // your will need to change the return statements 
    }
    
    // subtract two fractions 
    public Fraction minus(Fraction other) {
        int minusNum = (this.num * other.denom) - (other.num * this.denom);
        int minusDenom = (this.denom * other.denom);
        return new Fraction(minusNum, minusDenom);
    }
    
    // multiply two fractions 
    public Fraction multiply(Fraction other) {
    		int multiNum = this.num * other.num();
    		int multiDenom = this.denom * other.denom();
        return new Fraction(multiNum, multiDenom);
    }
    
    // divide two fractions 
    public Fraction divide(Fraction other) {
    		int divNum = this.num * other.denom();
    		int divDenom = this.denom * other.num();
    		return new Fraction(divNum, divDenom);
    }
    
    // returns decimal value of this fraction
    public double decimalVal() {
    		return (double) (this.num * 1.0) / (this.denom * 1.0);   
    }
    
    // returns a string with numerator / denominator 
    public String toString() {
        return this.num + " / " + this.denom;
    }
    
    // Test Client 
    public static void main(String[] args) {
        // creating a Fraction object from Class Fraction 
        // also known as in Instance 
        Fraction f1 = new Fraction(5, 10);
        Fraction f2 = new Fraction(1, 3);
        
        // example call of printing the value of two fractions multiplied 
        // f1.multiply(f2) returns a new Fraction object, so we can call its toString() method
        System.out.println(f1.multiply(f2).toString() );
        
    }
}