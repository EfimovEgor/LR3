package org.example;

class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int row, int column, String value) {
        super("Неверные данные \"" + value + "\" в ячейке [" + (row+1) + "][" + (column+1) + "]");
    }
}
