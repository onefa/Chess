package com.onefa.chess;

public class Square{
    boolean white;
    Piece piece = null;

    public Square(boolean color) {
        this.white = color;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

}
