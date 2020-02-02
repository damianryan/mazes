package com.damianryan.mazes;

import java.util.List;
import java.util.stream.Collectors;

public class HuntAndKill implements MazeAlgorithm {

    @Override
    public Grid on(Grid grid) {
        Cell current = grid.randomCell();
        while (null != current) {
            Cell neighbour;
            SampleableList<Cell> unvisitedNeighbours = new SampleableList<>(current.neighbours()
                                                                                   .stream()
                                                                                   .filter(n -> n.links().isEmpty())
                                                                                   .collect(Collectors.toList()));
            if (!unvisitedNeighbours.isEmpty()) {
                neighbour = unvisitedNeighbours.sample();
                current.link(neighbour);
                current = neighbour;
            } else {
                current = null;
            }

            List<Cell> cells = grid.cells().collect(Collectors.toList());
            for (Cell cell : cells) {
                SampleableList<Cell> visitedNeighbours = new SampleableList<>(cell.neighbours()
                                                                                  .stream()
                                                                                  .filter(n -> !n.links().isEmpty())
                                                                                  .collect(Collectors.toList()));
                if (cell.links().isEmpty() && !visitedNeighbours.isEmpty()) {
                    current = cell;
                    neighbour = visitedNeighbours.sample();
                    current.link(neighbour);
                    break;
                }
            }
        }
        return grid;
    }
}
