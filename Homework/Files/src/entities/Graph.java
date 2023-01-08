package entities;

import java.util.*;

/***
 * Class of the graph.
 */
public class Graph {
    /***
     * Pool of vertexes matching the name of the vertex and its object of Vertex class.
     */
    private final HashMap<String, Vertex> vertexPool;
    /***
     * Inform whether the graph is cycled.
     */
    private boolean cycled;

    /***
     * Constructor.
     */
    public Graph() {
        vertexPool = new HashMap<>();
        cycled = false;
    }

    /***
     * Add vertex to the pool.
     * @param vertexName Name of the vertex.
     */
    public void Add(String vertexName) {
        if (vertexPool.containsKey(vertexName)) {
            return;
        }
        vertexPool.put(vertexName, new Vertex(vertexName));
    }

    /***
     * Add graph edge.
     * @param from Beginning of the edge.
     * @param to End of the edge.
     */
    public void Add(String from, String to) {
        if (cycled) {
            return;
        }
        if (!vertexPool.containsKey(from)) {
            vertexPool.put(from, new Vertex(from));
        }
        if (!vertexPool.containsKey(to)) {
            vertexPool.put(to, new Vertex(to));
        }
        var departure = vertexPool.get(from);
        var destination = vertexPool.get(to);
        cycled |= departure.AddChildren(destination);
        if (cycled) {
            return;
        }
        destination.AddParent(departure);
    }

    /***
     * Inform the 'external world' whether the graph is cycled.
     * @return 'true' if the graph is cycled and 'false' otherwise.
     */
    public boolean Cycled() {
        return cycled;
    }

    /***
     * With modified BFS sets up ordered list of the files in the system.
     * @return Ordered list of the files in the system.
     */
    public LinkedList<String> Order() {
        LinkedList<String> order = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> toVisit = new LinkedList<>();
        for (var vertex : vertexPool.entrySet()) {
            if (!vertex.getValue().HasAncestors()) {
                toVisit.add(vertex.getKey());
            }
        }
        BFS(order, visited, toVisit);
        return order;
    }

    /***
     * Recursive modified BFS.
     * @param order Order of visited vertexes.
     * @param visited Set of visited vertexes (to check existence faster).
     * @param toVisit List of vertexes to visit.
     */
    private void BFS(LinkedList<String> order, HashSet<String> visited, LinkedList<String> toVisit) {
        if (toVisit.isEmpty()) {
            return;
        }
        var currentVertex = toVisit.getFirst();
        toVisit.removeFirst();
        visited.add(currentVertex);
        for (var child : vertexPool.get(currentVertex).GetChildren()) {
            if (!visited.contains(child.name)) {
                if (toVisit.contains(child.name)) {
                    toVisit.removeFirstOccurrence(child.name);
                }
            } else {
                order.removeFirstOccurrence(child.name);
                visited.remove(child.name);
            }
            toVisit.add(child.name);
        }
        order.addLast(currentVertex);
        BFS(order, visited, toVisit);
    }
}
