package com.damianryan.mazes.mains;

import com.damianryan.mazes.BinaryTree;
import com.damianryan.mazes.Cell;
import com.damianryan.mazes.DistanceGrid;
import com.damianryan.mazes.Distances;

public class Dijkstra {

    public static void main(String[] args) {
        DistanceGrid grid = new DistanceGrid(5, 5);
        new BinaryTree().on(grid);
        Cell start = grid.cellAt(0, 0);
        Distances distances = start.distances();
        grid.setDistances(distances);
        System.out.println(grid);
        System.out.println("path from northwest corner to southwest corner:");
        grid.setDistances(distances.pathTo(grid.cellAt(grid.rowCount() - 1, 0)));
        System.out.println(grid);
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
