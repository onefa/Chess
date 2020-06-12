package com.onefa.chess;

public class ChessBoard {
    final static boolean WHITE = true;
    final static boolean BLACK = false;
    final static int DIMENSION_V = 8;
    final static int DIMENSION_H = 8;
    Piece[] armyWhite = new Piece[2 * DIMENSION_V];
    Piece[] armyBlack = new Piece[2 * DIMENSION_V];
    Square[][] boardArray = new Square[DIMENSION_V][DIMENSION_H];
    Place enpassant = null;

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
    public void setEmptySquare (Place place){
        boardArray[place.placeV][place.placeH].setPiece(null);
    }

    public void killPiece(Piece piece){
        piece.outGame();
    }

    // Checks if square is empty
    public boolean isEmptySquare (Place place){
        if (boardArray[place.placeV][place.placeH].getPiece() != null &&
            boardArray[place.placeV][place.placeH].getPiece().isInGame()){
            return false;
        }else{
            return true;
        }
    }

    // Checks if Opponents piece is on square
    public boolean isOpponentSquare (boolean color, Place place){

        if (boardArray[place.placeV][place.placeH].getPiece() != null &&
            boardArray[place.placeV][place.placeH].getPiece().color != color &&
            boardArray[place.placeV][place.placeH].getPiece().isInGame()){
            return true;
        }else{
            return false;
        }
    }

    // Returns Place on empty square, which let get "en passant" piece
    public Place enPassant(){
        return enpassant;
    }

    public boolean doMove (Place placeFrom, Place placeTo){
        Piece movedPiece = boardArray[placeFrom.placeV][placeFrom.placeH].getPiece();
        for (Place attackPlace : movedPiece.getAttackPlaces()){
            if (attackPlace == placeTo){
                setEmptySquare(movedPiece.getPlace());
                movedPiece.setPlace(placeTo);
                boardArray[placeTo.placeV][placeTo.placeH].setPiece(movedPiece);
                return true;
            }
        }
        return false;
    }

}

