package dijkstraalg;

import static java.lang.Math.min;
import java.util.*;

public class MainDJ {

	public static class Edge {
		int to, pathweight;

		public Edge(int to, int pathweight) {
			this.to = to;
			this.pathweight = pathweight;
		}
	}

	private int numvertices;
	private int[] nodeweights;
	private List<List<Edge>> graph;

	public MainDJ(int numvertices) {
		this.numvertices = numvertices;
		graph = new ArrayList<>(numvertices);
		for (int i = 0; i < numvertices; i++)
			graph.add(new ArrayList<>());
	}

	public void addEdge(int from, int to, int pathweight) {
		graph.get(from).add(new Edge(to, pathweight));
	}

	public Integer[] dijkstra(int source) {
		//initialize priority queue
		MinIndexedBHeap<Integer> pq = new MinIndexedBHeap<>(numvertices);
		//array for printing path later
		Integer[] chain = new Integer[numvertices];
		//keeps track of nodes weve been to
		int [] been = new int[numvertices];
		//keeps track of optimal node weights so far.
		nodeweights = new int[numvertices];
		
		// init-single source
		Arrays.fill(nodeweights, Integer.MAX_VALUE);
		nodeweights[source] = 0;
		for (int i = 0; i < numvertices; i++) {
			pq.insert(i, nodeweights[i]);
			been[i] = 0;
		}
		//sets source node to have distance 0
		pq.update(source, 0);

		while (!pq.isEmpty()) {
			//get smallest node and delete it.
			int nodeId = pq.peekMinKeyIndex();
			pq.delete(nodeId);
			been[nodeId] = 1;
			//for each edge in adjacency list, relax the edge.
			for (Edge edge : graph.get(nodeId)) {
				if(been[edge.to]==1) {
					continue;
				}
				int newnodeweights = nodeweights[nodeId] + edge.pathweight;
				if (newnodeweights < nodeweights[edge.to]) {
					nodeweights[edge.to] = newnodeweights;
					chain[edge.to] = nodeId;
					pq.decrease(edge.to, newnodeweights);
				}
			}
		}
		//return print array.
		return chain;
	}
	//print shortest path function
	public void printshortestPath(int source) {
		//initialize path variable and calls dijkstras function
		List<Integer> path = new ArrayList<>();
		Integer[] chain = dijkstra(source);
		//for each node use use the return array to print out shortest path from source.
		for (int i = 0; i < numvertices; i++) {
			path.clear();
			//if node still set to infinity, then it has no path from source node.
			if (nodeweights[i] == Integer.MAX_VALUE) {
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

	private static class MinIndexedBHeap<T extends Comparable<T>> {

		// Current number of elements in the heap.
		private int sz;

		// Maximum number of elements in the heap.
		private final int N;

		// The degree of every node in the heap.
		private final int D;

		// Lookup arrays to track the child/parent indexes of each node.
		private final int[] child, parent;

		// The Position Map (pm) maps Key Indexes (ki) to where the position of that
		// key is represented in the priority queue in the domain [0, sz).
		public final int[] pm;

		// The Inverse Map (im) stores the indexes of the keys in the range
		// [0, sz) which make up the priority queue. It should be noted that
		// 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
		public final int[] im;

		// The values associated with the keys. It is very important to note
		// that this array is indexed by the key indexes (aka 'ki').
		public final Object[] values;

		// Initializes a binary heap with a maximum capacity of maxSize.
		public MinIndexedBHeap(int maxSize) {

			D = 2;
			N = maxSize;

			im = new int[N];
			pm = new int[N];
			child = new int[N];
			parent = new int[N];
			values = new Object[N];

			for (int i = 0; i < N; i++) {
				parent[i] = (i - 1) / D;
				child[i] = i * D + 1;
				pm[i] = im[i] = -1;
			}
		}

		public int size() {
			return sz;
		}

		public boolean isEmpty() {
			return sz == 0;
		}

		public boolean contains(int ki) {
			keyInBoundsOrThrow(ki);
			return pm[ki] != -1;
		}

		public int peekMinKeyIndex() {
			isNotEmptyOrThrow();
			return im[0];
		}

		public int pollMinKeyIndex() {
			int minki = peekMinKeyIndex();
			delete(minki);
			return minki;
		}

		@SuppressWarnings("unchecked")
		public T peekMinValue() {
			isNotEmptyOrThrow();
			return (T) values[im[0]];
		}

		public T pollMinValue() {
			T minValue = peekMinValue();
			delete(peekMinKeyIndex());
			return minValue;
		}

		public void insert(int ki, int nodeweights) {
			if (contains(ki))
				throw new IllegalArgumentException("index already exists; received: " + ki);
			valueNotNullOrThrow(nodeweights);
			pm[ki] = sz;
			im[sz] = ki;
			values[ki] = nodeweights;
			swim(sz++);
		}

		@SuppressWarnings("unchecked")
		public T valueOf(int ki) {
			keyExistsOrThrow(ki);
			return (T) values[ki];
		}

		@SuppressWarnings("unchecked")
		public T delete(int ki) {
			keyExistsOrThrow(ki);
			final int i = pm[ki];
			swap(i, --sz);
			sink(i);
			swim(i);
			T value = (T) values[ki];
			values[ki] = null;
			pm[ki] = -1;
			im[sz] = -1;
			return value;
		}

		@SuppressWarnings("unchecked")
		public T update(int ki, T value) {
			keyExistsAndValueNotNullOrThrow(ki, value);
			final int i = pm[ki];
			T oldValue = (T) values[ki];
			values[ki] = value;
			sink(i);
			swim(i);
			return oldValue;
		}

		// Strictly decreases the value associated with 'ki' to 'value'
		public void decrease(int ki, T value) {
			keyExistsAndValueNotNullOrThrow(ki, value);
			if (less(value, values[ki])) {
				values[ki] = value;
				swim(pm[ki]);
			}
		}

		// Strictly increases the value associated with 'ki' to 'value'
		public void increase(int ki, T value) {
			keyExistsAndValueNotNullOrThrow(ki, value);
			if (less(values[ki], value)) {
				values[ki] = value;
				sink(pm[ki]);
			}
		}

		/* Helper functions */

		private void sink(int i) {
			for (int j = minChild(i); j != -1;) {
				swap(i, j);
				i = j;
				j = minChild(i);
			}
			
		}

		private void swim(int i) {
			while (less(i, parent[i])) {
				swap(i, parent[i]);
				i = parent[i];
			}
		}

		// From the parent node at index i find the minimum child below it
		private int minChild(int i) {
			int index = -1, from = child[i], to = min(sz, from + D);
			for (int j = from; j < to; j++)
				if (less(j, i))
					index = i = j;
			return index;
		}

		private void swap(int i, int j) {
			pm[im[j]] = i;
			pm[im[i]] = j;
			int tmp = im[i];
			im[i] = im[j];
			im[j] = tmp;
		}

		// Tests if the value of node i < node j
		@SuppressWarnings("unchecked")
		private boolean less(int i, int j) {
			return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
		}

		@SuppressWarnings("unchecked")
		private boolean less(Object obj1, Object obj2) {
			return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
		}

		@Override
		public String toString() {
			List<Integer> lst = new ArrayList<>(sz);
			for (int i = 0; i < sz; i++)
				lst.add(im[i]);
			return lst.toString();
		}

		/* Helper functions to make the code more readable. */

		private void isNotEmptyOrThrow() {
			if (isEmpty())
				throw new NoSuchElementException("Priority queue underflow");
		}

		private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
			keyExistsOrThrow(ki);
			valueNotNullOrThrow(value);
		}

		private void keyExistsOrThrow(int ki) {
			if (!contains(ki))
				throw new NoSuchElementException("Index does not exist; received: " + ki);
		}

		private void valueNotNullOrThrow(Object value) {
			if (value == null)
				throw new IllegalArgumentException("value cannot be null");
		}

		private void keyInBoundsOrThrow(int ki) {
			if (ki < 0 || ki >= N)
				throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
		}
	}

	public static void main(String args[]) {
		//see how many nodes
		System.out.println("How many nodes in total?");
		//initialize
		Scanner sc = new Scanner(System.in);
		int number_nodes = sc.nextInt();
		MainDJ test = new MainDJ(number_nodes);
		System.out.println("Node initialization:");
		//collect adjeacency list to buld graph.
		for (int i = 0; i < number_nodes; i++) {
			System.out.println("How many children does node " + i + " have:");
			int number_roots = sc.nextInt();
			for (int j = 0; j < number_roots; j++) {
				System.out.println("Enter node " + i + "'s child " + (j + 1) + " and the weight of the edge");
				int childkey = sc.nextInt();
				int weight = sc.nextInt();
				test.addEdge(i, childkey, weight);
			}
		}
		//find source.
		System.out.println("What will be your source node?");
		//find time taken for algorithm.
		long startTime = System.currentTimeMillis();
		int source = sc.nextInt();
		//print shortest path for each node from source using print function.
		test.printshortestPath(source);
		long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println(" Total Time in milli seconds: "+ totalTime);
		
		sc.close();

	}

}