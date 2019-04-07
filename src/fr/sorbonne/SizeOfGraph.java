package fr.sorbonne;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class SizeOfGraph {
    public static Set<String> getNodes(Path path, AtomicLong edgeCount) {
        try {
            Set<String> nodes = ConcurrentHashMap.newKeySet();
            Files.lines(path)
                    .parallel()
                    .map(s -> split(s, edgeCount))
                    .flatMap(Stream::of)
                    .forEach(nodes::add);
            return nodes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] split(String line, AtomicLong count) {
        count.incrementAndGet();
        return line.split("\\s+");
    }

    @Test
    public void sizeOfGraph() throws Exception {

//        AtomicLong edgeCount = new AtomicLong(0);
//
//        Set<String> nodes = forkJoinPool.submit(() -> getNodes(edgeCount)).get();
        Path path = Paths.get("undirected-com-orkut.ungraph.txt");

        AtomicLong edgeCount = new AtomicLong();
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);

        Set<String> nodes = forkJoinPool.submit(() -> getNodes(path, edgeCount)).get();
        System.out.println("-------------------------------");
        System.out.println("File: " + path.getFileName());
        System.out.println("Nodes count: " + nodes.size());
        System.out.println("Edge count: " + edgeCount);
        System.out.println("-------------------------------");

    }


}
