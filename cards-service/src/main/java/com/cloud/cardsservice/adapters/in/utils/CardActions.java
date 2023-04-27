package com.cloud.cardsservice.adapters.in.utils;

import java.util.Random;

public class CardActions {

    public static String generateTuitionCard(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateNipCard(){
        Random random = new Random();
        int nipCard = random.nextInt(100000,999998) + 100000;
        return Integer.toString(nipCard);
    }

}
