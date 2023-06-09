package com.salihakbas.mayntarlas;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    String[][] gameBoard;
    String[][] mineBoard;
    int mineNumber;
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    void run() {
        System.out.print("Satır giriniz: ");
        row = scan.nextInt();
        System.out.print("Sütun giriniz: ");
        col = scan.nextInt();

        mineNumber = (row * col) / 4 ;

        gameBoard = new String[row][col];
        mineBoard = new String[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                gameBoard[i][j] = " - ";
                mineBoard[i][j] = " - ";
            }
        }
        while(mineNumber > 0) {
            int r1 = rand.nextInt(row);
            int r2 = rand.nextInt(col);

            if(mineBoard[r1][r2].equals(" - ")) {
                mineBoard[r1][r2] = " * ";
                mineNumber--;
            }
        }
        printMineBoard();

        play();
    }
    void play() {
        boolean finish = false;
        while(!finish) {
            System.out.print("Oynamak için satır giriniz: ");
            int selectedRow = scan.nextInt();
            System.out.print("Oynamak için sütun giriniz: ");
            int selectedCol = scan.nextInt();

            int gameMineNumber = 0;

            if(selectedRow < row && selectedCol < col) {
                if(gameBoard[selectedRow][selectedCol].equals(" - ") && mineBoard[selectedRow][selectedCol].equals(" - ")) {
                    for(int i = selectedRow - 1 ; i <= selectedRow + 1; i++) {
                        for(int j = selectedCol - 1 ; j <= selectedCol + 1; j++) {
                            if(i >= 0 && j >= 0 && i < row && j < col && mineBoard[i][j].equals(" * ")) {
                                gameMineNumber++;
                                gameBoard[selectedRow][selectedCol] = Integer.toString(gameMineNumber);
                            }else{
                                gameBoard[selectedRow][selectedCol] = Integer.toString(gameMineNumber);
                            }
                        }
                    }
                    printGameBoard();
                    if(!isWin()) {
                        System.out.println("Tebrikler, oyunu kazandınız!");
                        printMineBoard();
                        finish = true;
                    }
                }
                else if(mineBoard[selectedRow][selectedCol].equals(" * ")) {
                    System.out.println("Kaybettiniz!");
                    printMineBoard();
                    finish = true;
                }else if(!gameBoard[selectedRow][selectedCol].equals(" - ")) {
                    System.out.println("Daha önceden girdiğiniz değeri girdiniz, lütfen yeni değerler giriniz.");
                }

            }else{
                System.out.println("Hatalı değer girdiniz, lütfen yeni değerler giriniz.");
            }
        }
    }
    boolean isWin() {
        int emptyCell = 0;
        int mineCell = 0;

        for(int i = 0; i < mineBoard.length; i++) {
            for(int j = 0; j < mineBoard[i].length; j++) {
                if(gameBoard[i][j].equals(" - ")) {
                    emptyCell++;
                }
                if(mineBoard[i][j].equals(" * ")) {
                    mineCell++;
                }
            }
        }
        if(emptyCell == mineCell) {
            return false;
        }
        return true;
    }




    void printGameBoard() {
        for(String[] row: gameBoard) {
            for(String col: row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }
    void printMineBoard() {
        for(String[] row: mineBoard) {
            for(String col: row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }
}
