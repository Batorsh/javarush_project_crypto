package ru.javarush.cryptocode.cryptography;
import ru.javarush.cryptocode.fileinteraction.FileReaderWriter;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

import static ru.javarush.cryptocode.consoleui.Dialog.*;

public class CryptoCode {

    public static void start(String addressOfFile, String addressOfFileForWrite) {
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        char[] inputCharacters = textFromFile.toCharArray();
        char[] outputCharacters = new char[inputCharacters.length];
        ChangeCharByKey.setKeyToMapOfCharAccordance(key);
        for (int i = 0; i < inputCharacters.length; i++) {
            char lowCaseChar = Character.toLowerCase(inputCharacters[i]);
            outputCharacters[i] = ChangeCharByKey.change(lowCaseChar);
        }
        FileReaderWriter.writeToFile(addressOfFileForWrite, outputCharacters);
    }

    public static void startBruteForce(String addressOfFile, String addressOfFileForWrite) {
        String textFromFile = FileReaderWriter.readFromFile(addressOfFile);
        int resultKey = 0;
        char[] inputCharacters = textFromFile.toCharArray();
        char[] outputCharacters = new char[inputCharacters.length];
        char[] resultOutputCharacters = new char[inputCharacters.length];
        int maxOfCoincidence = 0;
        for (int i = 0; i < 42; i++) {
            int keyForCheck = -1*i;
            ChangeCharByKey.setKeyToMapOfCharAccordance(keyForCheck);
            for (int j = 0; j < inputCharacters.length; j++) {
                char lowCaseChar = Character.toLowerCase(inputCharacters[j]);
                outputCharacters[j] = ChangeCharByKey.change(lowCaseChar);
            }
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
