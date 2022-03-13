package ru.javarush.cryptocode.cryptography;

import ru.javarush.cryptocode.fileinteraction.FileReaderWriter;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StaticMethod {
    private static Map<Character, Integer> mapOfCharCoincidences = new HashMap<>();
    private static final char[] ALPHABET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '(', ')', '"', '\'', ':', '!', '?', ' ', '-', '+'};

    public static void startStaticMethod(String addressOfFile, String addressOfFileForWrite) {
        String exampleText = FileReaderWriter.readFromFile("C:\\test\\examplefile.txt");
        char[] exampleCharacters = exampleText.toCharArray();
        double exampleDeviation = getStandartDeviation(exampleCharacters);
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        char[] inputCharacters = textFromFile.toCharArray();
        int resultKey = 0;
        char[] outputCharacters;
        char[] resultOutputCharacters = new char[inputCharacters.length];
        double minDifference = 0;
        for (int i = 0; i < 42; i++) {
            int keyForCheck = -1*i;
            outputCharacters = CypherBox.getShiftedArray(keyForCheck, inputCharacters);
            double countDeviation = getStandartDeviation(outputCharacters);
            System.out.println(countDeviation);
            if (minDifference > Math.abs(exampleDeviation - countDeviation)) {
                minDifference = Math.abs(exampleDeviation - countDeviation);

                resultKey = keyForCheck;
                resultOutputCharacters = Arrays.copyOf(outputCharacters, outputCharacters.length);
            }
        }
        System.out.println("Минимальное отличие: " + minDifference + ". Ключ: " + resultKey);
        //FileReaderWriter.writeToFile(addressOfFileForWrite, resultOutputCharacters);

    }

    public static double getStandartDeviation(char[] inputCharacters) {
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

        int ariphmeticMean = 0;
        for (Map.Entry<Character, Integer> pair : mapOfCharCoincidences.entrySet()) {
            ariphmeticMean += pair.getValue();
            //System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        ariphmeticMean = ariphmeticMean / ALPHABET.length;
        System.out.println("Среднее арифметическое: " + ariphmeticMean);
        double sumOfSquares = 0;
        for (Map.Entry<Character, Integer> pair : mapOfCharCoincidences.entrySet()) {
            sumOfSquares += Math.pow(pair.getValue() - ariphmeticMean, 2);
            //System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        double standartDeviation = Math.sqrt(sumOfSquares / (inputCharacters.length - 1));

        return standartDeviation;
    }
}
