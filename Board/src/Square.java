public class Square {
    boolean color;
    Piece piece = null;

    public Square(boolean color) {
        this.color = color;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

}
