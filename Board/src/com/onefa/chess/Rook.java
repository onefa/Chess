package com.onefa.chess;

import java.util.ArrayList;

public class Rook extends Piece{

    // Constructor
    public Rook(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    //  Returns ArrayList of all available Place's for Rook attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = new ArrayList<>();
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), Place::withIncrementVertical));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), Place::withDecrementVertical));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), Place::withIncrementHorizontal));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), Place::withDecrementHorizontal));

        return aPlaces;
    }
}
