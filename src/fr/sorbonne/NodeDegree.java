package fr.sorbonne;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

import static fr.sorbonne.Utils.createNewFile;

public class NodeDegree {
    private static Path path;
    private static Path nodeDegreeGraphPath;

    @Test
    public void nodeDegree() throws Exception {
        String file  = "email-Eu-core.txt";
        path = Paths.get(file);
        System.out.println("done");
        nodeDegreeGraphPath = createNewFile("nodeDegree-" + file);


        ForkJoinPool forkJoinPool = new ForkJoinPool(16);

        Map<Integer, Integer> nodesDegree = forkJoinPool.submit(NodeDegree::getNodesDegree).get();
        Files.write(nodeDegreeGraphPath,
                (Iterable<String>) nodesDegree.entrySet().stream().map(e -> e.getKey() + " " + e.getValue())::iterator);
    }

    public static Map<Integer, Integer> getNodesDegree() {
        try {
            Map<Integer, Integer> nodesDegree = new ConcurrentHashMap<>();
            Files.lines(path)
                    .parallel()
                    .map(NodeDegree::split)
                    .forEach(uv -> merge(nodesDegree, uv));

            return nodesDegree;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void merge(Map<Integer, Integer> nodesDegree, int[] uv) {
        nodesDegree.merge(uv[0], 1, Integer::sum);
        nodesDegree.merge(uv[1], 1,Integer::sum);
    }


    public static int[] split(String line) {
        String[] edge = line.split("\\s+");
        return new int[]{Integer.valueOf(edge[0]), Integer.valueOf(edge[1])};
    }
}
