package hw5array;
import java.util.Arrays;
import java.util.Scanner;
public class arrayclass {

	public static void main(String[] args) {
		int[] array = new int [100];
		Scanner inputStuff = new Scanner(System.in);
		for(int i=0; i<100; i++) {
			array[i] = (int) (Math.random()*100+1);
		}
		printarray(array);
		Arrays.sort(array);
		printarray(array);
		System.out.println("Enter a number between 1 and 100 to find in the array.");
		int findnum = inputStuff.nextInt();
		searcharray(findnum, array);
		numarray(array);
		averagearray(array);
		highestnumarray(array);
		lowestnumarray(array);
		
		
	}

public static void printarray(int array[]) {
	for(int i=0; i<array.length; i++)
		System.out.println(array[i]);
}

public static void searcharray(int num, int array[]) {
	int found = 0;
	for(int i=0;i<array.length;i++) {
		if(array[i]==num)
			found ++;
	}
	if(found==0) 
		System.out.printf("Number was not found%n");
	
	else
		System.out.printf("Number was found%n");
}

public static void numarray( int array[]) {
	for(int i=1; i<=100; i++)
	{
	System.out.printf("%d: ",i);
	int count = 0;
		for(int j=0; j<array.length; j++)
		{
			if(array[j]==i)
				count++;
		}
		System.out.printf("%d%n", count);
	}
}

public static void averagearray( int array[]) {
	int total = 0;
	for(int average: array) {
		total+= average;
	}
	System.out.printf("The average is %d%n", total/array.length);
}

public static void highestnumarray(int array[]) {
	System.out.printf("The largest number in the array is %d%n", array[99]);
}

public static void lowestnumarray(int array[]) {
	System.out.printf("The smallest number in the array is %d%n", array[0]);
}

}
