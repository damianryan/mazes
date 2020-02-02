package com.damianryan.mazes;

import java.util.*;

public class Cell {

    private int row;
    private int column;
    private Cell north;
    private Cell south;
    private Cell east;
    private Cell west;
    private Map<Cell, Boolean> links;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        links = new HashMap<>();
    }

    public Cell link(Cell cell) {
        return link(cell, true);
    }

    public Cell link(Cell cell, boolean bidirectional) {
        links.put(cell, true);
        if (bidirectional) {
            cell.link(this, false);
        }
        return this;
    }

    public Cell unlink(Cell cell, boolean bidirectional) {
        links.remove(cell);
        if (bidirectional) {
            cell.unlink(this, false);
        }
        return this;
    }

    public SampleableList<Cell> links() {
        return new SampleableList<>(links.keySet());
    }

    public boolean isLinked(Cell cell) {
        return links().contains(cell);
    }

    public SampleableList<Cell> neighbours() {
        SampleableList<Cell> list = new SampleableList<>();
        if (null != north) {
            list.add(north);
        }
        if (null != south) {
            list.add(south);
        }
        if (null != east) {
            list.add(east);
        }
        if (null != west) {
            list.add(west);
        }
        return list;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public Cell north() {
        return north;
    }

    public void setNorth(Cell north) {
        this.north = north;
    }

    public Cell south() {
        return south;
    }

    public void setSouth(Cell south) {
        this.south = south;
    }

    public Cell east() {
        return east;
    }

    public void setEast(Cell east) {
        this.east = east;
    }

    public Cell west() {
        return west;
    }

    public void setWest(Cell west) {
        this.west = west;
    }

    public Distances distances() {
        Distances distances = new Distances(this);
        List<Cell> frontier = Arrays.asList(this);
        while (!frontier.isEmpty()) {
            List<Cell> newFrontier = new ArrayList<>();
            for (Cell cell : frontier) {
                SampleableList<Cell> links = cell.links();
                for (Cell linked : links) {
                    if (null == distances.distance(linked)) {
                        distances.setDistance(linked, distances.distance(cell) + 1);
                        newFrontier.add(linked);
                    }
                }
            }
            frontier = newFrontier;
        }
        return distances;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
