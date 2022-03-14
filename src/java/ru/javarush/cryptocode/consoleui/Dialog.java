package ru.javarush.cryptocode.consoleui;

import ru.javarush.cryptocode.cryptography.CryptoCode;
import ru.javarush.cryptocode.cryptography.StaticMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Dialog {

    public static void start() {
        int typeOfOperation = getTypeOfOperation();
        String addressOfFileForRead = getAddressOfFileForRead();
        String addressOfFileForWrite = getAddressOfFileForWrite();

        if (typeOfOperation == 1 || typeOfOperation == 2) {
            int key = getKey(typeOfOperation);
            CryptoCode.start(addressOfFileForRead, addressOfFileForWrite, key);
        }

        if (typeOfOperation == 3) {
            CryptoCode.startBruteForceMethod(addressOfFileForRead, addressOfFileForWrite);
        }

        if (typeOfOperation == 4) {
            String addressOfFileForExample = getAddressOfFileForExample();
            StaticMethod.startStaticMethod(addressOfFileForRead, addressOfFileForWrite, addressOfFileForExample);
        }

    }

    private static int getTypeOfOperation() {
        System.out.println("Введите желаемый тип операции:\nдля шифрования введите 1,\nдля дешифрования с известным ключом - 2,\nдля дешифровки методом BruteForce - 3,\n" +
                "для дешифровки статистическим методом - 4,\nдля выхода введите отрицательное число или больше 4.");
        try {
            Scanner scanner1 = new Scanner(System.in);
            int typeOfOperation = scanner1.nextInt();
            if (typeOfOperation < 0 || typeOfOperation > 4) {
                System.out.println("Программа завершена");
                System.exit(1);
            }
            return typeOfOperation;
        } catch (Exception e) {
            System.out.println("Не правильно введено число, попробуйте ещё раз.");
            return getTypeOfOperation();
        }
    }

    private static String getAddressOfFileForRead() {
        System.out.println("Введите адрес файла для чтения, адрес должен начинаться с \"C:\\test\\\", а заканчиваться на \".txt\", например C:\\test\\file.txt\n" +
                "для выхода введите exit или quit");
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        if (address.equalsIgnoreCase("exit") || address.equalsIgnoreCase("quit")) {
            System.out.println("Программа завершена");
            System.exit(1);
        }
        if (!address.startsWith("C:\\test\\") || !address.endsWith(".txt")) {
            System.out.println("Адрес файла не верный, введите ещё раз");
            return getAddressOfFileForRead();
        }
        if (Files.notExists(Path.of(address))) {
            System.out.println("Файл с таким адресом не существует, попробуйте ввести еще раз.");
            return getAddressOfFileForRead();
        }
        return address;
    }

    private static String getAddressOfFileForWrite() {
        System.out.println("Введите адрес файла для записи результата, адрес должен начинаться с \"C:\\test\\\", а заканчиваться на \".txt\",\n" +
                "например: C:\\test\\outputfile.txt, для выхода введите exit или quit");
        Scanner scanner2 = new Scanner(System.in);
        String address = scanner2.nextLine();
        if (address.equalsIgnoreCase("exit") || address.equalsIgnoreCase("quit")) {
            System.out.println("Программа завершена");
            System.exit(1);
        }
        if (!address.startsWith("C:\\test\\") || !address.endsWith(".txt")) {
            System.out.println("Адрес файла не верный, введите ещё раз");
            return getAddressOfFileForWrite();
        }
        return address;
    }

    private static int getKey(int typeOfOperation) {
        System.out.println("Введите ключ от 0 до 42, для выхода введите число меньше 0 или больше 42");
        try {
            Scanner scanner1 = new Scanner(System.in);
            int key = scanner1.nextInt();
            if (key < 0 || key > 42) {
                System.out.println("Программа завершена.");
                System.exit(1);
            }
            if (typeOfOperation == 2) {
                return -1 * key;
            }
            return key;
        } catch (Exception e) {
            System.out.println("Не правильно введено число, попробуйте ввести ещё раз");
            return getKey(typeOfOperation);
        }
    }

    private static String getAddressOfFileForExample(){
        System.out.println("Введите адрес файла для примера, например C:\\test\\examplefile.txt, для выхода введите exit или quit");
        Scanner scanner3 = new Scanner(System.in);
        String addressOfFileForExample = scanner3.nextLine();
        if (addressOfFileForExample.equalsIgnoreCase("exit") || addressOfFileForExample.equalsIgnoreCase("quit")) {
            System.exit(1);
        }
        if (!addressOfFileForExample.startsWith("C:\\test\\") || !addressOfFileForExample.endsWith(".txt")) {
            System.out.println("Адрес файла не верный, введите ещё раз");
            return getAddressOfFileForExample();
        }
        if (Files.notExists(Path.of(addressOfFileForExample))) {
            System.out.println("Файл с таким адресом не существует, введите ещё раз");
            return getAddressOfFileForExample();
        }
        return addressOfFileForExample;
    }
}



