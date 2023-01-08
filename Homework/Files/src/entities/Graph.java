package entities;

import java.util.*;

public class Graph {
    private final HashMap<String, Vertex> vertexPool;
    private boolean cycled;

    public Graph() {
        vertexPool = new HashMap<>();
        cycled = false;
    }

    public void Add(String vertexName) {
        if (vertexPool.containsKey(vertexName)) {
            return;
        }
        vertexPool.put(vertexName, new Vertex(vertexName));
    }

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

    public boolean Cycled() {
        return cycled;
    }

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

    private void Reverse(ArrayList<String> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
    }
}
