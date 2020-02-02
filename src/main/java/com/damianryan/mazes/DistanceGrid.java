package com.damianryan.mazes;

public class DistanceGrid extends Grid {

    protected Distances distances;

    public DistanceGrid(int rows, int columns) {
        super(rows, columns);
    }

    public void setDistances(Distances distances) {
        this.distances = distances;
    }

    @Override
    public String contentsOf(Cell cell) {
        if (null != distances && null != distances.distance(cell)) {
            return Integer.toString(distances.distance(cell), 36);
        } else {
            return super.contentsOf(cell);
        }
    }
}
