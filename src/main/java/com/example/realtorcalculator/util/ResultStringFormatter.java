package com.example.realtorcalculator.util;

import com.example.realtorcalculator.model.Deal;
import com.example.realtorcalculator.model.RoundingResult;

public class ResultStringFormatter {

    public static String roundUp(String value, Deal deal) throws ArrayIndexOutOfBoundsException {
        value = deal.isShowThousands() ? value.substring(0, value.length() - 3) : value;

        int[] inputArray = new int[value.length()];
        int[] outputArray = new int[value.length()];

        int counter = 0;
        for (char ch : value.toCharArray()) {
            inputArray[counter] = Integer.parseInt(String.valueOf(ch));
            counter++;
        }

        for (int i = inputArray.length - 1; i >= 0; i--) {
            if (((inputArray.length - 1) - i) == 0) {
                outputArray[i] = 0;

            } else if (((inputArray.length - 1) - i) == 1) {
                outputArray[i] = deal.getRoundingResult().equals(RoundingResult.ROUND_TO_10) ? inputArray[i] + 1
                        : inputArray[i] < 5 ? 5 : 0;

            } else if (((inputArray.length - 1) - i) == 2
                    && deal.getRoundingResult().equals(RoundingResult.ROUND_TO_50)
                    && outputArray[i + 1] == 0) {
                outputArray[i] = inputArray[i] + 1;

            } else if (inputArray[i + 1] != outputArray[i + 1] && inputArray[i + 1] != 9) {
                outputArray[i] = inputArray[i];

            } else if (inputArray[i + 1] != outputArray[i + 1] && inputArray[i + 1] == 9) {
                outputArray[i] = inputArray[i] + 1;
                outputArray[i + 1] = 0;

            } else {
                outputArray[i] = inputArray[i];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i : outputArray) {
            builder.append(i);
        }

        if (deal.isShowThousands()) {
            return builder.toString() + "000";
        } else {
            return builder.toString();
        }
    }

    public static String addSpacesToResultString(String string, Deal deal) {
        String reverseSalary = new StringBuilder(string).reverse().toString();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append((deal.isShowThousands() ? "₽" : ".бур.сыт") + " ").append(reverseSalary.substring(0, 3) + " ");
        reverseSalary = reverseSalary.substring(3);

        int endIndex = Math.min(reverseSalary.length(), 3);
        stringBuilder.append(reverseSalary.substring(0, endIndex));
        reverseSalary = reverseSalary.substring(endIndex);

        while (reverseSalary.length() > 3) {
            stringBuilder.append(" ").append(reverseSalary.substring(0, 3));
            reverseSalary = reverseSalary.substring(3);
        }

        stringBuilder.append(" ").append(reverseSalary);

        return stringBuilder.reverse().toString().trim();
    }
}
