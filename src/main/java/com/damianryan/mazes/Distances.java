package com.damianryan.mazes;

import java.util.HashMap;
import java.util.Map;

public class Distances {

    private Cell root;
    private Map<Cell, Integer> cells;

    public Distances(Cell root) {
        this.root = root;
        cells = new HashMap<>();
        cells.put(root, 0);
    }

    public Integer distance(Cell cell) {
        return cells.get(cell);
    }

    public void setDistance(Cell cell, int distance) {
        cells.put(cell, distance);
    }

    public SampleableList<Cell> cells() {
        return new SampleableList<>(cells.keySet());
    }

    public Distances pathTo(Cell goal) {
        Cell current = goal;
        Distances breadcrumbs = new Distances(root);
        breadcrumbs.setDistance(current, distance(current));
        while (!current.equals(root)) {
            SampleableList<Cell> links = current.links();
            for (Cell neighbour : links) {
                if (distance(neighbour) < distance(current)) {
                    breadcrumbs.setDistance(neighbour, distance(neighbour));
                    current = neighbour;
                }
            }
        }
        return breadcrumbs;
    }

    public CellDistance max() {
        int maxDistance = 0;
        Cell maxCell = root;

        for (Map.Entry<Cell, Integer> cellDistance : cells.entrySet()) {
            if (cellDistance.getValue() > maxDistance) {
                maxCell = cellDistance.getKey();
                maxDistance = cellDistance.getValue();
            }
        }

        return new CellDistance(maxCell, maxDistance);
    }
}
