package ru.javarush.cryptocode.cryptography;

import ru.javarush.cryptocode.fileinteraction.FileReaderWriter;

import java.util.Arrays;


public class CryptoCode {

    public static void start(String addressOfFile, String addressOfFileForWrite, int key) {
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        char[] inputCharacters = textFromFile.toCharArray();
        char[] outputCharacters = CypherBox.getShiftedArray(key, inputCharacters);
        FileReaderWriter.writeToFile(addressOfFileForWrite, outputCharacters);
    }

    public static void startBruteForceMethod(String addressOfFile, String addressOfFileForWrite) {
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        char[] inputCharacters = textFromFile.toCharArray();
        int resultKey = 0;
        char[] outputCharacters;
        char[] resultOutputCharacters = new char[inputCharacters.length];
        int maxOfCoincidence = 0;
        for (int i = 0; i < 42; i++) {
            int keyForCheck = -1 * i;
            outputCharacters = CypherBox.getShiftedArray(keyForCheck, inputCharacters);
            int count = CounterOfCoincidenceSpaceAndDots.countOfCoincidenceSpaceAndDots(outputCharacters);
            if (maxOfCoincidence < count) {
                maxOfCoincidence = count;
                resultKey = keyForCheck;
                resultOutputCharacters = Arrays.copyOf(outputCharacters, outputCharacters.length);
            }
        }
        System.out.println("Максимальное совпадение: " + maxOfCoincidence + ". Ключ: " + resultKey);
        FileReaderWriter.writeToFile(addressOfFileForWrite, resultOutputCharacters);
    }


}
