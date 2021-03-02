package mutlithreads;
import java.util.*;
import java.io.*;

class ThreadID {
	//thread class to keep track of guest ID and counter variable for counting unique visits.
	public static int counter;
	private static volatile int nextID = 0;
	private static class ThreadLocalID extends ThreadLocal<Integer> {
		protected synchronized Integer initialValue() {
			return nextID++;
		}
	}
	private static ThreadLocalID threadID = new ThreadLocalID();
	
		//for ID tracking
	 	public static int get() {
	 		return threadID.get();
	 	}
	 	public static void set(int index) {
	 		threadID.set(index);
	 	}
	 	
	 	//for counter variable counting
	 	public static int retrieve() {
	 		return ThreadID.counter;
	 	}
	 	
	 	public static void set2(int index) {
	 		ThreadID.counter = index;
	 	}
	}

public class Threading extends ThreadID implements Runnable{
	//static variables to be used for both problems.
	public static int cupcake = 1;
	public static int finished = 0;
	public static int problemno;
	public static int numthread;
	public static boolean sign = true;
	
	//method to simulate guest entering vase room
	public synchronized int vaseroom(boolean hasvisited) {
		System.out.println("guest number " + ThreadID.get() + " has entered the vase room");
		//update visit count variable when a new unique guest visits.
		if (hasvisited == false) {
			int visitcount = (ThreadID.retrieve() + 1);
			ThreadID.set2(visitcount);
			//once all guests have been counted end the sequence.
			if(visitcount == numthread) {
				finished = 1;
				System.out.println("All guests have been inside the vase room atleast once.");
			}
		}
		//update sign value to true so another guest may enter.
		sign = true;
		
		return 1;
	}
	
	
	//method to simulate a guest entering the labyrinth
	public synchronized int labyrinth(boolean haseaten) {
		
		System.out.println("guest number " + ThreadID.get() + " has entered the labyrinth");
		//special guest that keeps count of number of guests that passed the labyrinth is set to the thread with ID 0.
		if (ThreadID.get() == 0 && finished == 0) {
			//if theres no cupcake, then update count as it indicates one guest has eaten a cupcake for the first time.
			if (cupcake==0) {
				int newcount = (ThreadID.retrieve() + 1);
				if (newcount == numthread - 1 ) {
					//once all guests have been counted end the sequence and alert the minotaur.
					finished = 1;
					System.out.println("Minoutaur, we have counted all guests.");
				}
				//reset cupcake variable and update count variable
				ThreadID.set2(newcount);
				cupcake = cupcake +1;
				return 2;
			}
		}
		//if a guest enteres the labyrinth and there is a cupcake available and they have not eaten one yet, eat cupcake and return 1 to update boolean array in run method.
		else {
			if (cupcake == 1 && haseaten == false && finished == 0) {
				cupcake = cupcake - 1;
				return 1;
			}
		}
		//if a guest has already eaten a cupcake, leave the labyrinth.
		return 0;
	}
	//run method that implements both solutions
	public void run() {
		//boolean arrays to keep track of guests
		boolean[] haseaten = new boolean[numthread];
		boolean[] hasvisited = new boolean[numthread];
		//variable used to update boolean arrays for both problems.
		int labvar;
		//minotaur birthday cupcake
		if(problemno == 1) {
			while (finished == 0 ) {
				//enter the labyrinth and update array when necessary
				
				labvar = labyrinth(haseaten[ThreadID.get()]);
				if (labvar == 1) {
					haseaten[ThreadID.get()] = true;
				}
				
				try { Thread.sleep(200); } catch(Exception e){}
				
				}
		
		
		}
		//minotaurs crystal vase room
		else {
			while (finished == 0 ) {
				//if sign reads true ("AVAILABLE") then enter the vase room by calling vaseroom method and update boolean array if a guest visits for fist time.
				if (sign == true) {
					sign = false;
					labvar = vaseroom(hasvisited[ThreadID.get()]);
					if (labvar == 1) {
						hasvisited[ThreadID.get()] = true;
						}
					}	
				try { Thread.sleep(200); } catch(Exception e){}
			}
			}
		
		}
	
	public static void main(String[] args) {
		//ask user for which problem they want to run through
		Scanner input = new Scanner(System.in);
		System.out.println("What problem do you want to start? 1 or 2");
		problemno = input.nextInt();
		//ask for the number of guests (N).
		System.out.println("How many guests will there be?");
		numthread = input.nextInt();
		Thread[] thread = new Thread[numthread];
		
		//start tracking time.
		System.out.println("application started");
		long startingTime = System.nanoTime();
		
		//create N threads.
		for(int i=0 ; i < thread.length ; i++) {
			thread[i] = new Thread(new Threading());
			ThreadID.set(i);
			ThreadID.set2(0);
		}
		//start N threads.
		for (int i =0; i < thread.length; i++) {
			thread[i].start();
			try { Thread.sleep(20); } catch(Exception e){}
		}
		//wait for all threads to finish.
		for (int i =0; i < thread.length; i++) {
			
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("application ended");
		
		//display amount of time the code took to complete.
		long endingTime = System.nanoTime();
		System.out.println("total time was: " + (double)((endingTime - startingTime)/1000000000.0) + " seconds");
		input.close();
	}
}
