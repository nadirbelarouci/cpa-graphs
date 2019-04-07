package fr.sorbonne;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.valueOf;

public class GraphAdjacencyList implements Graph{
    List<Integer> [] graph;

    public GraphAdjacencyList(int size) {
        graph = new List[size];
    }

//    @Override
//    public Set<Integer> getNodes() {
//        Set<Integer> set = ConcurrentHashMap.newKeySet();
//         IntStream.range(0,graph.size())
//                .parallel()
//                .filter(i->graph.get(i)!=null)
//                .forEach(set::add);
//         return set;
//    }

    public void addEdge(int i, int j){
        List<Integer> list = graph[i];

        if(list == null){
            list = new ArrayList<>();
            graph[i] = list;
        }
        list.add(j);
    }

    @Override
    public List<Integer> getNeighbours(int i) {
        List<Integer> list = graph[i];
        return list == null? Collections.emptyList():list;
    }

    public int size(){
        return graph.length;
    }
}
