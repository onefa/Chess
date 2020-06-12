import java.util.ArrayList;

public class Queen extends Piece{

    // Constructor
    public Queen(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    //  Returns ArrayList of all available Place's for Queen attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV());
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV().getIncH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV().getDecH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV().getDecH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV().getIncH()));

        return aPlaces;
    }
}
