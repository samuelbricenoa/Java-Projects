package raggedarray;

//import scanner
import java.util.Scanner;

public class raggedarray {
	public static void main(String[] args) {
		
		//declare 2d array with 25 students max.
		int [][] studentarray = new int[25][];
		Scanner inputStufff = new Scanner(System.in);
		
		//variable for counting number of students with grades.
		int currentstudent = 0;
		
		//variable for switch statement.
		int navigate;
		
		//variable to count the maximum number of exams for all students.
		int numexams2 = 0;
		
		// do while loop that repeats the menu untill 5 is entered.
		do {
			//print menu
			System.out.println("1: Input grades for next student");
			System.out.println("2: Exam average by student");
			System.out.println("3: Exam average by exam");
			System.out.println("4: Class average for all exams");
			System.out.println("5: Leave");
			System.out.println("Enter navigation number:");
			
			//input navigation number for menu.
			navigate = inputStufff.nextInt();
			
		//switch statement based off navigation number.	
		switch(navigate) {
		
		//option 1 is to enter grades for the enxt student.
		case 1: {
			
			//ask user to input number of exams
			System.out.println("Enter how many exams the student has taken:");
			int numexams = inputStufff.nextInt();
			
			//if number of exams is greater than numexams2 then numexams2 is updated to reflect larger number.
			if(numexams2<numexams)
				numexams2 = numexams;
			
			//creates collumn for student depending on number of exams.
			studentarray[currentstudent]=new int[numexams];
			System.out.println("Enter the exam grades:");
			
			//input exam grade into array.
			for(int i=0;i<numexams;i++)
			{
			studentarray[currentstudent][i]=inputStufff.nextInt();
			}
			//update number of students with grades.
			currentstudent++;
			
			break;
			}
		
		//option 2 is to calculate grade average for each student.
		case 2: {
			
			//initial for loop loops through each student id (the rows section of the array)
			System.out.println("Student ID : average");
			for(int i=0;i<currentstudent;i++)
			{
			int total = 0;
			System.out.printf("%d : ", i);
			
			//for each row, loop through columns to create total
			for(int j=0;j<studentarray[i].length;j++)
			{
			total+=studentarray[i][j];
			}
			
			//once total is calculated, convert to double and divide by array length to get average.
			double average = total * 1.0/studentarray[i].length;
			//print out average.
			System.out.printf("%.2f%n",average);
			}
			System.out.println();
			
		
			break;
		}
		//option 3 is to calulate average of each exam
		case 3: {
			System.out.println("Exam Number : average");
			
			//initial for loop uses numexams2 as it dictates the maximum number of exams a student has taken.
			for(int i=0;i<numexams2;i++)
			{
			//total exams variable is for each exam ID.
			int sum = 0,totalexams = 0;
			System.out.printf("%d : ", i+1);
			
			//second for loop runs through each student and adds to total
			for(int j=0; j<currentstudent; j++)
			{	
				//second for loop runs through each student only if they have space in the columns section.
				if(studentarray[j].length >= i+1)
				{
					totalexams++;
					sum += studentarray[j][i];
				}
			}
			
			//calculate average for each exam then output with 2 decimal place accuracy.
			double average = sum * 1.0/totalexams;
			System.out.printf("%.2f%n",average);
			}
			System.out.println("");	
			break;
		}
		//option 4 is to calculate the total average for the entire class for all exams.
		case 4: {
			int sumtotal = 0;
			//initial for loop runs through each student.
			for(int i=0; i<currentstudent; i++)
			{
			int sum = 0;
			
			//second for loop runs through each students grades and adds them together.
			for(int j=0; j<studentarray[i].length; j++)
			{
			sum+=studentarray[i][j];
			}
			double average = sum * 1.0/studentarray[i].length;
			sumtotal += average;
			}
			//using the average for each student. calculate the average for the entire class.
			double averageTotal = sumtotal * 1.0/currentstudent;
			System.out.println("Current class Average for all exams: " + averageTotal);
			System.out.println("");	
			break;
		}
		//for 5, exit the switch statement and the while loop.
		case 5: {
			System.out.println("You have exited the menu.");
			break;
		}
		//for any other number ask user to try again and break.
		default: {
			System.out.println("Invalid navigation number, Try again");
		}
		
		}
		
	}while (navigate!= 5);

	}
}
		
