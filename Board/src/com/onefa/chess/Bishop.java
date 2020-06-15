package com.onefa.chess;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = new ArrayList<>();
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withIncrementVertical(withIncrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withDecrementVertical(withDecrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withIncrementVertical(withDecrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withDecrementVertical(withIncrementHorizontal(place))));

        return aPlaces;
    }
}
