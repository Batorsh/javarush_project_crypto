package ru.javarush.cryptocode.cryptography;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CounterOfCoincidenceSpaceAndDots {

    private static final Set<Character> SET_OF_PUNCTUATION_MARKS = Set.of('.', ',', ':', '!', '?');

    public static int countOfCoincidenceSpaceAndDots(char[] charsForAnalyze) {
        int count = 0;
        for (int i = 0; i < charsForAnalyze.length - 1; i++) {
            if (SET_OF_PUNCTUATION_MARKS.contains(charsForAnalyze[i])) {
                if (charsForAnalyze[i + 1] == ' ' || charsForAnalyze[i + 1] == '\n') {
                    count++;
                }
            }
        }
        return count;
    }

}
