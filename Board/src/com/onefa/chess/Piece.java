package com.onefa.chess;

import java.util.ArrayList;

public abstract class Piece extends Place {
    boolean color;          // color of piece
    boolean inGame = true;  // flag for piece in game (not killed yet)
    ChessBoard board;

    // Constructor. Create Piece with color and coordinates on board
    public Piece(ChessBoard board, int placeV, int placeH, boolean color) {
        super(placeV, placeH);
        this.board = board;
        this.color = color;
        board.boardArray[placeV][placeH].setPiece(this);
    }

    // Check if Piece still in game
    public boolean isInGame() {
        return inGame;
    }

    // Remove Piece out of game
    public void outGame(){
        inGame = false;
        board.setEmptySquare(this.getPlace().placeV, this.getPlace().placeH);
    }

    public Place functionResult (Place place, Direction function) {
        try{
            return function.nextAttackPlace(place);
        }catch (NullPointerException e){
            return null;
        }
    }

    // Returns ArrayList of Place's with defined function of direction
    public ArrayList<Place> getAttackPlacesOnDirection (Place place, Direction function){
        ArrayList<Place> aPlaces = new ArrayList<>();
        Place nextAttack = functionResult(place, function);

        while (nextAttack != null && board.isEmptySquare(nextAttack.getPlace().placeV,
                                                         nextAttack.getPlace().placeH)){
            aPlaces.add(nextAttack);
            nextAttack = functionResult(nextAttack, function);
        }
        if (nextAttack != null && board.isOpponentSquare(this.color, nextAttack.getPlace().placeV,
                                                                     nextAttack.getPlace().placeH)){
            aPlaces.add(nextAttack);
        }
        return aPlaces;
    }

    // Returns ArrayList of all available Place's for attack.
    // This method defined in respectively classes
    abstract public ArrayList<Place> getAttackPlaces();


}


