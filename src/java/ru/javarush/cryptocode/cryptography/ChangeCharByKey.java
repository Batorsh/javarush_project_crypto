package ru.javarush.cryptocode.cryptography;

import ru.javarush.cryptocode.consoleui.Dialog;
import java.util.*;

public class ChangeCharByKey {
    public static Map<Character, Character> mapOfCharAccordance = new HashMap<>();
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    static {
        if (Dialog.key >= 0) {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (i + Dialog.key < ALPHABET.length) {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + Dialog.key]);
                } else {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + Dialog.key - ALPHABET.length]);
                }
            }
        } else {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (i + Dialog.key < 0) {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + Dialog.key + ALPHABET.length]);
                } else {
                    mapOfCharAccordance.put(ALPHABET[i], ALPHABET[i + Dialog.key]);
                }
            }
        }
        for (Map.Entry<Character, Character> pair : mapOfCharAccordance.entrySet()
        ) {
            System.out.println("Мапа: " + pair.getKey() + " - " + pair.getValue());
        }
    }

    public static char change(char inputChar) {
        return mapOfCharAccordance.get(inputChar);
    }
}
