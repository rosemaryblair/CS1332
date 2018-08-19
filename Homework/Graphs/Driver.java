import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Iterator;

public class Driver {

	public static void main(String[] args) {

		Graph<Integer> directed = createDirectedGraph();
		Graph<Character> undirected = createUndirectedGraph();
		
		System.out.println("\n -- Tests -- \n");

      //Krugals Test
    Set<Edge<Character>> mstActual = GraphAlgorithms.kruskals(
          undirected);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

      boolean t1 = (mstActual.equals(edges));

        System.out.println("Assert Equals: " + t1);
        System.out.println("Size comparison: " + mstActual.size() + " vs. " + edges.size());

        System.out.println();

        Iterator<Edge<Character>> itr1 = mstActual.iterator();
        Iterator<Edge<Character>> itr2 = edges.iterator();
        while (itr1.hasNext() && itr2.hasNext()) {
          Edge<Character> e = itr1.next();
          Edge<Character> f = itr2.next();
          System.out.println("Answered: " + e);
          System.out.println("Solution: " + f);
          System.out.println();
        }

    //     //DFS Test

    // Map<Vertex<Integer>, List<VertexDistance<Integer>>> adj = directed.getAdjList();
    // for (Vertex<Integer> vtx : adj.keySet()) {
    //   System.out.println("Vertex " + vtx + ":");
    //   List<VertexDistance<Integer>> vtxd = adj.get(vtx);
    //   for (VertexDistance<Integer> vd : vtxd) {
    //     System.out.println(vd);
    //   }
    //   System.out.println();
    // }
    // System.out.println();
    // List<Vertex<Integer>> dfsActual = GraphAlgorithms.depthFirstSearch(
    //  new Vertex<Integer>(5), directed);
    // List<Vertex<Integer>> dfsExpected = new LinkedList<>();
    // dfsExpected.add(new Vertex<Integer>(5));
    //     dfsExpected.add(new Vertex<Integer>(4));
    //     dfsExpected.add(new Vertex<Integer>(6));
    //     dfsExpected.add(new Vertex<Integer>(7));

    //     System.out.println("DFS matches solution? " + dfsActual.equals(dfsExpected));
    //     System.out.println("Size is " + dfsActual.size() + ", should equal " 
    //      + dfsExpected.size() + ".\n");

    //     int i = 0;
    //     for (Vertex<Integer> v : dfsActual) {
    //      System.out.println("Answered: " + v.toString());
    //      System.out.println("Solution: " + dfsExpected.get(i));
    //      System.out.println();
    //      i++;
    //     }

		  //   //Dijkstras Test
    // Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
    //     new Vertex<Character>('D'), undirected);
    //     Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
    //     dijkExpected.put(new Vertex<>('A'), 4);
    //     dijkExpected.put(new Vertex<>('B'), 4);
    //     dijkExpected.put(new Vertex<>('C'), 2);
    //     dijkExpected.put(new Vertex<>('D'), 0);
    //     dijkExpected.put(new Vertex<>('E'), 1);
    //     dijkExpected.put(new Vertex<>('F'), 7);

    //     System.out.println("Dijik matches solution? " + dijkActual.equals(dijkExpected));
    //     System.out.println("Size is " + dijkActual.size() + ", should equal " 
    //      + dijkExpected.size() + ".\n");

    //     for (Vertex<Character> v : dijkActual.keySet()) {
    //      System.out.println("Output: " + v.toString());
    //     }
    //     System.out.println();
    //     for (Vertex<Character> u : dijkExpected.keySet()) {
    //      System.out.println("Expected: " + u.toString());
    //     }
        


        // for (Edge<Character> e : mstActual) {
        // 	System.out.println("Answered: " + e.toString());
        // }
        // System.out.println();
        // for (Edge<Character> f : edges) {
        // 	System.out.println("Solution: " + f.toString());
        // }

		System.out.println();

	}




	//DFS Test
		// List<Vertex<Integer>> dfsActual = GraphAlgorithms.depthFirstSearch(
		// 	new Vertex<Integer>(5), directed);
		// List<Vertex<Integer>> dfsExpected = new LinkedList<>();
		// dfsExpected.add(new Vertex<Integer>(5));
  //       dfsExpected.add(new Vertex<Integer>(4));
  //       dfsExpected.add(new Vertex<Integer>(6));
  //       dfsExpected.add(new Vertex<Integer>(7));

  //       System.out.println("DFS matches solution? " + dfsActual.equals(dfsExpected));
  //       System.out.println("Size is " + dfsActual.size() + ", should equal " 
  //       	+ dfsExpected.size() + ".\n");

  //       int i = 0;
  //       for (Vertex<Integer> v : dfsActual) {
  //       	System.out.println("Answered: " + v.toString());
  //       	System.out.println("Solution: " + dfsExpected.get(i));
  //       	System.out.println();
  //       	i++;
  //       }

		// //Dijkstras Test
		// Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
  //       new Vertex<Character>('D'), undirected);
  //       Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
  //       dijkExpected.put(new Vertex<>('A'), 4);
  //       dijkExpected.put(new Vertex<>('B'), 4);
  //       dijkExpected.put(new Vertex<>('C'), 2);
  //       dijkExpected.put(new Vertex<>('D'), 0);
  //       dijkExpected.put(new Vertex<>('E'), 1);
  //       dijkExpected.put(new Vertex<>('F'), 7);

  //       System.out.println("Dijik matches solution? " + dijkActual.equals(dijkExpected));
  //       System.out.println("Size is " + dijkActual.size() + ", should equal " 
  //       	+ dijkExpected.size() + ".\n");

  //       for (Vertex<Character> v : dijkActual.keySet()) {
  //       	System.out.println("Output: " + v.toString());
  //       }
  //       System.out.println();
  //       for (Vertex<Character> u : dijkExpected.keySet()) {
  //       	System.out.println("Expected: " + u.toString());
  //       }

	private static Graph<Integer> createDirectedGraph() {
		Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
		for (int i = 1; i <= 7; i++) {
			vertices.add(new Vertex<Integer>(i));
		}
		Set<Edge<Integer>> edges = new HashSet<Edge<Integer>>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 0));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 0));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(6), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(4), 0));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(7), 0));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(6), 0));

        return new Graph<Integer>(vertices, edges);
	}

	private static Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 70; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 8));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 6));

     	return new Graph<Character>(vertices, edges);
    }

}