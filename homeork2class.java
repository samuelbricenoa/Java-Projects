package homework2;
//import scanner.
import java.util.Scanner;
public class homeork2class {
	
	
	public static void main(String[] args) {
	//declare variables, 3 for the input values, one for the sum, and one more for the average.
	int num1, num2, num3;
	int outputsum;
	float outputnum;
	
	Scanner inputStuff = new Scanner(System.in);
	
	//ask user to input 3 values.
	System.out.printf("Enter your three numbers:");
	
	//scan in 3 integers.
	num1 = inputStuff.nextInt();
	num2 = inputStuff.nextInt();
	num3 = inputStuff.nextInt();
	
	//calculates the sum of the 3 numbers and then the average.
	outputsum = (num1 + num2 + num3);
	outputnum = outputsum /3;
	
	//outputs all 3 integers that were inputed.
	System.out.printf("The three numbers are:%d,%d, and %d.%n", num1, num2, num3);
	
	//outputs the sum of the 3 integers.
	System.out.printf("The sum of the numbers is:%d%n", outputsum);
	
	//outputs the average of the 3 integers.
	System.out.printf("The average of the numbers is: %.2f", outputnum);
	
	}
}


