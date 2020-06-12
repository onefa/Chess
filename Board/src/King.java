import java.util.ArrayList;

public class King extends Piece{

    public King(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    // Returns ArrayList of all available Place's for King attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = null;
        Place attackPlace = this.getPlace().getIncV();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }

        return aPlaces;
    }
}
