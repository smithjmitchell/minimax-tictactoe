package com.ai.tic.tac.toe.model;

import com.ai.tic.tac.toe.logic.MoveLogic;
import java.awt.Point;
import java.util.Arrays;

/**
 * Class representing a standard 3x3 game grid for tic-tac-toe.
 */
public class Board {
    
    /** Constant for initial board cell values. */
    public static final String BLANK = " ";
    
    /** Constant for board size. */
    private static final int SIZE = 3;

    /** 2d-array for any board size equal to GRID_SIZE^2. */
    private final String[][] grid;

    public Board() {
        this.grid = new String[SIZE][SIZE];
    }

    public Board(String[][] grid) {
        this.grid = grid;
    }
    
    public Board copy() {
        String[][] clone = new String[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(this.grid[i], 0, clone[i], 0, SIZE);
        }
        return new Board(clone);
    }

    public final void reset() {
        for (String[] row : grid) {
            Arrays.fill(row, BLANK);
        }
    }

    public void print() {
        for (String[] grid1 : grid) {
            System.out.printf("| %s | %s | %s |%n", grid1[0],
                    grid1[1], grid1[2]);
        }
        System.out.println(" ----------- ");
    }

    public void updateCell(Point move, String player) {
        getGrid()[move.x][move.y] = player;
    }

    public boolean isFull() {
        return MoveLogic.getLegalMoves(this).isEmpty();
    }

    public String[][] getGrid() {
        return grid;
    }
}