package com.damianryan.mazes.mains;

import com.damianryan.mazes.*;

public class LongestPath {

    public static void main(String[] args) {
        DistanceGrid grid = new DistanceGrid(10, 10);
        new HuntAndKill().on(grid);
        Cell start = grid.cellAt(0, 0);
        Distances distances = start.distances();
        CellDistance cd = distances.max();
        Cell newStart = cd.getCell();
        Distances newDistances = newStart.distances();
        cd = newDistances.max();
        Cell goal = cd.getCell();
        grid.setDistances(newDistances.pathTo(goal));
        System.out.println(grid);
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
