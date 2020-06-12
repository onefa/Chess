
import java.util.ArrayList;

public abstract class Piece extends Place{
    boolean color;          // color of piece
    boolean inGame = true;  // flag for piece in game (not killed yet)

    // Constructor. Create Piece with color and coordinates on board
    public Piece(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH);
        this.color = color;
    }

    // Check if Piece still in game
    public boolean isInGame() {
        return inGame;
    }

    // Remove Piece out of game
    public void outGame(){
        inGame = false;
        board.setEmptySquare(this);
    }

    // Returns ArrayList of Place's with defined function of direction
    public ArrayList<Place> getAttackPlacesOnDirection (Place place, Direction function){
        ArrayList<Place> aPlaces = null;
        Place nextAttack = function.nextAttackPlace(place);

        while (nextAttack != null && board.isEmptySquare(nextAttack)){
            aPlaces.add(nextAttack);
            nextAttack = function.nextAttackPlace(nextAttack);
        }
        if (nextAttack != null && board.isOpponentSquare(this.color, nextAttack)){
            aPlaces.add(nextAttack);
        }
        return aPlaces;
    }

    // Returns ArrayList of all available Place's for attack.
    // This method defined in respectively classes
    abstract public ArrayList<Place> getAttackPlaces();


}


