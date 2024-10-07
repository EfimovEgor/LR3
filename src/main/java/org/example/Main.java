package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int sum = 0;
    static boolean hasError = false;

    public static void main(String[] args) {

        String[][] array = {
                {"2", "123321", "9", "5"},
                {"30", "14", "8", "2"},
                {"928", "100", "232", "22"},
                {"2", "252", "255", "728889"}
        };

        List<RuntimeException> exceptions = new ArrayList<>();
        try {
            test(array, exceptions);

            if (!exceptions.isEmpty()) {
                for (Exception e : exceptions) {
                    e.printStackTrace();
                }
            }
        } catch (MyArrayDataException | MyArraySizeException e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (!hasError) {
                System.out.println("Сумма элементов массива: " + sum);
            } else {
            }
        }
    }

    static void test(String[][] array, List<RuntimeException> exceptions) throws MyArrayDataException, MyArraySizeException {
        int rows = array.length;
        boolean sizeCorrect = true;

        if (rows == 4) {
            for (String[] row : array) {
                if (row.length != 4) {
                    sizeCorrect = false;
                    break;
                }
            }
        } else {
            sizeCorrect = false;
        }

        if (!sizeCorrect) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String element = array[i][j];
                try {
                    if (!element.matches("\\d+")) {
                        throw new MyArrayDataException(i, j, element);
                    }

                    double value = Double.parseDouble(element);
                    sum += value;

                    if (isInteger(element)) {
                        int intValue = (int) value;

                        if (isHappy(intValue)) {
                            exceptions.add(new MyHappyException(i, j, element));
                        }

                        if (intValue > 1000 && !isHamming(intValue)) {
                            exceptions.add(new MyNoHammingException(i, j, element));
                        }
                    }

                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, element);
                }
            }
        }
    }

    static boolean isInteger(String s) {
        return s.matches("\\d+");
    }

    public static boolean isHappy(int number) {
        String numberString = String.valueOf(number);
        if (numberString.length() != 6) {
            return false;
        }

        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < 3; i++) {
            leftSum += Character.getNumericValue(numberString.charAt(i));
            rightSum += Character.getNumericValue(numberString.charAt(6 - i - 1));
        }

        return leftSum == rightSum;
    }

    public static boolean isHamming(int number) {
        if (number <= 0) {
            return false;
        }

        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }

        return number == 1;
    }
}
