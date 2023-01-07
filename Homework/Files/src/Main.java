import entities.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.Add("A", "B");
        graph.Add("A", "C");
        graph.Add("B", "C");
        graph.Add("C", "D");
        graph.Add("D", "E");
        graph.Add("E", "B");
        System.out.println(graph.Cycled());
    }
}