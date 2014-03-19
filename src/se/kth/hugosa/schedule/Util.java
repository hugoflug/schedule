package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.Random;

public class Util {
    public static <T> T getRandomElement(ArrayList<T> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

    public static int getRandomIndex(ArrayList list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

    public static int getRandomInt(int below) {
        Random random = new Random();
        return random.nextInt(below);
    }
}
