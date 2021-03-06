package com.ai.tic.tac.toe.model;

import java.awt.Point;

/** Interface representing any player in the game of tic-tac-toe. */
public interface Player {
    
    /** 
     * Method for prompting a players desired move.
     * 
     * @param   game    the current game board state.
     * @return  the integer coordinates (row, col) for desired move.
     */
    public Point getMove(Game game);
}