package com.nimblecode.integratedaviationpersonellicencing.utility;

import java.util.Random;

public class Util {


    public static String generateReference(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 8; i++){
            s.append(digit());
        }
        return s.toString();
    }

    private static int digit(){
        Random r = new Random();
        return r.nextInt(9);
    }
}
