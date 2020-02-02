package com.damianryan.mazes.mains;

import com.damianryan.mazes.Grid;
import com.damianryan.mazes.Wilsons;

public class WilsonsDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        System.out.println(new Wilsons().on(grid));
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
