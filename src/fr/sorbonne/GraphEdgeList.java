package fr.sorbonne;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GraphEdgeList implements Graph{
    Set<Edge> edges = ConcurrentHashMap.newKeySet();

    @Override
    public void addEdge(int i, int j) {
        edges.add(new Edge(i,j));
    }

    @Override
    public int size() {
        return edges.size();
    }

    @Override
    public List<Integer> getNeighbours(int i) {
        return edges.stream()
                .filter(e->e.u == i)
                .map(e->e.v)
                .collect(Collectors.toList());
    }
}
