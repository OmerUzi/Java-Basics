
/**
 * This class include a method that receives as input the triangle's sides lengths from the user.
 * This method calculates the perimeter and the area of the triangle.
 * This method prints the triangle sides, perimeter and area at the end.
 * Omer Uziel
 * 5.11.22
 */

import java.util.Scanner;
public class Triangle1
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area " + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths" + " of the triangle's sides");
        // Input of the three lengths of the triangle's sides 
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        // Calculation of area and perimeter
        int perimeter = a + b + c;
        double s = perimeter * 0.5; 
        double area = Math.sqrt(s * (s-a) * (s-b) *(s-c)); // heron's formula

        //Output
        System.out.println("The lengths of the triangle sides are: " + a + ", " + b + ", " + c + ".");
        System.out.println("The perimeter of the triangle is: " + perimeter);
        System.out.println("The area of the triangle is: " + area);

    } // end of method main
} //end of class Triangle