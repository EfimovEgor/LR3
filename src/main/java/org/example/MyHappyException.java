package org.example;

public class MyHappyException extends RuntimeException {

    public MyHappyException(int row, int column, String value) {
        super("Счастливое число \"" + value + "\" в ячейке [" + (row+1) + "][" + (column+1) + "]");

    }
}
