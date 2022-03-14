package ru.javarush.cryptocode.consoleui;

import ru.javarush.cryptocode.cryptography.CryptoCode;
import ru.javarush.cryptocode.cryptography.StaticMethod;

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
        System.out.println("Введите желаемый тип операции:\nдля шифрования то введите 1,\nесли дешифрования с известным ключом - 2,\nдешифровка методом BruteForce - 3,\n" +
                "дешифровка статистическим методом - 4");
        try {
            Scanner scanner1 = new Scanner(System.in);
            typeOfOperation = scanner1.nextInt();
        } catch (Exception e) {
            System.out.println("Не правильно введено число");
            return;
        }

        System.out.println("Введите адрес файла для чтения, например C:\\test\\file.txt");
        Scanner scanner = new Scanner(System.in);
        addressOfFileForRead = scanner.nextLine();
        if (Files.notExists(Path.of(addressOfFileForRead))) {
            System.out.println("Файл с таким адресом не существует");
            return;
        }

        System.out.println("Введите адрес файла для записи результата, например: C:\\test\\outputfile.txt");
        Scanner scanner2 = new Scanner(System.in);
        addressOfFileForWrite= scanner2.nextLine();

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
            CryptoCode.startBruteForceMethod(addressOfFileForRead, addressOfFileForWrite);
        }

        if (typeOfOperation == 4) {
            System.out.println("Введите адрес файла для примера, например C:\\test\\examplefile.txt");
            Scanner scanner3 = new Scanner(System.in);
            String addressOfFileForExample = scanner.nextLine();
            if (Files.notExists(Path.of(addressOfFileForExample))) {
                System.out.println("Файл с таким адресом не существует");
                return;
            }
            StaticMethod.startStaticMethod(addressOfFileForRead, addressOfFileForWrite, addressOfFileForExample);
        }

    }
}



