package com.damianryan.mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SideWinder implements MazeAlgorithm {

    private Random random = new Random();

    @Override
    public Grid on(Grid grid) {
        grid.rows().forEach(this::visitRow);
        return grid;
    }

    private void visitRow(List<Cell> row) {
        SampleableList<Cell> run = new SampleableList<>();
        row.stream().forEach(c -> visitCell(c, run));
    }

    private void visitCell(Cell cell, SampleableList<Cell> run) {
        run.add(cell);
        boolean atEasternBoundary = null == cell.east();
        boolean atNorthernBoundary = null == cell.north();
        boolean shouldCloseOut = atEasternBoundary || (!atNorthernBoundary && random.nextInt(2) == 0);
        if (shouldCloseOut) {
            Cell member = run.sample();
            if (null != member.north()) {
                member.link(member.north());
                run.clear();
            }
        } else {
            cell.link(cell.east());
        }
    }
}
