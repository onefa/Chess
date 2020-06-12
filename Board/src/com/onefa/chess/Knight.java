package com.onefa.chess;

import java.util.ArrayList;

public class Knight extends Piece{

    // Constructor
    public Knight(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    // Returns ArrayList of all available Place's for Knight attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = new ArrayList<>();
        Place aPlace;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j <3; j++) {
                if (i != 0 && j != 0 && i + j != 0 && i != j) {
                    aPlace = new Place(this.placeV + i, placeH + j);
                    if (aPlace != null && (board.isEmptySquare(aPlace) ||
                        board.isOpponentSquare(this.color, aPlace))) {
                        aPlaces.add(aPlace);
                    }
                }
            }
        }

        return aPlaces;
    }
}
