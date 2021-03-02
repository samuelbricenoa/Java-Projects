package edmondkarps;
import java.util.*;

public class Edmondtest {
	
	//variables for graph that holds capacity values and number of vertices.
	private int [][] capacityedges;
	private int numvertices;
	//constructor to initialize graph and numvertices.
	public Edmondtest(int numvertices) {
		this.numvertices = numvertices;
		this.capacityedges = new int [numvertices][numvertices];
	}
	
	//add edge method
	public void addEdge(int from, int to, int edgecapacity) {
		this.capacityedges[from][to] += edgecapacity;
	}
	
	//method to do breadth first search and compute max flow
	public int Edmonds(int source, int end) {
		//variable initialization
		int nodeId = 0, maxflow = 0;
		//flow 2d array to compute up to date flow
		int flow[][] = new int [numvertices][numvertices];
		//array to keep track of edge chain
		int prev[] = new int [numvertices];
		//boolean array to keep track of visited nodes.
		boolean been[] = new boolean[numvertices];
		boolean found = true;
		//while path to end node found.
		while(found = true) {
			//initialize queue and add source to queue
			Queue<Integer> q = new ArrayDeque<>(numvertices);
			q.add(source);
			found = false; 
			//initualize been array to false
			for (int i= 0; i< numvertices; i++) {
				been[i] = false;
			}
			//set values for source node in been and prev array.
			been[source] = true;
			prev[source] = -1;
			
			//breadth first search implementation
			while(q.size() != 0) {
				nodeId = q.peek();
				q.remove();
				for (int k=0; k<numvertices; k++) {
					//only continue down a path if the capacity allows.
					if ((been[k]== false ) && (capacityedges[nodeId][k] - flow[nodeId][k] > 0 )){
						q.add(k);
						//add node id to been array to stop back flow
						been[k] = true;
						//add to prev array to be able to retrace path later on.
						prev[k] = nodeId;
					
					}
				}
			}
			
			//ford fulkerson method 
			if(been[end]) {	
				int currcalc = 10000;
				int calcflag = 0;
				int index = end;
				//retrace path from end to source and update flow array using smallest value.
				for(int j = 0; j<2; j++) {
					while(prev[index]!= source) {
						if(calcflag == 0 ) {
							currcalc = Math.min((capacityedges[prev[index]][index] - flow[prev[index]][index]), currcalc);
							}
						else {
							flow[prev[index]][index] += currcalc;
						}
						index = prev[index];
					}
					if(calcflag == 0) {
						currcalc = Math.min((capacityedges[prev[index]][index] - flow[prev[index]][index]), currcalc);
						}
					else {
						flow[prev[index]][index] += currcalc;
					}
					
					calcflag = 1;
					index = end;
				}
				//update max flow using min value from path.
				maxflow += currcalc;
				
			}
			//if no path found break out of while loop
			else 
				break;
			}
		
		return maxflow;
		}

public static void main(String args[]) {
	//see how many nodes
	System.out.println("How many nodes in total?");
	//initialize
	Scanner sc = new Scanner(System.in);
	int number_nodes = sc.nextInt();
	Edmondtest test = new Edmondtest(number_nodes);
	System.out.println("Node initialization:");
	//collect adjeacency list to buld graph.
	for (int i = 0; i < number_nodes; i++) {
		System.out.println("How many children does node " + i + " have:");
		int number_roots = sc.nextInt();
		for (int j = 0; j < number_roots; j++) {
			System.out.println("Enter node " + i + "'s child " + (j + 1) + " and the capacity of the edge");
			int childkey = sc.nextInt();
			int weight = sc.nextInt();
			test.addEdge(i, childkey, weight);
		}
	}
	//find source and end node
	System.out.println("What will be your source node?");
	int source = sc.nextInt();
	System.out.println("What will be your end node?");
	int end = sc.nextInt();
	//call edmondskarp algorithm that returns max flow.
	int returnmax = test.Edmonds( source, end);
	System.out.println("Max flow detected was: " + returnmax);
	sc.close();

}

}
	




	
