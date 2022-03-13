package ru.javarush.cryptocode.consoleui;

import ru.javarush.cryptocode.cryptography.CryptoCode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Dialog {
    public static Path inputFilePath;
    public static int key;
    public static String addressOfFileForRead;
    public static String addressOfFileForWrite;
    public static int typeOfOperation;

    public static void start() {
        System.out.println("Введите операции: если шифрования то введите 1,\n если дешифрование с известным ключом - 2,\n дешифровка методом BruteForce - 3");
        try {
            Scanner scanner1 = new Scanner(System.in);
            typeOfOperation = scanner1.nextInt();
        } catch (Exception e) {
            System.out.println("Не правильно введено число");
            return;
        }

        System.out.println("Введите адрес файла для чтения");
        //Scanner scanner = new Scanner(System.in);
        addressOfFileForRead = "C:\\test\\file.txt";
        inputFilePath = Path.of(addressOfFileForRead);
        if (Files.notExists(inputFilePath)) {
            System.out.println("Файл с таким адресом не существует");
            return;
        }

        System.out.println("Введите адрес файла для записи результата");
        //Scanner scanner = new Scanner(System.in);
        addressOfFileForWrite= "C:\\test\\outputfile.txt";

        if (typeOfOperation == 1 || typeOfOperation == 2) {
            System.out.println("Введите ключ от 0 до 42");
            try {
                Scanner scanner1 = new Scanner(System.in);
                key = scanner1.nextInt();
                if (typeOfOperation == 2) {
                    key = -1 * key;
                }
            } catch (Exception e) {
                System.out.println("Не правильно введено число");
                return;
            }
            CryptoCode.start(addressOfFileForRead, addressOfFileForWrite, key);
        }
        if (typeOfOperation == 3) {
            CryptoCode.startBruteForce(addressOfFileForRead, addressOfFileForWrite);
        }

    }
}



