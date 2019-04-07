package fr.sorbonne;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Distribution {
    private Path nodeDegreeGraphPath;
    private  Path distributionGraphPath;
    @Test
    public void calculateDistribution() throws Exception {
        String file  = "com-friendster.ungraph.txt";
        distributionGraphPath = Paths.get("distribution-"+file);
        nodeDegreeGraphPath = Paths.get("nodeDegree-" + file);
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);


        Map<Integer, Integer> distribution = forkJoinPool.submit(this::getDistribution).get();

        Files.write(distributionGraphPath,
                (Iterable<String>) distribution.entrySet()
                        .stream()
                        .map(e -> e.getKey() + " " + e.getValue())
                        ::iterator);

    }

    private Map<Integer, Integer> getDistribution() {
        try {
            Map<Integer, Integer> nodesDegree = new ConcurrentHashMap<>();

            Files.lines(nodeDegreeGraphPath)
                    .parallel()
                    .map(this::split)
                    .forEach(uv->nodesDegree.put(uv[0],uv[1]));

            Map<Integer, Integer> distribution = new ConcurrentHashMap<>();
            nodesDegree.values()
                    .parallelStream()
                    .forEach(d -> distribution.merge(d, 1, Integer::sum));

            return distribution;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  int[] split(String line) {
        String[] edge = line.split("\\s+");
        return new int[]{Integer.valueOf(edge[0]), Integer.valueOf(edge[1])};
    }


}
