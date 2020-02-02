package com.damianryan.mazes.mains;

import com.damianryan.mazes.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DeadendCounts {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        List<Class<? extends MazeAlgorithm>> algorithms = Arrays.asList(BinaryTree.class,
                                                                        SideWinder.class,
                                                                        AldousBroder.class,
                                                                        Wilsons.class,
                                                                        HuntAndKill.class);
        int tries = 10;
        int size = 70;
        Map<Class<? extends MazeAlgorithm>, Integer> averages = new HashMap<>();
        for (Class<? extends MazeAlgorithm> algorithm : algorithms) {
            int totalDeadends = 0;
            System.out.println("running " + algorithm.getSimpleName() + "...");
            List<Integer> deadendCounts = new ArrayList<>();
            long start = System.nanoTime();
            for (int i = 0; i < tries; i++) {
                Grid grid = new Grid(size, size);
                algorithm.newInstance().on(grid);
                deadendCounts.add(grid.deadends().size());
            }
            long duration = System.nanoTime() - start;
            totalDeadends += deadendCounts.stream().mapToInt(i -> i).sum();
            averages.put(algorithm, totalDeadends / deadendCounts.size());
            System.out.println(tries + " tries took " + TimeUnit.NANOSECONDS.toMillis(duration) + "ms");
        }

        int totalCells = size * size;
        System.out.println("average dead ends per " + size + "x" + size + " maze (" + totalCells + " cells):");
        algorithms.sort((o1, o2) -> averages.get(o2) - averages.get(o1));
        for (Class<? extends MazeAlgorithm> algorithm : algorithms) {
            int percentage = averages.get(algorithm) * 100 / totalCells;
            System.out.println(algorithm.getSimpleName() +
                               ": " +
                               averages.get(algorithm) +
                               "/" +
                               totalCells +
                               " (" +
                               percentage +
                               "%)");
        }
    }
}
