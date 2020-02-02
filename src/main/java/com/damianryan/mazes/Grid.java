package com.damianryan.mazes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid {

    protected int rows;
    protected int columns;
    protected List<List<Cell>> cells;
    protected Random random = new Random();

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = prepareGrid(rows, columns);
        configureCells();
    }

    protected List<List<Cell>> prepareGrid(int rows, int columns) {
        cells = new ArrayList<>(rows);
        for (int row = 0; row < rows; row++) {
            List<Cell> rowCells = new ArrayList<>();
            cells.add(rowCells);
            for (int column = 0; column < columns; column++) {
                rowCells.add(new Cell(row, column));
            }
        }
        return cells;
    }

    public Stream<List<Cell>> rows() {
        return cells.stream();
    }

    public Stream<Cell> cells() {
        return rows().flatMap(Collection::stream).collect(Collectors.toList()).stream();
    }

    protected void configureCells() {
        cells().forEach(this::setNeighbours);
    }

    protected void setNeighbours(Cell cell) {
        int row = cell.row();
        int column = cell.column();
        cell.setNorth(cellAt(row - 1, column));
        cell.setSouth(cellAt(row + 1, column));
        cell.setWest(cellAt(row, column - 1));
        cell.setEast(cellAt(row, column + 1));
    }

    public Cell cellAt(int row, int column) {
        if (row < 0 || column < 0) {
            return null;
        }
        if (row >= rows || column >= columns) {
            return null;
        }
        return cells.get(row).get(column);
    }

    public Cell randomCell() {
        return cells.get(random.nextInt(rows)).get(random.nextInt(columns));
    }

    public int size() {
        return rows * columns;
    }

    public int rowCount() {
        return rows;
    }

    public int columnCount() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("+");
        for (int column = 0; column < columns; column++) {
            s.append("---+");
        }
        s.append('\n');
        for (List<Cell> row : cells) {
            String top = "|";
            String bottom = "+";
            for (Cell cell : row) {
                if (null == cell) {
                    cell = new Cell(-1, -1);
                }
                String body = " " + contentsOf(cell) + " ";
                String eastBoundary = cell.isLinked(cell.east()) ? " " : "|";
                top += body;
                top += eastBoundary;
                String southBoundary = cell.isLinked(cell.south()) ? "   " : "---";
                String corner = "+";
                bottom += southBoundary;
                bottom += corner;
            }
            s.append(top).append('\n').append(bottom).append('\n');
        }
        return s.toString();
    }

    public String contentsOf(Cell cell) {
        return " ";
    }

    public SampleableList<Cell> deadends() {
        SampleableList<Cell> list = new SampleableList<>();
        cells().filter(c -> c.links().size() == 1).forEach(list::add);
        return list;
    }
}
