package com.ai.tic.tac.toe.model;

import com.ai.tic.tac.toe.logic.MoveLogic;
import java.awt.Point;
import java.util.Scanner;

/** Class represents a local player, prompting their desired move. */
public class PlayerHuman implements Player {
    
    @Override
    public Point getMove(Game game) {
        Scanner in  = new Scanner(System.in);
        Point move  = new Point();
        Board board = game.getBoard();
        
        while (true) {
            System.out.println("Input x-coordinate:");
            move.x = in.nextInt();
            System.out.println("Input y-coordinate:");
            move.y = in.nextInt();
        
            if (MoveLogic.isLegalMove(move, board)) break;
            System.out.println("Invalid input, please try again.");
        }
        return move;
    }
}