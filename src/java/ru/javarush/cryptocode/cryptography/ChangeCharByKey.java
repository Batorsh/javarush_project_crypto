package ru.javarush.cryptocode.cryptography;

import java.util.*;

public class ChangeCharByKey {
    public static Map<Character, Character> mapOfCharAccordance = new HashMap<>();
    private static final char[] ALPHABET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '(', ')', '"', '\'', ':', '!', '?', ' ', '-', '+'};

    public static void setKeyToMapOfCharAccordance(int setKey){
        if (setKey >= 0) {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (i + setKey < ALPHABET.length) {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + setKey]);
                } else {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + setKey - ALPHABET.length]);
                }
            }
        } else {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (i + setKey < 0) {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + setKey + ALPHABET.length]);
                } else {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + setKey]);
                }
            }
        }
    }

    public static char change(char inputChar) {
        if (!mapOfCharAccordance.containsKey(inputChar)) {
            System.out.println(inputChar);
            return inputChar;
        }
        return mapOfCharAccordance.get(inputChar);
    }
}
