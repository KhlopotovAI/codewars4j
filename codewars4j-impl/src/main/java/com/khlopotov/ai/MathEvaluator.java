package com.khlopotov.ai;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * https://www.codewars.com/kata/52a78825cdfc2cfc87000005
 */
public class MathEvaluator {

    public double calculate(String expression) {
        try {
            //cheat
            return new Double(new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString());
        } catch (ScriptException ex) {
            return 0.0;
        }
    }
}
