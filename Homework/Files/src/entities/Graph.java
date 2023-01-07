package entities;

import java.util.HashMap;
import java.util.ArrayList;

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
            return;
        }
        if (!vertexPool.containsKey(to)) {
            return;
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
        ArrayList<String> answer = new ArrayList<>();
        return answer;
    }
}
