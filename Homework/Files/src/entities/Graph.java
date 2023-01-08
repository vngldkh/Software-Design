package entities;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
    private final HashMap<String, Vertex> vertexPool;
    private boolean cycled;

    public Graph() {
        vertexPool = new HashMap<>();
        cycled = false;
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

    public ArrayList<String> Order() {
        ArrayList<String> order = new ArrayList<>();
        HashSet<String> toVisit = new HashSet<>(vertexPool.keySet());
        while (!toVisit.isEmpty()) {
            String head = null;
            for (String vertex : toVisit) {
                if (!vertexPool.get(vertex).HasAncestors()) {
                    head = vertex;
                    break;
                }
            }
            AddFiles(head, order, toVisit);
        }
        Reverse(order);
        return order;
    }

    private void AddFiles(String current, ArrayList<String> order, HashSet<String> pool) {
        if (!pool.contains(current)) {
            return;
        }
        order.add(current);
        pool.remove(current);
        if (!vertexPool.get(current).HasChildren()) {
            return;
        }
        for (var vertex : vertexPool.get(current).GetChildren()) {
            AddFiles(vertex.name, order, pool);
        }
    }

    private void Reverse(ArrayList<String> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
    }
}
