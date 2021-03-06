package com.ai.tic.tac.toe.model;

import com.ai.tic.tac.toe.logic.HashLogic;
import com.ai.tic.tac.toe.logic.MoveLogic;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** Class represents an AI player using the Minimax algorithm. */
public class PlayerMinimax implements Player {
    
    /** Player constants. */
    private static final String P1 = "X";
    private static final String P2 = "O";
    
    /** Collection maps a unique board hash (String) to a calculated score. */
    private final HashMap<String, Integer> cache;
    
    /** Object for calculating board state hashes. */
    private final HashLogic hashing;
            
    public PlayerMinimax() {
        this.cache = new HashMap<>();
        this.hashing = new HashLogic();
    }

    @Override
    public Point getMove(Game game) {
        return minimax(game, game.getP1Turn() ? P1 : P2);
    }

    /**
     * Access point for Minimax algorithm: loops through all legal moves
     * remaining for a given board state, determining the highest scoring 
     * (best) move.
     * 
     * @param   game      a current game state.
     * @param   optimize  the player we are optimizing for is the current 
     *                    players turn (in the passed in game state).
     * @return  the optimal move. 
     */
    private Point minimax(Game game, String optimize) {                
        int bestScore   = -99;
        Point bestMove  = null;
        
        Board board = game.getBoard();
        List<Point> legalMoves = MoveLogic.getLegalMoves(board);
        
        int score;
        for (Point move : legalMoves) {
            Game copy = game.copy();            
            copy.makeMove(move, optimize);
            
            String toMove = game.getP1Turn() ? P1 : P2;
            String hash   = hashing.getHash(copy.getBoard());
            
            if (!cache.containsKey(hash)) {
                score = minimaxScore(copy, toMove, optimize);
                cache.put(hash, score);
            }
            score = cache.get(hash);
                        
            if (score > bestScore) {
                bestMove = move;
                bestScore = score;          
            } 
        }  
        return bestMove;
    }
    
    /**
     * Iterates through all legal moves for a given board state, recursively
     * calling itself until a winner or final score can be determined. 
     * 
     * @param   game      a current game state.
     * @param   toMove    the player prompted to move (i.e. game.isP1Turn).
     * @param   optimize  the player the algorithm is optimizing for.
     * @return  highest (or lowest) score for all legal moves. 
     */
    private int minimaxScore(Game game, String toMove, String optimize) {
        Board board = game.getBoard();
        
        String winner = game.getWinner();
        if (winner.equals(optimize)) return 10;
        if (winner.equals(toMove))   return -10;
        if (board.isFull())          return 0;
        
        List<Point> legalMoves   = MoveLogic.getLegalMoves(board);
        ArrayList<Integer> score = new ArrayList<>();  
        
        for (Point move : legalMoves) {
            Game copy = game.copy();
            
            toMove = copy.getP1Turn() ? P1 : P2;
            copy.makeMove(move, toMove);
            
            int opponentsScore = minimaxScore(copy, toMove, optimize);
            score.add(opponentsScore);
        }
        
        if (toMove.equals(optimize)) {
            return Collections.max(score);
        } else {
            return Collections.min(score);
        }
    }
}