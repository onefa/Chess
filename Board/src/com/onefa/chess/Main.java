package com.onefa.chess;

public class Main {
    static ChessBoard chessBoard = new ChessBoard();

    public static void main(String[] args) {
/*
        getMove ("g2g4");
        getMove ("e7e5");
        getMove ("f2f3");
        getMove ("b7b6");
        getMove ("e2e4");
        getMove ("d8h4");
        getMove ("e1e2");
        getMove ("c8a6");
        getMove ("c2c4");
        getMove ("b8c6");
        getMove ("a2a3");
        getMove ("c6d4");
*/
        getMove ("e2e4");
        getMove ("e7e5");
        getMove ("f1c4");
        getMove ("b8c6");
        getMove ("d1h5");
        getMove ("g8f6");
        getMove ("h5f7");


    }

    static void getMove(String move) {
        System.out.printf("Move %s: %b, %s\n", move,
                chessBoard.move(move),
                chessBoard.getMoveResult());
    }



}
