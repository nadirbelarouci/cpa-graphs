package fr.sorbonne;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.valueOf;

public class GraphAdjacencyMatrix implements Graph{
    int [][] graph;

    public GraphAdjacencyMatrix(int size){
        graph = new int[size][size];
    }

    public void addEdge(int i,int j){
        graph[i][j] = 1;
//        graph[j][i] = 1;
    }

    @Override
    public List<Integer> getNeighbours(int i) {
        List<Integer> neighbours = new Vector<>();

        IntStream.range(0,graph.length)
                .parallel()
                .filter(j->graph[i][j]==1)
                .forEach(neighbours::add);

        return neighbours;
    }

    public int size(){
        return graph.length;
    }
}
