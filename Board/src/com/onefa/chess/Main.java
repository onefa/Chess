package com.onefa.chess;

public class Main {
    public static void main (String[] args){
        ChessBoard chessBoard = new ChessBoard();

        System.out.println("===== All results are in internal integer addressing =====");

        System.out.println("Attack places for Knight b1");
        for (Place place : chessBoard.armyWhite[9].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for closed Bishop c1");
        for (Place place : chessBoard.armyWhite[10].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for opened Bishop c1 (without Pawn at d2)");
        chessBoard.killPiece(chessBoard.armyWhite[3]);
        for (Place place : chessBoard.armyWhite[10].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for opened Queen d1 left - forward (without Pawns at c2 & d2)");
        chessBoard.killPiece(chessBoard.armyWhite[2]);
        for (Place place : chessBoard.armyWhite[11].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for opened white Rook (without Pawn at h2)");
        chessBoard.killPiece(chessBoard.armyWhite[7]);
        for (Place place : chessBoard.armyWhite[15].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }


        System.out.println("Attack places for opened black Rook (without Pawn at h7)");
        chessBoard.killPiece(chessBoard.armyBlack[7]);
        for (Place place : chessBoard.armyBlack[15].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for black Pawn at a7");
        for (Place place : chessBoard.armyBlack[0].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.printf("doMove b2b4: %b\n", chessBoard.move("b2b4"));
        System.out.printf("doMove b4b5: %b\n", chessBoard.move("B4b5"));
        System.out.printf("doMove a7a5: %b\n", chessBoard.move("a7a5"));
        System.out.printf("doMove e4e5: %b\n", chessBoard.move("e4e5"));

        System.out.println("Attack places for white Pawn at b5");
        for (Place place : chessBoard.armyWhite[1].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.printf("doMove g2g4: %b\n", chessBoard.move("g2g4"));
        System.out.println("Attack places for white Pawn at f2");
        for (Place place : chessBoard.armyWhite[5].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println("Attack places for white Pawn at b5");
        for (Place place : chessBoard.armyWhite[1].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }
        System.out.printf("doMove b5a6: %b\n", chessBoard.move("b5a6"));
        System.out.printf("doMove a2a4: %b\n", chessBoard.move("a2a4"));

        System.out.println("Attack places for white Pawn at a4");
        for (Place place : chessBoard.armyWhite[0].getAttackPlaces()){
            System.out.printf("%d : %d \n", place.placeV, place.placeH);
        }

        System.out.println();
        System.out.println("===== All results are in internal integer addressing =====");


    }



}
