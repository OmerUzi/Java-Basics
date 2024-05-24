
/**
 * This class receives 3 integers as input and check wether the three integers can represent triangle.
 * If the integers can represent a valid triangle it prints the type of the triangle.
 * Omer Uziel
 * 5.11.22
 */

import java.util.Scanner;
public class Triangle2
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("Please enter the three lengths" + " of the triangle's sides");
        // Input of the three lengths of the triangle's sides 
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        // If the lengths are equal to 0 or negative, the triangle is invalid.
        if ((a <= 0) || (b <= 0) || (c <= 0)) 
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " cannot represent a triangle");
        // Sum of 2 triangle's sides must be bigger than the third side
        else if ((a >= b + c) || (b >= a + c) || (c >= a + b)) 
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " cannot represent a triangle");
        // Check if all the triangle's sides is equal
        else if ((a == b) && (b == c)) 
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " represent an equilateral triangle"); 
        // Check if 2 of the the triangle's sides is equal 
        else if ((a == b) || (a == c) || (b == c)) 
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " represent an isosceles triangle");
        // Check if it is a right-angle triangle by Pythagorean theorem
        else if ((a == Math.sqrt((b * b) + (c * c))) || (b == Math.sqrt((a * a) + (c * c))) || (c == Math.sqrt((b * b) + (a * a))))  
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " represent a right-angle triangle"); 
        // it is a common triangle
        else 
            System.out.println("The numbers: " + a + ", " +  b +  " and " + c + " represent a common triangle");  
    } // end of method main
} //end of class Triangle