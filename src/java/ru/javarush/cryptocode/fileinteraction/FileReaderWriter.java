package ru.javarush.cryptocode.fileinteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileReaderWriter {
    public static String readFromFile(String addressOfFIleForRead) {
        StringBuilder textFromFile= new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(addressOfFIleForRead))) {
            while (reader.ready()) {
                textFromFile.append(reader.readLine()).append(" ");
            }
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return textFromFile.toString();
    }

    public static void writeToFile(String addressOfFileForWrite, char[] outputCharacters) {

        Path outputFilePath = Paths.get(addressOfFileForWrite);
        if (Files.notExists(outputFilePath)) {
            try {
                Files.createFile(outputFilePath);
            } catch (Exception e) {
                System.out.println("Ошибка при создании файла для записи");
                e.printStackTrace();
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(addressOfFileForWrite))) {
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
