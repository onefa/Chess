package com.onefa.chess;

import java.util.ArrayList;

public class King extends Piece{

    public King(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    // Returns ArrayList of all available Place's for King attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = null;
        Place aPlace;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                if (i != 0 || j != 0) {
                    aPlace = new Place(this.placeV + i, placeH + j);
                    if (aPlace != null && board.isEmptySquare(aPlace) ||
                            board.isOpponentSquare(this.color, aPlace)){
                        aPlaces.add(aPlace);
                    }
                }
            }
        }

        return aPlaces;
    }
}
