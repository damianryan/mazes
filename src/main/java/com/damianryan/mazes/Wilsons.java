package com.damianryan.mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Wilsons implements MazeAlgorithm {

    @Override
    public Grid on(Grid grid) {
        SampleableList<Cell> unvisited = new SampleableList<>();
        unvisited.addAll(grid.cells().collect(Collectors.toList()));

        Cell first = unvisited.sample();
        unvisited.remove(first);

        while (!unvisited.isEmpty()) {
            Cell cell = unvisited.sample();
            List<Cell> path = new ArrayList<>();
            path.add(cell);

            while (unvisited.contains(cell)) {
                cell = cell.neighbours().sample();
                int position = path.indexOf(cell);
                if (position >= 0) {
                    path = path.subList(0, position);
                } else {
                    path.add(cell);
                }

                for (int index = 0; index < path.size() - 1; index ++) {
                    path.get(index).link(path.get(index + 1));
                    unvisited.remove(path.get(index));
                }
            }
        }
        return grid;
    }
}
