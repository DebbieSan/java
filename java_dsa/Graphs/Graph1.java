import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph1 {

    private static final String[] vertices = {
            "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p"
    };

    private static final Map<String, List<String>> graph = new LinkedHashMap<>();

    public static void main(String[] args) {
        buildGraph();

        System.out.println("Adjacency List:");
        printAdjacencyList();

        System.out.println("\nAdjacency Matrix:");
        printAdjacencyMatrix();

        System.out.println("\nDFS starting at g:");
        Set<String> visited = new HashSet<>();
        List<String> dfsResult = new ArrayList<>();
        dfs("g", visited, dfsResult);
        System.out.println(dfsResult);

        System.out.println("\nBFS starting at b:");
        List<String> bfsResult = bfs("b");
        System.out.println(bfsResult);

        System.out.println("\nPath that uses each edge once in each direction:");
        System.out.println("Note: This path may be different from the written answer because");
        System.out.println("DFS can choose available edges in different orders. The important");
        System.out.println("thing is that each directed edge is used exactly once.");

        List<String> edgePath = new ArrayList<>();
        Set<String> usedDirectedEdges = new HashSet<>();
        edgePath.add("g");
        edgeDFS("g", usedDirectedEdges, edgePath);
        System.out.println(edgePath);

        System.out.println("\nNumber of directed edges used: " + usedDirectedEdges.size());
        System.out.println("Expected number of directed edges: 40");
    }

    private static void buildGraph() {
        for (String v : vertices) {
            graph.put(v, new ArrayList<>());
        }

        addEdge("a", "b");
        addEdge("a", "e");
        addEdge("a", "f");
        addEdge("b", "c");
        addEdge("c", "d");
        addEdge("c", "f");
        addEdge("d", "g");
        addEdge("e", "i");
        addEdge("f", "j");
        addEdge("g", "h");
        addEdge("g", "j");
        addEdge("g", "k");
        addEdge("h", "o");
        addEdge("i", "j");
        addEdge("i", "m");
        addEdge("i", "n");
        addEdge("k", "o");
        addEdge("l", "p");
        addEdge("n", "o");
        addEdge("o", "p");

        for (String v : vertices) {
            Collections.sort(graph.get(v));
        }
    }

    private static void addEdge(String u, String v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    private static void printAdjacencyList() {
        for (String v : vertices) {
            System.out.println(v + " -> " + graph.get(v));
        }
    }

    private static void printAdjacencyMatrix() {
        System.out.print("   ");
        for (String v : vertices) {
            System.out.print(v + " ");
        }
        System.out.println();

        for (String row : vertices) {
            System.out.print(row + "  ");
            for (String col : vertices) {
                if (graph.get(row).contains(col)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    private static void dfs(String current, Set<String> visited, List<String> result) {
        visited.add(current);
        result.add(current);

        for (String neighbour : graph.get(current)) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, visited, result);
            }
        }
    }

    private static List<String> bfs(String start) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            for (String neighbour : graph.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }

    private static void edgeDFS(String current, Set<String> usedDirectedEdges, List<String> path) {
        for (String neighbour : graph.get(current)) {
            String edge = current + "->" + neighbour;

            if (!usedDirectedEdges.contains(edge)) {
                usedDirectedEdges.add(edge);
                path.add(neighbour);

                edgeDFS(neighbour, usedDirectedEdges, path);
            }
        }
    }
}
