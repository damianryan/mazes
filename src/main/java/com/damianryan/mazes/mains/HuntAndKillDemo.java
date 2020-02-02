package com.damianryan.mazes.mains;

import com.damianryan.mazes.Grid;
import com.damianryan.mazes.HuntAndKill;

public class HuntAndKillDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        System.out.println(new HuntAndKill().on(grid));
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
