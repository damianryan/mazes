package com.damianryan.mazes.mains;

import com.damianryan.mazes.AldousBroder;
import com.damianryan.mazes.Grid;

public class AldousBroderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 20);
        System.out.println(new AldousBroder().on(grid));
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
