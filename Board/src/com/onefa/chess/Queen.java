package com.onefa.chess;

import java.util.ArrayList;

public class Queen extends Piece{

    // Constructor
    public Queen(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    //  Returns ArrayList of all available Place's for Queen attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = new ArrayList<>();
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), this::withIncrementVertical));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), this::withDecrementVertical));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), this::withIncrementHorizontal));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), this::withDecrementHorizontal));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withIncrementVertical(withIncrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withDecrementVertical(withDecrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withIncrementVertical(withDecrementHorizontal(place))));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> withDecrementVertical(withIncrementHorizontal(place))));

        return aPlaces;
    }
}
