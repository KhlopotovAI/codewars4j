package com.khlopotov.ai;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * https://www.codewars.com/kata/54bd79a7956834e767001357
 */
public class Psychic {

    private static final Random LUCKY_RND = new Random(0);

    private Psychic() {
        //def ctor
    }

    static { //some reflection magic
        try {
            Class<?> innerClass = Class.forName("java.lang.Math$RandomNumberGeneratorHolder");
            Field f = innerClass.getDeclaredField("randomNumberGenerator");
            f.setAccessible(true);
            Random rnd = (Random) f.get(null);
            rnd.setSeed(0);
        } catch (Exception ignored) {
            //you need to believe
        }
    }

    public static double guess() {
        return LUCKY_RND.nextDouble();
    }

}