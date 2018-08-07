package com.khlopotov.ai;

import javassist.*;

/**
 * https://www.codewars.com/kata/5510caecaacf801f820002ac
 */
public class Catch22 {

    private Catch22() {
        //def ctor
    }

    public static Yossarian loophole() throws Throwable {
        ClassPool classPool = ClassPool.getDefault();

        CtClass yossarian = classPool.get("Yossarian");
        CtMethod isCrazy = yossarian.getDeclaredMethod("isCrazy");
        isCrazy.setModifiers(Modifier.PUBLIC);
        yossarian.toClass();

        //Create crazy Yossarian
        CtClass crazyYossarian = classPool.makeClass("CrazyYossarian", yossarian);
        CtMethod newIsCrazy = CtNewMethod.make("public boolean isCrazy() { return true; }", crazyYossarian);
        crazyYossarian.addMethod(newIsCrazy);

        return (Yossarian) crazyYossarian.toClass().newInstance();
    }
}

//mock
class Yossarian {}