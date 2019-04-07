package fr.sorbonne;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class SpecialQuantity {
    private  Path nodeDegreeGraphPath;
    private  Path path;

    @Test
    public void calculateSpecialQuantity() throws Exception {
        String file  = "com-friendster.ungraph.txt";
        path = Paths.get(file);
        nodeDegreeGraphPath = Paths.get("nodeDegree-" + file);

        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        long sum = forkJoinPool.submit(this::getSpecialQuantity).get();

        System.out.println("-------------------------------");
        System.out.println("File: " + path.getFileName());
        System.out.println("Special Quantity: " + sum);
        System.out.println("-------------------------------");
    }
    private  long getSpecialQuantity() {

        try {
            AtomicLong sum = new AtomicLong();
            Map<Integer, Integer> nodesDegree = new ConcurrentHashMap<>();

            Files.lines(nodeDegreeGraphPath)
                    .parallel()
                    .map(this::split)
                    .forEach(uv->nodesDegree.put(uv[0],uv[1]));

            System.out.println("done");
             Files.lines(path)
                    .parallel()
                    .map(this::split)
                    .mapToLong(uv -> nodesDegree.get(uv[0]) * nodesDegree.get(uv[1]))
                    .forEach(sum::addAndGet);
             return sum.get();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  int[] split(String line) {
        String[] edge = line.split("\\s+");
        return new int[]{Integer.valueOf(edge[0]), Integer.valueOf(edge[1])};
    }
}
