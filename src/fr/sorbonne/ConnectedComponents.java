package fr.sorbonne;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {
    static Map<String,Integer> sizes = new HashMap<>();
    static {
        sizes.put("email-Eu-core.txt",1005);
        sizes.put("com-amazon.ungraph.txt",334863);
        sizes.put("com-lj.ungraph.txt",3997962);
        sizes.put("com-orkut.ungraph.txt",3072441);
        sizes.put("com-friendster.ungraph.txt",65608366);
    }
    Graph graph;

    @Test
    public void calculateConnectedCompoenents()throws Exception{
        String file = "com-orkut.ungraph.txt";
        graph = new GraphAdjacencyList(sizes.get(file)+1);
        Set<Integer> nodes = Graph.fromFile(file,graph);

        BFS bfs = new BFS(graph);
       nodes.stream()
                .filter(u -> !bfs.visited[u])
                .map(bfs::run)
                .forEach(scc-> System.out.println(scc.size()));
    }

}
