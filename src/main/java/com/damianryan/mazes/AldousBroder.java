package com.damianryan.mazes;

public class AldousBroder implements MazeAlgorithm {

    @Override
    public Grid on(Grid grid) {
        Cell cell = grid.randomCell();
        int unvisited = grid.size() - 1;
        while (unvisited > 0) {
            Cell neighbour = cell.neighbours().sample();
            if (neighbour.links().isEmpty()) {
                cell.link(neighbour);
                unvisited -= 1;
            }
            cell = neighbour;
        }
        return grid;
    }
}
