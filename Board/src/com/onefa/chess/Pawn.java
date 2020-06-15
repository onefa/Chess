package com.onefa.chess;
/*
Класс Pawn.
Пешка.
Что необходимо сделать.
1. Ход без взятия - одно поле вперед.
2. Ход без взятия из исходной позиции - два поля вперед.
    При этом в enPassant записываются координаты
    пропущеного поля до любого следующего хода.
    Пока enPassant != null (один ход) пешка может быть
    взята на проходе пешкой соперника. Любым следующим ходом
    enPassant освобождается от записанных координат (значением null
    либо координатами следующего положения enPassant, если пешка
    соперника пошла ченрез одно поле из исходной позиции).
3. Ход со взятием - одно поле по диагонали вправо или влево.
    Возможен только для взятия пешки или фигуры соперника.
4. Превращение. При достижении дальней горизонтали пешка может быть
    заменена на любую фигуру того же цвета, кроме короля.
    Как это реализовать?



 */
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(ChessBoard board, int placeV, int placeH, boolean color) {
        super(board, placeV, placeH, color);
    }

    @Override
    public ArrayList<Place> getAttackPlaces() {
        ArrayList<Place> aPlaces = new ArrayList<>();
        int attackDirection;

        // Attack direction (plus for Whites and minus for Blacks)
        if (this.color == ChessBoard.WHITE) {
            attackDirection = 1;
        }else{
            attackDirection = -1;
        }

        // Checks square for one and two steps forward
        for (int i = 1; i < 3; i++) {
            if (board.isEmptySquare(this.getPlace().placeV, this.getPlace().placeH + attackDirection * i)) {
                aPlaces.add(new Place(this.getPlace().placeV, this.getPlace().placeH + attackDirection * i));
            }
        }

        // Checks square for attack steps
        for (int i = -1; i < 2; i = i + 2) {
            if (board.isOpponentSquare(this.color, this.getPlace().placeV + i, this.getPlace().placeH + attackDirection)) {
                aPlaces.add(new Place(this.getPlace().placeV + i, this.getPlace().placeH + attackDirection));
            }
        }
        return aPlaces;
    }
}
