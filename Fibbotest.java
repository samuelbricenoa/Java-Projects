package dijkstraalg;
import java.util.*;
import org.graphstream.algorithm.util.FibonacciHeap;
import org.graphstream.algorithm.util.FibonacciHeap.Node;

public class Fibbotest {
	
	  private int numvertices;
	  private int[] nodeweight2;
	  private FibonacciHeap.Node fibNode[];
      private List<List<Edge2>> graph;
      
      public Fibbotest(int numvertices) {
    	  this.numvertices = numvertices;
    	  graph = new ArrayList<>(numvertices);
    	  for (int i = 0; i < numvertices; i++)
    		  graph.add(new ArrayList<>());
      }
	  
      public static class Edge2 {
    	  int to, pathweight;
    	  
    	  public Edge2(int to, int pathweight) {
    		  this.to = to;
    		  this.pathweight = pathweight;
    	  }
      }

      public void addEdge2(int from, int to, int pathweight) {
    	  graph.get(from).add(new Edge2(to, pathweight));
      }
		  
      public Integer[] DijkstraFib (int source) {
    	  //initialize priority queue
    	  FibonacciHeap<Integer, Integer> pq = new FibonacciHeap<>();
    	  //array for printing path later
    	  Integer[] chain = new Integer[numvertices];
    	  nodeweight2 = new int[numvertices];
    	  //Initialize nodes for the FibnonacciHeap library
    	  fibNode = new FibonacciHeap.Node[numvertices];
    	  
    	  //Init single source.
    	  Arrays.fill(nodeweight2, Integer.MAX_VALUE);
    	  nodeweight2[source] = 0;
    	  fibNode[0] = pq.add(0, source);
    	  for (int i = 1; i < numvertices; i++) {
    		  fibNode[i] = pq.add(nodeweight2[i], i);
    	  }
    	  
    	  while (!pq.isEmpty()) {
    		  //get smallest node and delete it.
  			int nodeId = pq.getMinValue();
  			pq.extractMin();
  			//for each edge in adjacency list, relax the edge
  			for (Edge2 edge : graph.get(nodeId)) {
  				int newDist = nodeweight2[nodeId] + edge.pathweight;
  				if (newDist < nodeweight2[edge.to]) {
  					nodeweight2[edge.to] = newDist;
  					chain[edge.to] = nodeId;
  					if((int)(fibNode[edge.to].getValue()) > newDist){
  						pq.decreaseKey( fibNode[edge.to], newDist);
  					}
  				}
  			}
  		}
    	  //return print array. 
    	  return chain;
      }
    //print shortest path function
      public void printShortestPath(int source) {
    	//initialize path variable and calls dijkstras function
  		List<Integer> path = new ArrayList<>();
  		Integer[] chain = DijkstraFib(source);
  	//for each node use use the return array to print out shortest path from source.
  		for (int i = 0; i < numvertices; i++) {
  			path.clear();
  		//if node still set to infinity, then it has no path from source node.
  			if (nodeweight2[i] == Integer.MAX_VALUE) {
  				System.out.println("Node " + i + " has no path from source node.");
  				continue;
  			}
  		//adds node from print array to path array list
  			for (Integer index = i; index != null; index = chain[index])
  				path.add(index);
  		//reverses path 
  			Collections.reverse(path);
  		//prints out path for each node.
  			System.out.println("Path from " + source + " to " + i + ":" + path);
  		}

		  

		   
}

      public static void main(String args[]) {
    	//see how many nodes
  		System.out.println("How many nodes in total?");
  		//initialize
  		Scanner sc = new Scanner(System.in);
  		int number_nodes = sc.nextInt();
  		Fibbotest test2 = new Fibbotest(number_nodes);
  		System.out.println("Node initialization:");
  		//collect adjeacency list to buld graph.
  		for (int i = 0; i < number_nodes; i++) {
  			System.out.println("How many children does node " + i + " have:");
  			int number_roots = sc.nextInt();
  			for (int j = 0; j < number_roots; j++) {
  				System.out.println("Enter node " + i + "'s child " + (j + 1) + " and the weight of the edge");
  				int childkey = sc.nextInt();
  				int weight = sc.nextInt();
  				test2.addEdge2(i, childkey, weight);
  			}
  		}
  		//find source.
  		System.out.println("What will be your source node?");
  		//find time taken for algorithm.
  		long startTime = System.currentTimeMillis();
  		int source = sc.nextInt();
  	//print shortest path for each node from source using print function.
  		test2.printShortestPath(source);
  		sc.close();
  		long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println(" Total Time in milli seconds: "+ totalTime);

  	}

      
      
}
