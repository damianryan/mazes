package com.damianryan.mazes;

public class BinaryTree implements MazeAlgorithm {

    @Override
    public Grid on(Grid grid) {
        grid.cells().forEach(this::visit);
        return grid;
    }

    private void visit(Cell cell) {
        SampleableList<Cell> neighbours = new SampleableList<>();
        if (null != cell.north()) {
            neighbours.add(cell.north());
        }
        if (null != cell.east()) {
            neighbours.add(cell.east());
        }
        if (!neighbours.isEmpty()) {
            cell.link(neighbours.sample());
        }
    }
}
