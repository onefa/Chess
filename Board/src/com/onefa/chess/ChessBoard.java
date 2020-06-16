package com.onefa.chess;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessBoard {
    Piece[][] armies = new Piece[2][2 * DIMENSION_V]; // Arrays of armies.
    Square[][] boardArray = new Square[DIMENSION_V][DIMENSION_H];
    private static EnPassant enPassant;

    final static boolean WHITE = true;
    final static boolean BLACK = false;
    final static boolean[] colors = {WHITE, BLACK};
    final static int DIMENSION_V = 8;
    final static int DIMENSION_H = 8;
    final static int MAX_VERTICAL = DIMENSION_V - 1;
    final static int MAX_HORIZONTAL = DIMENSION_H - 1;
    final static int KING_INDEX = 12;
    enum Move {
        SUCCESSFUL,
        CAPTURE,
        KING_UNDER_ATTACK,
        INVALID_COORDINATES,
        CHECK,
        CAPTURE_CHECK,
        MATE,
        CAPTURE_MATE
    }
    private Move moveResult = Move.SUCCESSFUL;

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
    public ChessBoard() {

        // Fill the boardArray with objects Squares (black/white)
        boolean color = BLACK;
        for (int i = 0; i < DIMENSION_V; i++) {
            for (int j = 0; j < DIMENSION_H; j++) {
                boardArray[i][j] = new Square(color);
                color = !color;
            }
        }

        // Fill with pieces the armies array
        {
            for (boolean pieceColor : colors) {
                int placeV;
                int army = pieceColor == WHITE ? 0 : 1;
                int placeH = pieceColor == WHITE ? 1 : 6;
                for (placeV = 0; placeV < DIMENSION_V; placeV++) {
                    armies[army][placeV] = new Pawn(this, placeV, placeH, pieceColor);
                }
                placeH = pieceColor == WHITE ? 0 : 7;
                armies[army][placeV] = new Rook(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Knight(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Bishop(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Queen(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new King(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Bishop(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Knight(this, placeV-DIMENSION_V, placeH, pieceColor);
                armies[army][++placeV] = new Rook(this, placeV-DIMENSION_V, placeH, pieceColor);
            }
        }
    }

    //
    public void killPiece(Piece piece) {
        piece.setInGame(false);
        setEmptySquare(piece.getPlace().placeV, piece.getPlace().placeH);
    }

    // Clear square from piece
    public void setEmptySquare(int placeV, int placeH) {
        boardArray[placeV][placeH].setPiece(null);
    }

    // Checks if square is empty
    public boolean isEmptySquare(int placeV, int placeH) {
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
    public boolean isOpponentSquare(boolean color, int placeV, int placeH) {
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

    private boolean checkKing(boolean color) {
        for (Piece attackPiece : armies[color ? 1 : 0]) {
            if (attackPiece != null && attackPiece.getAttackPlaces() != null && attackPiece.isInGame()) {
                for (Place attackPlace : attackPiece.getAttackPlaces()) {
                    if (attackPlace.equals(armies[color ? 0 : 1][KING_INDEX].getPlace())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkMateKing(boolean color) {
        for (Piece attackPiece : armies[color ? 1 : 0]) {
            if (attackPiece != null && attackPiece.getAttackPlaces() != null && attackPiece.isInGame()) {
                for (Place attackPlace : attackPiece.getAttackPlaces()) {
                    if (attackPlace.equals(armies[color ? 0 : 1][KING_INDEX].getPlace())) {

                        moveResult = Move.KING_UNDER_ATTACK;
                        for (Piece defencePiece : armies[color ? 0 : 1]) {
                            if (defencePiece != null &&
                                defencePiece.isInGame() &&
                                defencePiece.getAttackPlaces() != null) {
                                Place oldDefencePlace = new Place(defencePiece.getPlace().placeV,
                                        defencePiece.getPlace().placeH);
                                for (Place defencePlace : defencePiece.getAttackPlaces()) {
                                    if (doMove(oldDefencePlace, defencePlace)) {
                                        moveResult = Move.CHECK;
                                        setEmptySquare(defencePlace.getPlace().placeV, defencePlace.getPlace().placeH);
                                        defencePiece.setPlace(oldDefencePlace.getPlace());
                                        boardArray[oldDefencePlace.getPlace().placeV][oldDefencePlace.getPlace().placeH].setPiece(defencePiece);
                                        return false;
                                    }
                                }
                            }
                        }

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Move getMoveResult() {
        return moveResult;
    }

    public boolean move(String fromTo) {
        int[] coordinatesArray = new int[4];
        moveResult = Move.SUCCESSFUL;

        if (fromTo.length() == 4) {
            for (int i = 0; i < 4; i++) {
                char charKey = fromTo.toLowerCase().charAt(i);
                if (charAddress.containsKey(charKey)) {
                    coordinatesArray[i] = charAddress.get(charKey);
                } else {
                    moveResult = Move.INVALID_COORDINATES;
                    return false;
                }
            }
            if (boardArray[coordinatesArray[0]][coordinatesArray[1]].getPiece() != null) {
                if (doMove (new Place(coordinatesArray[0], coordinatesArray[1]),
                            new Place(coordinatesArray[2], coordinatesArray[3]))) {

                    Piece movedPiece = boardArray[coordinatesArray[2]][coordinatesArray[3]].getPiece();
                    if (movedPiece.getClass() == Pawn.class) {
                        if (enPassant != null && enPassant.getMaster() != null
                                && movedPiece.getPlace().equals(enPassant.getPlace())) {
                            killPiece(enPassant.getMaster());
                            clearEnPassant();
                        }
                        if (Math.abs(coordinatesArray[1] - coordinatesArray[3]) == 2) {
                            clearEnPassant();
                            setEnPassant((Pawn) movedPiece);
 //                           moveResult = Move.SUCCESSFUL;
                            return true;
                        }
                    }
                    clearEnPassant();
 //                   moveResult = Move.SUCCESSFUL;
                    return true;
                }
            }
        }
        moveResult = Move.INVALID_COORDINATES;
        return false;
    }

    private boolean doMove(Place placeFrom, Place placeTo) {
        Piece movedPiece = boardArray[placeFrom.getPlace().placeV][placeFrom.getPlace().placeH].getPiece();
        Place savedPlace = new Place(placeFrom.placeV, placeFrom.placeH);
        for (Place attackPlace : movedPiece.getAttackPlaces()) {
            if (attackPlace.equals(placeTo.getPlace())) {
                setEmptySquare(movedPiece.getPlace().placeV, movedPiece.getPlace().placeH);
                movedPiece.setPlace(placeTo.getPlace());
                boardArray[placeTo.getPlace().placeV][placeTo.getPlace().placeH].setPiece(movedPiece);

                // Handle CHECK for our King
                if (checkKing(movedPiece.color) && !(moveResult == Move.CHECK)) {
                    setEmptySquare(movedPiece.getPlace().placeV, movedPiece.getPlace().placeH);
                    movedPiece.setPlace(savedPlace);
                    boardArray[savedPlace.getPlace().placeV][savedPlace.getPlace().placeH].setPiece(movedPiece);
                    moveResult = Move.KING_UNDER_ATTACK;
                    return false;
                }

                for (Piece opponentPiece : armies[movedPiece.color == WHITE ? 1 : 0]) {
                    if (opponentPiece.getPlace().equals(movedPiece.getPlace())){
                        killPiece(opponentPiece);
                        moveResult = Move.CAPTURE;
                    }
                }

                if (moveResult != Move.KING_UNDER_ATTACK) {

                    // Handle MATE for opponent's King
                    if (checkMateKing(!movedPiece.color)) {
                        moveResult = moveResult == Move.CAPTURE ? Move.CAPTURE_MATE : Move.MATE;
                        return true;
                    } else if (moveResult == Move.CHECK) {
                        return true;
                    }

                    // Handle CHECK for opponent's King
                    if (checkKing(!movedPiece.color)) {
                        moveResult = moveResult == Move.CAPTURE ? Move.CAPTURE_CHECK : Move.CHECK;
                        return true;
                    }

                }
                moveResult = Move.SUCCESSFUL;
                return true;
            }
        }
        moveResult = Move.INVALID_COORDINATES;
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

    private void clearEnPassant() {
        if (enPassant != null) {
            enPassant.clearMaster();
        }
    }

    private static class EnPassant extends Piece {

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

        public void clearMaster() {
            this.inGame = false;
            this.master = null;
        }

    }
}

