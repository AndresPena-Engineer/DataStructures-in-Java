package dijkstra;

//ANDRES PENA
//LAB 4
//11-6-2017
//CS 302

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Vector;
 
 
public class Dijkstra {
    private static final Graph.Edges[] GRAPH = {
      new Graph.Edges("0", "1", 4),
      new Graph.Edges("0", "7", 8),
      new Graph.Edges("1", "7", 11),
      new Graph.Edges("1", "2", 8),
      new Graph.Edges("7", "8", 7),
      new Graph.Edges("7", "6", 1),
      new Graph.Edges("8", "2", 2),
      new Graph.Edges("8", "6", 6),
      new Graph.Edges("2", "3", 7),
      new Graph.Edges("2", "5", 4),
      new Graph.Edges("6", "5", 2),
      new Graph.Edges("3", "5", 14),
      new Graph.Edges("3", "4", 9),
      new Graph.Edges("5", "4", 10),
   };
   private static final String START = "0";
   private static final String END = "4";
   
 public static void main(String[] args) {
      Graph g = new Graph(GRAPH);
      g.dijkstra(START);
      g.printPath(END);
      //g.printAllPaths();
      {
        LongestPathinDAG lp = new LongestPathinDAG();
        /*
         * find a longest path from vertex 0 to vertex n-1.
         */
        if (lp.findLongestPath(0, lp.n - 1, 1))
            System.out.println("Longest Path is " + lp
                    + " and the distance is " + lp.bestDistance);
        else
            System.out.println("No path from v0 to v" + (lp.n - 1));
        /*
         * find a longest path from vertex 3 to vertex 5.
         */
        if (lp.findLongestPath(3, 5, 1))
            System.out.println("Longest Path is " + lp
                    + " and the distance is " + lp.bestDistance);
        else
            System.out.println("No path from v3 to v5");
        /*
         * find a longest path from vertex 5 to vertex 3.
         */
        if (lp.findLongestPath(lp.n - 1, 3, 1))
            System.out.println("Longest Path is " + lp
                    + " and the distance is " + lp.bestDistance);
        else
            System.out.println("No path from v5 to v3");
    }
   }
 
}
 
class Graph {
   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Edges {
      public final String v1, v2;
      public final int dist;
      public Edges(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
      static {
          System.out.println("The shortest path is");
      }
   }
 
   /** One vertex of the graph, complete with mappings to neighbouring vertices */
  public static class Vertex implements Comparable<Vertex>{
	public final String name;
	public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();
 
	public Vertex(String name)
	{
		this.name = name;
	}
 
	private void printPath()
	{
		if (this == this.previous)
		{
			System.out.printf("%s", this.name);
		}
		else if (this.previous == null)
		{
			System.out.printf("%s(unreached)", this.name);
		}
		else
		{
			this.previous.printPath();
			System.out.printf(" -> %s(%d)", this.name, this.dist);
		}
	}
 
        @Override
	public int compareTo(Vertex other)
	{
		if (dist == other.dist)
			return name.compareTo(other.name);
 
		return Integer.compare(dist, other.dist);
	}
 
	@Override public String toString()
	{
		return "(" + name + ", " + dist + ")";
	}
}
 
   /** Builds a graph from a set of edges */
   public Graph(Edges[] edges) {
      graph = new HashMap<>(edges.length);
 
      //one pass to find all vertices
      for (Edges e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      //another pass to set neighbouring vertices
      for (Edges e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
   /** Runs dijkstra using a specified source vertex */ 
   public void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
 
       // set-up vertices
       graph.values().stream().map((v) -> {
           v.previous = v == source ? source : null;
           return v;
       }).map((v) -> {
           v.dist = v == source ? 0 : Integer.MAX_VALUE;
           return v;
       }).forEachOrdered((v) -> {
           q.add(v);
       });
 
      dijkstra(q);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   public void printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
         return;
      }
 
      graph.get(endName).printPath();
      System.out.println();
   }
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
       graph.values().stream().map((v) -> {
           v.printPath();
           return v;
       }).forEachOrdered((_item) -> {
           System.out.println();
       });
   }
   class Node
{
    int             name; // node ID, started from 0 to n-1
    Vector<Integer> preds; // predecessors (String)
    Vector<Integer> neibs; // neighbors (String)
    Vector<Integer> backs; // backward edges -node is end vertex (Integer)
    Vector<Integer> fors; // forward edges -node is start vertex (Integer)
    int             pNode; // previous node on the augmenting path
    int             pEdge; // from which edge this node comes on the augmenting
                           // path
 
    public Node(int id)
    {
        name = id;
        backs = new Vector<>();
        fors = new Vector<>();
        pNode = -1;
        pEdge = -1;
    }
}
 
class Edge
{
    int name;    // edge ID, started from 0 to n-1
    int start;   // start vertex of this edge
    int end;     // end vertex of this edge
    int direct;  // forwards (+1) or backwards (-1) on augmenting path
                  // if 0 then not part of augmenting path
    int capacity; // capacity
    int flow;    // current flow
 
    public Edge(int id)
    {
        name = id;
        start = -1;
        end = -1;
        direct = 0; // default is neither
        capacity = 0;
        flow = 0;
    }
 
    @Override
    public String toString()
    {
        return name + ": s=" + start + " e=" + end + " d=" + direct;
    }
}
 
public class LongestPathinDAG
{
    int    n;                      // number of nodes
    int    target;                 // destination node
    int    minLength;              // the minimal length of each path
    Node[] v;                      // used to store Nodes
    Edge[] e;                      // used to store Edges
    int[]  path;                   // used to store temporary path
    int    length       = 0;       // length of the path
    int    distance     = 0;       // distance of the path
    int[]  bestPath;               // used to store temporary path
    int    bestLength   = 0;       // length of the longest path
    int    bestDistance = -1000000; // distance of the longest path
    int[]  visited;                // used to mark a node as visited if set as
                                    // 1
 
    public LongestPathinDAG()
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number of vertices: ");
            n = sc.nextInt();
            System.out.println("Enter the number of edges: ");
            int m = sc.nextInt();
            v = new Node[n];
            e = new Edge[m];
            System.out.println(n + " nodes and " + m + " edges.");
            
            for (int i = 0; i < n; i++)
                v[i] = new Node(i);
            int i = 0;
            while (i < e.length)
            {
                Edge edge = new Edge(i);
                int sVal = sc.nextInt();
                edge.start = sVal;// sc.nextInt();
                int eVal = sc.nextInt();
                edge.end = eVal;// sc.nextInt();
                edge.capacity = sc.nextInt();
                System.out.println(" edge: " + edge.start + " - " + edge.end
                        + " : " + edge.capacity);
                edge.flow = 0;
                e[i] = edge;
                v[sVal].fors.add(i);
                v[eVal].backs.add(i);
                i++;
                if (i == m)
                    break;
            }
            visited = new int[v.length];
            path = new int[v.length];
            bestPath = new int[v.length];
        }
    }
 
    /*
     * this function looks for a longest path starting from being to end,
     * using the backtrack depth-first search.
     */
    public boolean findLongestPath(int begin, int end, int minLen)
    {
        /*
         * compute a longest path from begin to end
         */
        target = end;
        bestDistance = -100000000;
        minLength = minLen;
        dfsLongestPath(begin);
        return bestDistance != -100000000;
    }
 
    private void dfsLongestPath(int current)
    {
        visited[current] = 1;
        path[length++] = current;
        if (current == target && length >= minLength)
        {
            if (distance > bestDistance)
            {
                System.arraycopy(path, 0, bestPath, 0, length);
                bestLength = length;
                bestDistance = distance;
            }
        }
        else
        {
            Vector<Integer> fors = v[current].fors;
            for (int i = 0; i < fors.size(); i++)
            {
                Integer edge_obj = fors.elementAt(i);
                int edge = edge_obj;
                if (visited[e[edge].end] == 0)
                {
                    distance += e[edge].capacity;
                    dfsLongestPath(e[edge].end);
                    distance -= e[edge].capacity;
                }
            }
        }
        visited[current] = 0;
        length--;
    }
 
    @Override
    public String toString()
    {
        String output = "v" + bestPath[0];
        for (int i = 1; i < bestLength; i++)
            output = output + " -> v" + bestPath[i];
        return output;
    }
 }
}