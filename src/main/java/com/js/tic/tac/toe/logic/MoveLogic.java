package com.ai.tic.tac.toe.logic;

import com.ai.tic.tac.toe.model.Board;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The Move class provides static methods to validate any desired move, as 
 * well as returning a list of all available moves.
 */
public class MoveLogic {
    
    /** 
     * Confirm a move is legal - an unplayed (empty) cell on 
     * a game board.
     *
     * @param   move    array containing two values.. 
     * @param   board   current state of a game board.
     * @return  true if a move is valid.
    */
    public static boolean isLegalMove(Point move, Board board) {
        int n   = 3;
        int row = move.x;
        int col = move.y;
        
        if ((row < 0 || row >= n) || (col < 0 || col >= n)) {
            return false;
        }
        return board.getGrid()[row][col].equals(Board.BLANK);
    }
    
    /** 
     * Get all unplayed (empty) cells for a given game board.  
     *
     * @param   board current state of a game board.
     * @return  if a move is valid.
    */
    public static List<Point> getLegalMoves(Board board) {
        List<Point> legalMoves = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getGrid()[i][j].equals(Board.BLANK)) {
                    legalMoves.add(new Point(i, j));
                }
            }
        }
        return legalMoves;
    }
}