package com.damianryan.mazes;

public class CellDistance {

    private Cell cell;
    private int distance;

    public CellDistance(Cell cell, int distance) {
        this.cell = cell;
        this.distance = distance;
    }

    public Cell getCell() {
        return cell;
    }

    public int getDistance() {
        return distance;
    }
}
