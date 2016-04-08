package com.rsc.harry.arithmeticcalculator;

import java.text.*;

public class Calculate {
    public static final String none = "";
    public boolean first_negative = false;
    public boolean second_negative = false;
    public boolean equalPressed = false;

    private double first ;
    private int firstSize;

    private double second ;
    private double answer;

    private String type;

    public Calculate() {
        first = 0;
        second = 0;
        type = none;
    }

    public boolean checkExistingOperator() {
        if(type.equals(none))
            return false;
        else
            return true;
    }

    public void setType(String input) {
        type = input;
    }

    public String getType() {
        return type;
    }

    public Boolean getEqualPressed() {
        return equalPressed;
    }

    public void setEqualPressed(Boolean status) {
        equalPressed = status;
    }

    public String checkLastChar(String input, String operator){
        char last = input.charAt(input.length() - 1);

        if(Character.isDigit(last))
            return input;
        else
            return input.substring(0, input.length() - 1) + operator;
    }

    public void createFirst(String input) {
        first = Double.parseDouble(input);
        firstSize = input.length();
    }


    public void createSecond(String input) {
        second = Double.parseDouble(input.substring(firstSize + 1));
    }

    public String arithmetic() {
        switch(type) {
            case "+":
                answer = first + second;
                break;
            case "-":
                answer = first - second;
                break;
            case "÷":
                if (second == 0)
                    return "Err";
                else {
                    answer = first / second;
                    break;
                }
            case "×":
                answer = first * second;
                break;
        }

        return factorAnswer(answer);
    }

    public String factorAnswer(double ans) {
        NumberFormat formatter;

        if(ans % 1 == 0){
            formatter = new DecimalFormat("#");

            if (String.valueOf(formatter.format(ans)).length() > 8) {
                formatter = new DecimalFormat("0.###E0");
                return formatter.format(ans);
            }

            return String.valueOf(formatter.format(ans));
        }

        if (String.valueOf(ans).length() > 8) {
            formatter = new DecimalFormat("0.###E0");
            return formatter.format(ans);
        }

        return String.valueOf(ans);
    }

    public void reset() {
        first = 0;
        second = 0;
        type = none;
        first_negative = false;
        second_negative = false;
    }

    public int checkDecimal(String input) {
        int decimal_first = 0;
        int decimal_second = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '.') {
                if(i < firstSize)
                    decimal_first = 1;
                else
                    decimal_second = 1;
            }
        }

        if ((decimal_first == 0 && decimal_second == 0) || (decimal_first == 1 && decimal_second == 0))
            return 1;
        else
            return 0;
    }

    //Return 0: Ignore
    //Return 1: First number to -'ve
    //Return 2: Second number to -'ve
    //Return 3: First number to +'ve
    //Return 4: Second number to +'ve
    //Return 5: Equal pressed, clear screen
    public int checkPlusOrMinus(String input) {
        if (input.length() == 0 && !first_negative) {
            first_negative = true;
            return 1;
        }
        else if (input.length() == 1 && first_negative) {
            first_negative = false;
            return 3;
        }
        else if(input.length() == firstSize + 1 && !second_negative) {
            second_negative = true;
            return 2;
        }
        else if(input.length() == firstSize + 2 && second_negative) {
            second_negative = false;
            return 4;
        }
        else if(equalPressed)
            return 5;

        return 0;
    }

    public void deletePlusOrMinus(char last) {
        if(last == '-'){
            if (second_negative)
                second_negative = false;
            else if (first_negative)
                first_negative = false;
        }
    }

    public boolean getPlusOrMinusStatus() {
        if (second_negative || first_negative)
            return false;
        else
            return true;
    }
}
