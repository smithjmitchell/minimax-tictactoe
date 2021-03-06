package com.ai.tic.tac.toe.model;

import java.awt.Point;

/**
 * Class representing a full game of tic-tac-toe, from initialization to finish.
 */
public class Game {
    
    /** Constant for board size. */
    private static final int SIZE = 3;
    
    /** Player constants. */
    private static final String P1     = "X";
    private static final String P2     = "O";
    private static final int P1_POINT  = 1;
    private static final int P2_POINT  = -1;
        
    private Player playerOne;
    
    private Player playerTwo;
    
    private final Board board;
    
    /** To track score and determine winner. */
    private final int[] score; 
    
    /** Toggled each valid game move. */
    private boolean isP1Turn;
    
    public Game() {
        this.board      = new Board();
        this.score      = new int[2 * SIZE + 2];
        this.isP1Turn   = true;
    }
    
    public Game(Board board, int[] score, boolean isP1Turns) { 
        this.board      = board;
        this.score      = score;        
        this.isP1Turn   = isP1Turns;
    }
    
    public Game copy() { 
        Board copy = this.board.copy();
        
        int[] scoreCopy = new int[score.length];
        System.arraycopy(score, 0, scoreCopy, 0, score.length);
        
        boolean isP1Copy = isP1Turn;
        
        return new Game(copy, scoreCopy, isP1Copy);
    }

    private void init() {
        System.out.println("Initializing game. \nRow and Columns "
                + "are ordered 0 - 2. For example, bottom middle cell is 2,1.");
        playerTwo = new PlayerMinimax();
        playerOne = new PlayerHuman();
    }
    
    public void run() {                
        init();
        board.reset();
        board.print();

        Player currentPlayer = playerOne;
        while (true) {
            Point move = currentPlayer.getMove(this);
            
            makeMove(move, isP1Turn ? P1 : P2);
            board.print();
            
            if (isGameOver()) break;
            currentPlayer = !isP1Turn ? playerTwo : playerOne;
        }
    }
    
    public void makeMove(Point move, String player) {
        board.updateCell(move, player);
        
        int point = isP1Turn ? P1_POINT : P2_POINT;
        int row = move.x;
        int col = move.y;

        // Scoring logic: README.md.
        score[row]        += point;                                       
        score[SIZE + col] += point;
        if (row == col)              score[2 * SIZE] += point;
        if ((SIZE - 1 - col) == row) score[2 * SIZE + 1] += point;
        
        // Toggle current player, finishing move.
        isP1Turn = !isP1Turn;
    }

    public boolean isGameOver() {
        for (int i : score) {
            if (i == SIZE || i == -SIZE) return true;
        }
        
        return board.isFull();
    }
    
    public String getWinner() {
        for (int i : score) {
            if (i == SIZE)  return P1;
            if (i == -SIZE) return P2;
        }
        return "";
    }
            
    public Board getBoard() {
        return board;
    }
    
    public boolean getP1Turn() {
        return isP1Turn;
    }
}