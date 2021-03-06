package com.ai.tic.tac.toe.model;

import com.ai.tic.tac.toe.logic.MoveLogic;
import java.awt.Point;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** Class representing an AI player whom returns a randomized, legal move. */
public class PlayerRandom implements Player {
    
    @Override
    public Point getMove(Game game) {
        Board board = game.getBoard();
        
        List<Point> legalMoves = MoveLogic.getLegalMoves(board);
        int random = ThreadLocalRandom.current()
                        .nextInt(0, legalMoves.size());
        
        return legalMoves.get(random);
    }
}