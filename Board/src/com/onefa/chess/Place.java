package com.onefa.chess;
/*
Класс Place.
Содержит данные о местоположении фигуры.
Конструктор используется только при инициализаии новой фигуры.
Проверяет, соответствуют ли координаты размерности доски.
Не проверяет, занята ли клетка другой фигурой.
 */

public class Place {
    int placeV;         // vertical (alphbet) coordinate at board
    int placeH;         // horizontal (digital) coordinate at board

    // Constructor. Initialization Place on board
    public Place (int placeV, int placeH) {
        if (placeV >= 0 && placeV < ChessBoard.DIMENSION_V &&
            placeH >= 0 && placeH < ChessBoard.DIMENSION_H) {
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
    public Place withIncrementVertical(Place place){
        if (place.placeV < ChessBoard.DIMENSION_V) {
            return new Place(place.placeV+1, place.placeH);
        }else{
            return null;
        }
    }

    // Returns Place with vertical coordinate decrement
    public Place withDecrementVertical(Place place){
        if (place.placeV > 0) {
            return new Place(place.placeV-1, place.placeH);
        }else{
            return null;
        }
    }

    // Returns Place with horizontal coordinate increment
    public Place withIncrementHorizontal(Place place){
        if (place.placeH < ChessBoard.DIMENSION_H) {
            return new Place(place.placeV, place.placeH+1);
        }else{
            return null;
        }
    }

    // Returns Place with horizontal coordinate decrement
    public Place withDecrementHorizontal(Place place){
        if (place.placeH > 0) {
            return new Place(place.placeV, place.placeH-1);
        }else{
            return null;
        }
    }

}
