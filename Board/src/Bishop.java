import java.beans.Expression;
import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV().getIncH());
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV().getDecH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getIncV().getDecH()));
        aPlaces.addAll(getAttackPlacesOnDirection(this.getPlace(), (place) -> getDecV().getIncH()));

        return aPlaces;
    }
}
