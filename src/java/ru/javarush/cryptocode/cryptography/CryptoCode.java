package ru.javarush.cryptocode.cryptography;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static ru.javarush.cryptocode.consoleui.Dialog.*;

public class CryptoCode {

    // pathOfFile
    public static void start() {
        String textFromFile = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(addressOfFile))) {
            while (reader.ready()) {
                textFromFile = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        char[] inputCharacters = textFromFile.toCharArray();
        char[] outputCharacters = new char[inputCharacters.length];
        for (int i = 0; i < inputCharacters.length; i++) {
            char lowCaseChar = Character.toLowerCase(inputCharacters[i]);
            outputCharacters[i] = ChangeCharByKey.change(lowCaseChar);
        }

        String newFileName = getNewFileName(addressOfFile, "crypted");
        Path outputFilePath = Paths.get(newFileName);
        if (Files.notExists(outputFilePath)) {
            try {
                Files.createFile(outputFilePath);
            } catch (Exception e) {
                System.out.println("Ошибка при создании файла");
                e.printStackTrace();
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFileName))) {
            bufferedWriter.write(outputCharacters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNewFileName(String oldFileName, String addition) {
        int dotIndex = oldFileName.lastIndexOf(".");
        return oldFileName.substring(0, dotIndex) + addition + oldFileName.substring(dotIndex);
    }

}
