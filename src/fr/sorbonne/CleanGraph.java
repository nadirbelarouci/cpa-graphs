package fr.sorbonne;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

import static fr.sorbonne.Utils.createNewFile;

public class CleanGraph {
    private static Path path;
    private static Path undirectedGraphPath;

    public static Set<Edge> getEdges() {
        try {
            Set<Edge> edges = ConcurrentHashMap.newKeySet();
            Files.lines(path)
                    .parallel()
                    .map(Edge::valueOf)
                    .forEach(edges::add);

            return edges;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cleanGraph() throws Exception {
        path = Paths.get("email-Eu-core.txt");
        undirectedGraphPath = createNewFile("undirected-" + path.getFileName());

        ForkJoinPool forkJoinPool = new ForkJoinPool(16);


        Set<Edge> edges = forkJoinPool.submit(CleanGraph::getEdges).get();
        System.out.println("done");
        Files.write(undirectedGraphPath, (Iterable<String>) edges.stream().map(Edge::toString)::iterator);
    }


}
