
public class ChessBoard {
    final static boolean WHITE = true;
    final static boolean BLACK = false;
    final int DIMENSION_V = 8;
    final int DIMENSION_H = 8;
    Piece[] armyWhite = new Piece[17];
    Piece[] armyBlack = new Piece[17];
    Square[][] board;
    Place enpassant = null;

    // Constructor.
    // Creates array of Square's, creates armies on initial position
    public ChessBoard () {
        boolean color = BLACK;
        for (int i = 1; i <= DIMENSION_V; i++){
            for (int j = 1; j <= DIMENSION_H; j++){
                board[i][j] = new Square(color);
                color = !color;
            }
        }

        for (int i = 1; i <= DIMENSION_V; i++){
            armyWhite[i] = new Pawn(this, i, 2, WHITE);
        }


        for (int i = 1; i <= DIMENSION_V; i++){
            armyBlack[i] = new Pawn(this, i, 7, BLACK);
        }

    }

    // Clear square from piece
    public void setEmptySquare (Place place){
        board[place.placeV][place.placeH].setPiece(null);
    }

    // Checks if square is empty
    public boolean isEmptySquare (Place place){
        if (board[place.placeV][place.placeH].getPiece() != null &&
            board[place.placeV][place.placeH].getPiece().isInGame()){
            return false;
        }else{
            return true;
        }
    }

    // Checks if Opponents piece is on square
    public boolean isOpponentSquare (boolean color, Place place){

        if (board[place.placeV][place.placeH].getPiece() != null &&
            board[place.placeV][place.placeH].getPiece().color != color &&
            board[place.placeV][place.placeH].getPiece().isInGame()){
            return true;
        }else{
            return false;
        }
    }

    // Returns Place on empty square, which let get "en passant" piece
    public Place enPassant(){
        return enpassant;
    }

    public boolean doMove (Place placeFrom, Place placeTo){
        Piece movedPiece = board[placeFrom.placeV][placeFrom.placeH].getPiece();
        for (Place attackPlace : movedPiece.getAttackPlaces()){
            if (attackPlace == placeTo){
                setEmptySquare(movedPiece.getPlace());
                movedPiece.setPlace(placeTo);
                board[placeTo.placeV][placeTo.placeH].setPiece(movedPiece);
                return true;
            }
        }
        return false;
    }

}

