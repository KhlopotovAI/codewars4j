package com.khlopotov.ai;

/**
 * https://www.codewars.com/kata/5235c913397cbf2508000048
 */

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Calculator {

    private Calculator() {
        //def ctor
    }

    public static Double evaluate(String expression) {
        List<String> strings = stream(expression.split(" ")).collect(Collectors.toList());

        Double result;
        while (indexOfOperation(strings) != -1) {
            int indexOfOperation = indexOfOperation(strings);

            result = solve(strings, indexOfOperation);

            strings.set(indexOfOperation, String.valueOf(result));
            strings.remove(indexOfOperation - 1);
            strings.remove(indexOfOperation);
        }

        return Double.parseDouble(strings.get(0));
    }

    //Get next operation by priority
    private static int indexOfOperation(List<String> expression) {
        int mult = expression.indexOf("*");
        int div = expression.indexOf("/");
        int add = expression.indexOf("+");
        int sub = expression.indexOf("-");

        if (mult != -1 && div != -1) return Math.min(mult, div);
        if (mult == -1 && div != -1) return div;
        if (mult != -1) return mult;

        if (add != -1 && sub != -1) return Math.min(add, sub);
        if (add == -1 && sub != -1) return sub;
        if (add != -1) return add;

        return -1;
    }

    //solve simple expression
    private static double solve(List<String> list, int index) {
        if (list.get(index).equals("*")) {
            return Double.parseDouble(list.get(index - 1)) * Double.parseDouble(list.get(index + 1));
        }
        if (list.get(index).equals("/")) {
            return Double.parseDouble(list.get(index - 1)) / Double.parseDouble(list.get(index + 1));
        }
        if (list.get(index).equals("+")) {
            return Double.parseDouble(list.get(index - 1)) + Double.parseDouble(list.get(index + 1));
        }
        if (list.get(index).equals("-")) {
            return Double.parseDouble(list.get(index - 1)) - Double.parseDouble(list.get(index + 1));
        }
        throw new UnsupportedOperationException("Unknown operation");
    }

}
