package ru.javarush.cryptocode.consoleui;

import ru.javarush.cryptocode.cryptography.CryptoCode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Dialog {
    public static Path inputFilePath;
    public static int key;
    public static String addressOfFile;
    public static int typeOfOperation;

    public static void start() {
        System.out.println("Введите операции: если шифрования то введите 1, если дешифрования то 2");
        try {Scanner scanner1 = new Scanner(System.in);
            typeOfOperation = scanner1.nextInt();
        } catch (Exception e) {
            System.out.println("Не правильно введено число");
            return;
        }

        System.out.println("Введите адрес файла");
        Scanner scanner = new Scanner(System.in);
        addressOfFile = "C:\\test\\file.txt";
        inputFilePath = Path.of(addressOfFile);
        if (Files.notExists(inputFilePath)) {
            System.out.println("Файл с таким адресом не существует");
           return;
        }

        System.out.println("Введите ключ от 0 до 32");
        try {Scanner scanner1 = new Scanner(System.in);
        key = scanner1.nextInt();
        } catch (Exception e) {
            System.out.println("Не правильно введено число");
            return;
        }
        CryptoCode.start();
    }
}



