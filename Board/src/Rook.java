import java.util.ArrayList;

public class Rook extends Piece{

    // Constructor
    public Rook(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    //  Returns ArrayList of all available Place's for Rook attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {

        ArrayList<Place> aPlaces = getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV());
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecH()));

        return aPlaces;
    }
}
