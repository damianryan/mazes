package com.damianryan.mazes.mains;

import com.damianryan.mazes.Grid;
import com.damianryan.mazes.SideWinder;

public class SideWinderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(25, 50);
        System.out.println(new SideWinder().on(grid));
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
