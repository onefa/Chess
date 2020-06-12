package com.onefa.chess;

public class Main {
    public static void main (String[] args){
        ChessBoard chessBoard = new ChessBoard();
        for (int i = 0; i < ChessBoard.DIMENSION_H; i++) {
            for (int j = 0; j < ChessBoard.DIMENSION_V; j++) {
                if (chessBoard.boardArray[i][j].getPiece() != null){
                    System.out.print(chessBoard.boardArray[i][j].getPiece().toString() + ' ');
                }else{
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

        System.out.println("Attack for Knight");
        for (Place place : chessBoard.armyWhite[9].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack for closed Bishop");
        for (Place place : chessBoard.armyWhite[10].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack for opened Bishop");
        chessBoard.killPiece(chessBoard.armyWhite[3]);
        for (Place place : chessBoard.armyWhite[10].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack for opened Queen left - forward");
        chessBoard.killPiece(chessBoard.armyWhite[2]);
        for (Place place : chessBoard.armyWhite[11].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack for opened Rook");
        chessBoard.killPiece(chessBoard.armyWhite[7]);
        for (Place place : chessBoard.armyWhite[15].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

    }

}
