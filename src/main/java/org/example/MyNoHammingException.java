    package org.example;
    
    class MyNoHammingException extends RuntimeException {

        public MyNoHammingException(int row, int column, String value) {
            super("Не принадлежит последовательности Хемминга число \"" + value + "\" в ячейке [" + (row + 1) + "][" + (column + 1) + "]");

        }
    
    }
