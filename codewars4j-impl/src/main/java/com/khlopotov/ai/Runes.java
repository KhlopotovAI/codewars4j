package com.khlopotov.ai;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.codewars.com/kata/546d15cebed2e10334000ed9
 */
public class Runes {

    private Runes() {
        //def ctor
    }

    public static int solveExpression(final String expression) {
        Expression expr = parseExpression(expression);
        return expr.getMissingRune();
    }

    public static Expression parseExpression(String expression) {
        Expression expr = new Expression();

        //left number
        Pattern firstPattern = Pattern.compile("-??[\\d?]+[+*-]");
        Matcher firstMatcher = firstPattern.matcher(expression);
        if (firstMatcher.find()) {
            expr.setFirst(firstMatcher.group());
            expr.setFirst(expr.getFirst().substring(0, expr.getFirst().length() - 1));
        }

        //operation
        Pattern secondPattern = Pattern.compile("[+*-]-??[\\d?]+");
        Matcher secondMatcher = secondPattern.matcher(expression);
        if (secondMatcher.find(1)) {
            String operatorPlusSecond = secondMatcher.group();
            expr.setOperation(operatorPlusSecond.charAt(0));
            expr.setSecond(operatorPlusSecond.substring(1));
        }

        //right number
        Pattern thirdPattern = Pattern.compile("=-??[\\d?]+");
        Matcher thirdMatcher = thirdPattern.matcher(expression);
        if (thirdMatcher.find()) {
            expr.setResult(thirdMatcher.group().substring(1));
        }

        return expr;
    }
}

class Expression {
    private String first;
    private char operation;
    private String second;
    private String result;

    public Expression() {
    }

    public Expression(String first, char operation, String second, String result) {
        this.first = first;
        this.operation = operation;
        this.second = second;
        this.result = result;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getMissingRune() {
        List<Integer> digits = getAvailableDigits();
        digits.sort(Comparator.naturalOrder());
        for (Integer digit : digits) {
            try {
                if (checkRune(digit)) return digit;
            } catch (NumberFormatException ignored) {}
        }
        return -1;
    }

    private boolean checkRune(int rune) {
        Integer f = replaceQuestion(getFirst(), rune);
        Integer s = replaceQuestion(getSecond(), rune);
        Integer r = replaceQuestion(getResult(), rune);
        if (operation == '*') {
            return  f * s == r;
        }
        if (operation == '+') {
            return  f + s == r;
        }
        if (operation == '-') {
            return f - s == r;
        }
        return false;
    }

    private Integer replaceQuestion(String value, int rune) {
        Integer res = Integer.valueOf(value.replace("?", String.valueOf(rune)));

        if (String.valueOf(res).length() != value.length()) {
            throw new NumberFormatException();
        }

        return res;
    }

    private List<Integer> getAvailableDigits() {
        Set<Integer> numbers = new HashSet<>();
        for (char c : this.toString().toCharArray()) {
            try {
                numbers.add(Character.getNumericValue(c));
            } catch (ClassCastException ignored) {}
        }
        List<Integer> availableDigits = new ArrayList<>();

        for (Integer i = 0; i <= 9; i++)
            if (!numbers.contains(i)) availableDigits.add(i);

        return availableDigits;
    }

    @Override
    public String toString() {
        return first + operation + second + '=' + result;
    }
}
