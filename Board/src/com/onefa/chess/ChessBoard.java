package com.onefa.chess;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessBoard {
    final static boolean WHITE = true;
    final static boolean BLACK = false;
    final static int DIMENSION_V = 8;
    final static int DIMENSION_H = 8;
    final static int MAX_VERTICAL = DIMENSION_V-1;
    final static int MAX_HORIZONTAL = DIMENSION_H-1;
    Piece[] armyWhite = new Piece[2 * DIMENSION_V];
    Piece[] armyBlack = new Piece[2 * DIMENSION_V];
    Square[][] boardArray = new Square[DIMENSION_V][DIMENSION_H];
    private static EnPassant enPassant;
    private final static HashMap<Character, Integer> charAddress = new HashMap<>();
        static {
        charAddress.put('a', 0);
        charAddress.put('b', 1);
        charAddress.put('c', 2);
        charAddress.put('d', 3);
        charAddress.put('e', 4);
        charAddress.put('f', 5);
        charAddress.put('g', 6);
        charAddress.put('h', 7);
        charAddress.put('1', 0);
        charAddress.put('2', 1);
        charAddress.put('3', 2);
        charAddress.put('4', 3);
        charAddress.put('5', 4);
        charAddress.put('6', 5);
        charAddress.put('7', 6);
        charAddress.put('8', 7);
        }

    // Constructor.
    // Creates array of Square's, creates armies on initial position
    public ChessBoard () {
        boolean color = BLACK;
        for (int i = 0; i < DIMENSION_V; i++){
            for (int j = 0; j < DIMENSION_H; j++){
                boardArray[i][j] = new Square(color);
                color = !color;
            }
        }

        for (int i = 0; i < DIMENSION_V; i++){
            armyWhite[i] = new Pawn(this, i, 1, WHITE);
        }
        {
            int i = DIMENSION_V;
            int j = 0;
            armyWhite[i++] = new Rook(this, j++, 0, WHITE);
            armyWhite[i++] = new Knight(this, j++, 0, WHITE);
            armyWhite[i++] = new Bishop(this, j++, 0, WHITE);
            armyWhite[i++] = new Queen(this, j++, 0, WHITE);
            armyWhite[i++] = new King(this, j++, 0, WHITE);
            armyWhite[i++] = new Bishop(this, j++, 0, WHITE);
            armyWhite[i++] = new Knight(this, j++, 0, WHITE);
            armyWhite[i] = new Rook(this, j, 0, WHITE);
        }

        for (int i = 0; i < DIMENSION_V; i++){
            armyBlack[i] = new Pawn(this, i, 6, BLACK);
        }
        {
            int i = DIMENSION_V;
            int j = 0;
            armyBlack[i++] = new Rook(this, j++, 7, BLACK);
            armyBlack[i++] = new Knight(this, j++, 7, BLACK);
            armyBlack[i++] = new Bishop(this, j++, 7, BLACK);
            armyBlack[i++] = new Queen(this, j++, 7, BLACK);
            armyBlack[i++] = new King(this, j++, 7, BLACK);
            armyBlack[i++] = new Bishop(this, j++, 7, BLACK);
            armyBlack[i++] = new Knight(this, j++, 7, BLACK);
            armyBlack[i] = new Rook(this, j, 7, BLACK);
        }

    }

    // Clear square from piece
    public void setEmptySquare (int placeV, int placeH){
        boardArray[placeV][placeH].setPiece(null);
    }

    public void killPiece(Piece piece){
        piece.outGame();
    }

    // Checks if square is empty
    public boolean isEmptySquare (int placeV, int placeH) {
        if (Place.outOfBorders(placeV, placeH)) {
            return false;
        }
        if (boardArray[placeV][placeH].getPiece() != null &&
            boardArray[placeV][placeH].getPiece().isInGame()) {
            return false;
        } else {
            return true;
        }
    }

    // Checks if Opponents piece is on square
    public boolean isOpponentSquare (boolean color, int placeV, int placeH) {
        if (Place.outOfBorders(placeV, placeH)) {
            return false;
        }
        if (boardArray[placeV][placeH].getPiece() != null &&
            boardArray[placeV][placeH].getPiece().color != color &&
            boardArray[placeV][placeH].getPiece().isInGame()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean move (String fromTo){
        int[] coordinatesArray = new int[4];

        if (fromTo.length() == 4) {
            for (int i = 0; i < 4; i++) {
                char charKey = fromTo.toLowerCase().charAt(i);
                if (charAddress.containsKey(charKey)) {
                    coordinatesArray[i] = charAddress.get(charKey);
                } else {
                    return false;
                }
            }
            if (doMove (new Place(coordinatesArray[0], coordinatesArray[1]),
                        new Place(coordinatesArray[2], coordinatesArray[3]))) {
                return true;
            }
        }
        return false;
    }

    public boolean doMove (Place placeFrom, Place placeTo) {
        Piece movedPiece = boardArray[placeFrom.placeV][placeFrom.placeH].getPiece();
        if (movedPiece == null) {
            return false;
        }
        for (Place attackPlace : movedPiece.getAttackPlaces()) {
            if (attackPlace.equals(placeTo)) {
                setEmptySquare(movedPiece.getPlace().placeV, movedPiece.getPlace().placeH);
                movedPiece.setPlace(placeTo);
                boardArray[placeTo.placeV][placeTo.placeH].setPiece(movedPiece);

                if (movedPiece.getClass() == Pawn.class) {
                    if (enPassant != null && enPassant.getMaster()!=null
                                          && movedPiece.getPlace().equals(enPassant.getPlace())) {
                        killPiece(enPassant.getMaster());
                        enPassant.clearMaster();
                    }
                    if (Math.abs(placeFrom.getPlace().placeH - placeTo.getPlace().placeH) == 2) {
                        if (enPassant != null) {
                            enPassant.clearMaster();
                        }
                        setEnPassant((Pawn) movedPiece);
                        return true;
                    }
                }
                enPassant.clearMaster();
                return true;
            }
        }
        return false;
    }

    public void setEnPassant(Pawn pawn) {
        int direction = -1;
        if (pawn.color == BLACK) {
            direction = 1;
        }
        enPassant = new EnPassant(this, pawn.getPlace().placeV,pawn.getPlace().placeH+direction, pawn.color);
        enPassant.setMaster(pawn);
    }


    private static class EnPassant extends Piece{

        private Pawn master = null;

        public EnPassant(ChessBoard board, int placeV, int placeH, boolean color) {
            super(board, placeV, placeH, color);
        }

        @Override
        public ArrayList<Place> getAttackPlaces() {
            return null;
        }

        public void setMaster(Pawn pawn) {
            this.inGame = true;
            this.master = pawn;
        }

        public Pawn getMaster() {
            return this.master;

        }

        public void clearMaster(){
            this.inGame = false;
            this.master = null;
        }

    }
}

