package fr.sorbonne;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.valueOf;

public interface Graph {
    static Set<Integer> fromFile(String s,Graph graph)throws Exception {
        Set<Integer> nodes = new HashSet<>();
         Files.lines(Paths.get(s))
                 .map(line->Graph.process(line,graph))
                 .flatMapToInt(IntStream::of)
                .forEach(nodes::add);
         return nodes;
    }
    static int [] process(String line,Graph graph){
        String [] s = line.split("\\s+");
       int [] e = { valueOf(s[0]),valueOf(s[1])};
       graph.addEdge(e[0],e[1]);
       return e;
    }
    void addEdge(int i, int j);
    default void addEdge(String edge){
        String [] s = edge.split("\\s+");
        addEdge(valueOf(s[0]),valueOf(s[1]));
    }
    int size();

    List<Integer> getNeighbours(int i);


}
