package fr.sorbonne;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    boolean [] visited;
    Graph graph ;
    public BFS(Graph graph) {
        visited = new boolean[graph.size()];
        this.graph = graph;
    }

    public Set<Integer> run(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()){
            int e = queue.remove();
            set.add(e);
            graph.getNeighbours(e).stream()
                    .filter(n->!visited[n])
                    .forEach(n->{
                        queue.add(n);
                        visited[n] = true;
                    });
        }
        return set;
    }
}
