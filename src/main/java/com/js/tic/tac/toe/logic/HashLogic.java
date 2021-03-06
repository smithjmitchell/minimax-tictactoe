package com.ai.tic.tac.toe.logic;

import com.ai.tic.tac.toe.model.Board;

/**
 * Class representing a pseudo-board and accommodating logic to hash a 
 * passed in game-board state.
 */
public class HashLogic {
    
    /** The hashGrid acts as a comparison board for calculating a hash. */
    private final String[][] hashGrid;
    
    /** Constant for board size. */
    private static final int SIZE = 3;
    
    public HashLogic() {
        this.hashGrid = new String[SIZE][SIZE];
        init();
    }
    
    /** Initializes the board for hashing calculations. See README.md. */
    public final void init() {
        hashGrid[0][0] = "1";
        hashGrid[0][1] = "4";
        hashGrid[0][2] = "2";
        hashGrid[1][0] = "3";
        hashGrid[1][1] = "5";
        hashGrid[1][2] = "3";
        hashGrid[2][0] = "2";
        hashGrid[2][1] = "4";
        hashGrid[2][2] = "1";
    }
    
    /**
     * Calculates a unique hash String for unique board states - i.e., 
     * symmetrical board states return the same hash.
     * 
     * @param   board   current state of a game board.
     * @return  a unique combination of board cells and hashGrid cells.
     */
    public String getHash(Board board) {
        String hash = "";
        String[][] grid = board.getGrid();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) { 
                if (grid[i][j].equals("X"))    hash += (hashGrid[i][j] + "X");
                if (grid[i][j].equals("O"))    hash += (hashGrid[i][j] + "O");
            }
        }
        return hash;
    }
}