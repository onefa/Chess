/*
Класс Pawn.
Пешка.
Метод getPlaces() возвращает возможные положения
пешки при обычном ходе (одно поле вперед),
ходе с исходной позиции (два поля вперед),
ходе со взятием пешки или фигуры противника (одно поле по диагонали),
ходе со взятием пешки на проходе (одно поле по диагонали).
Информация о доступности того или иного поля предоставляется
классом ChessBoard, метод ChessBoard.getSquareInfo(Place square)
 */
import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = null;


        return aPlaces;
    }
}
