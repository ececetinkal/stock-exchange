package com.stock.exchange.util;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public static double getRandomDouble(){
        return random.nextDouble();
    }
}
