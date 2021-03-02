package beerproject1;
//import scanner.
import java.util.Scanner;
public class Beerassignment {
	public static void main(String[] args) {
		
		//declare variables for number of beers and the cost per beer.
		float numbeers, beercost;
		
		Scanner inputStuff = new Scanner(System.in);
		
		//ask user for amount of beers consumed daily.
		System.out.printf("On average, how many beers will you consume each day?");
		
		//scan in number into numbeers variable.
		numbeers = inputStuff.nextFloat();
		
		//ask user for amount payed for each beer can.
		System.out.printf("On average, how much will you pay for each can of beer?");
		
		//scan in number into beercost variable.
		beercost = inputStuff.nextFloat();
		
		//calculate the number of beers consumed per year by multiplying numbeers by 365 days. Then print.
		System.out.printf("That is approximately %.2f beers in one year.%n", numbeers*365);
		
		//calculate the number of calories by multiplying daily beers by 365 days, and multiplying again by 150 calories per beer. Then print.
		System.out.printf("In one year, you will consume approximately  %.2f calories from beer alone. %n", numbeers*365*150);
		
		//calculate the amount of pounds gained by multiplying numbeers by 15 since weight gain scales linearly and one beer daily is 15 pounds gained.
		System.out.printf("Without diet or exercise to counter these calories, you can expect to gain %.2f pounds from drinking that much beer this year. %n", numbeers*15);
		
		//calculate the ammount payed by the user each year on beer by multiplying beercost with numbeers.
		System.out.printf("In one year, you will spend approximately $%.2f on beer.%n", numbeers*beercost*365);
		
	}

}
