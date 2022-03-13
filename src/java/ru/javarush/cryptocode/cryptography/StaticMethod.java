package ru.javarush.cryptocode.cryptography;

import ru.javarush.cryptocode.fileinteraction.FileReaderWriter;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StaticMethod {

    private static final char[] ALPHABET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '(', ')', '"', '\'', ':', '!', '?', ' ', '-', '+'};

    public static void startStaticMethod(String addressOfFile, String addressOfFileForWrite, String adressOfFileForExample) {
        String exampleText = FileReaderWriter.readFromFile(adressOfFileForExample);
        char[] exampleCharacters = exampleText.toCharArray();
        HashMap<Character, Integer> exampleMap = createMapOfStatistics(exampleCharacters);
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        char[] inputCharacters = textFromFile.toCharArray();
        int resultKey = 0;
        char[] outputCharacters;
        char[] resultOutputCharacters = new char[inputCharacters.length];
        double minDifference = Double.MAX_VALUE;
        for (int i = 0; i < 42; i++) {
            int keyForCheck = -1 * i;
            outputCharacters = CypherBox.getShiftedArray(keyForCheck, inputCharacters);
            HashMap<Character, Integer> tempMap = createMapOfStatistics(outputCharacters);
            double countDeviation = getSumOfSquaresOfDeviation(tempMap, exampleMap);
            System.out.println(countDeviation);
            if (minDifference > countDeviation) {
                minDifference = countDeviation;
                resultKey = keyForCheck;
                resultOutputCharacters = Arrays.copyOf(outputCharacters, outputCharacters.length);
            }
        }
        System.out.println("Минимальное отличие: " + minDifference + ". Ключ: " + resultKey);
        FileReaderWriter.writeToFile(addressOfFileForWrite, resultOutputCharacters);

    }

    public static HashMap<Character, Integer> createMapOfStatistics(char[] inputCharacters) {
        HashMap<Character, Integer> mapOfCharCoincidences = new HashMap<>();
        //char[] inputCharacters = text.toCharArray();
        for (int i = 0; i < ALPHABET.length; i++) {
            int count = 0;
            for (int j = 0; j < inputCharacters.length; j++) {
                if (ALPHABET[i] == inputCharacters[j]) {
                    count++;
                }
            }
            mapOfCharCoincidences.put(ALPHABET[i], count * 1000 / inputCharacters.length);
        }
        return mapOfCharCoincidences;
    }

    public static double getSumOfSquaresOfDeviation(HashMap<Character, Integer> mapForComparison, HashMap<Character, Integer> exampleMap) {
        double sumOfSquares = 0;
        for (Map.Entry<Character, Integer> pair : exampleMap.entrySet()) {
            sumOfSquares += Math.pow(pair.getValue() - mapForComparison.get(pair.getKey()), 2);
        }
        return sumOfSquares;
    }
}

