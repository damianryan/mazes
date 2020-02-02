package com.damianryan.mazes.mains;

import com.damianryan.mazes.BinaryTree;
import com.damianryan.mazes.Grid;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(5, 5);
        System.out.println(new BinaryTree().on(grid));
        System.out.println(grid.deadends().size() + " dead-ends");
    }
}
