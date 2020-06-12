import java.util.ArrayList;

public class Knight extends Piece{

    // Constructor
    public Knight(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    // Returns ArrayList of all available Place's for Knight attack.
    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = null;
        Place attackPlace = this.getPlace().getIncV().getIncV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncV().getIncV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncV().getDecV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getIncV().getDecV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getIncV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getIncV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getDecV().getDecH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }
        attackPlace =  this.getPlace().getDecV().getDecV().getIncH();
        if (attackPlace != null){
            aPlaces.add(attackPlace);
        }

        return aPlaces;
    }
}
