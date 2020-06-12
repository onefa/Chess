/*
Класс Place.
Содержит данные о местоположении фигуры.
Конструктор используется только при инициализаии новой фигуры.
Проверяет, соответствуют ли координаты размерности доски.
Не проверяет, занята ли клетка другой фигурой.
 */

import java.util.ArrayList;

public class Place {
    int placeV;         // vertical (alphbet) coordinate at board
    int placeH;         // horizontal (digital) coordinate at board
    ChessBoard board;   // board for game

    // Constructor. Initialization Place on board
    public Place (ChessBoard board, int placeV, int placeH) {
        if (placeV > 0 && placeV <= board.DIMENSION_V &&
            placeH > 0 && placeH <= board.DIMENSION_H) {
            this.board = board;
            this.placeV = placeV;
            this.placeH = placeH;
        }
    }

    // Changes coordinates using another Place object
    public void setPlace(Place place){
        this.placeV = place.placeV;
        this.placeH = place.placeH;
    }

    // Returns Place
    public Place getPlace(){
        return this;
    }

    // Returns Place with vertical coordinate increment
    public Place getIncV(){
        if (placeV < board.DIMENSION_V) {
            return new Place(board, placeV++, placeH);
        }else{
            return null;
        }
    }

    // Returns Place with vertical coordinate decrement
    public Place getDecV(){
        if (placeV > 1) {
            return new Place(board, placeV--, placeH);
        }else{
            return null;
        }
    }

    // Returns Place with horizontal coordinate increment
    public Place getIncH(){
        if (placeH < board.DIMENSION_H) {
            return new Place(board, placeV, placeH++);
        }else{
            return null;
        }
    }

    // Returns Place with horizontal coordinate decrement
    public Place getDecH(){
        if (placeH > 1) {
            return new Place(board, placeV, placeH--);
        }else{
            return null;
        }
    }

}
