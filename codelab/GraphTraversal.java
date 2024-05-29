import java.util.*;

public class GraphTraversal {
    private Map<Integer, List<Integer>> graph;

    public GraphTraversal() {
        graph = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public List<Integer> bfs(int start) {
        List<Integer> bfsOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsOrder.add(node);
            
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        
        return bfsOrder;
    }

    public List<Integer> dfs(int start) {
        List<Integer> dfsOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            
            if (!visited.contains(node)) {
                visited.add(node);
                dfsOrder.add(node);
                
                List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
                Collections.sort(neighbors, Collections.reverseOrder()); // Sort in reverse order to maintain the correct order of traversal
                
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        
        return dfsOrder;
    }

    public static void main(String[] args) {
        GraphTraversal graphTraversal = new GraphTraversal();
        
        // Adding edges based on the provided graph
        graphTraversal.addEdge(0, 1);
        graphTraversal.addEdge(0, 4);
        graphTraversal.addEdge(0, 5);
        graphTraversal.addEdge(1, 2);
        graphTraversal.addEdge(1, 5);
        graphTraversal.addEdge(2, 3);
        graphTraversal.addEdge(2, 5);
        graphTraversal.addEdge(4, 5);

        int startNode = 3;

        // Performing BFS and DFS starting from node 3
        System.out.println("BFS Order: " + graphTraversal.bfs(startNode));
        System.out.println("DFS Order: " + graphTraversal.dfs(startNode));
    }
}
