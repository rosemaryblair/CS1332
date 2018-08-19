import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Rosemary Blair
 * @userid rblair8
 * @GTID 903359318
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Cannot have null inputs.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex not in graph.");
        }

        Set<Vertex<T>> known = new HashSet<Vertex<T>>(); //for visited vertices
        List<Vertex<T>> list = new ArrayList<Vertex<T>>(); //return result
        Map<Vertex<T>, List<VertexDistance<T>>> aList = graph.getAdjList();

        dfs(start, aList, known, list);

        return list;
    }

    /**
     * Recursive helper method to search a graph in depth-based order.
     * Since method is called in for-loop, vertex passed in won't be null.
     *
     * @param <T> the generic typing of data
     * @param curr the current vertex in graph in search
     * @param aList the map representing adjacency list of graph
     * @param known the set containing visited vertices
     * @param list the list of vertices to add as the graph is searched
     */
    private static <T> void dfs(Vertex<T> curr, Map<Vertex<T>,
        List<VertexDistance<T>>> aList, Set<Vertex<T>> known,
        List<Vertex<T>> list) {

        list.add(curr); //add current vertex to list to return
        known.add(curr); //add current vertex to known (for keeping track)

        for (VertexDistance<T> v : aList.get(curr)) {
            if (!known.contains(v.getVertex())) { //if not already visited
                dfs(v.getVertex(), aList, known, list); //depth-order search
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Cannot have null inputs.");
        } else if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex not in graph.");
        }

        Map<Vertex<T>, Integer> map = new HashMap<>(); //return result
        Map<Vertex<T>, List<VertexDistance<T>>> aList = graph.getAdjList();
        Queue<VertexDistance<T>> pq = new PriorityQueue<>(); //queue of dist's
        Set<Vertex<T>> known = new HashSet<Vertex<T>>(); //for visited vertices

        for (Vertex<T> v : aList.keySet()) {
            if (v.equals(start)) {
                map.put(v, 0); //source has distance 0
            } else {
                map.put(v, Integer.MAX_VALUE); //all others infinite
            }
        }

        pq.add(new VertexDistance<T>(start, 0));
        //terminates when queue is empty OR when all are visited
        while (!pq.isEmpty() && known.size() != graph.getVertices().size()) {
            VertexDistance<T> curr = pq.remove();
            if (!known.contains(curr.getVertex())) {
                map.put(curr.getVertex(), curr.getDistance());
                known.add(curr.getVertex());
                for (VertexDistance<T> v : aList.get(curr.getVertex())) {
                    if (map.get(v.getVertex()) > (v.getDistance() 
                        + curr.getDistance())) {
                        map.put(v.getVertex(), (v.getDistance() 
                            + curr.getDistance()));
                        pq.add(new VertexDistance<T>(v.getVertex(),
                            (v.getDistance() + curr.getDistance())));
                    }
                }
            }
        }
        return map;
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Cannot use null as input.");
        }

        Set<Edge<T>> mst = new HashSet<Edge<T>>();
        DisjointSet<Vertex<T>> vertices = new DisjointSet<>(
            graph.getAdjList().keySet());
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        for (Edge<T> edge : graph.getEdges()) {
            pq.add(edge);
        }

        while (!pq.isEmpty() && mst.size() <= (2 * graph.getVertices()
            .size() - 1)) {
            Edge<T> curr = pq.poll();
            if (curr == null) {
                return null;
            }
            Vertex<T> u = curr.getU();
            Vertex<T> v = curr.getV();
            if (vertices.find(u) != vertices.find(v)) {
                mst.add(curr);
                mst.add(new Edge<T>(v, u, curr.getWeight())); //reverse
                vertices.union(u, v);
            }
        }
        if (2 * (graph.getVertices().size() - 1) != mst.size()) {
            return null;
        }
        return mst;
    }
}