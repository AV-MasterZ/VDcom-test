package org.zhadaev.vdcomtest.incremenator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker {

    public static int currentNumber;

    public static File createOutFile() {
        String path = FileWorker.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        for (int i = 1; i <= 2; i++) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }
        path = path.concat(File.separator);
        return new File(path + "out.txt");
    }

    public static void incrementAndWrite(File file) {
        int oldNumber = readFile(file);
        int newNumber = oldNumber + 1;
        System.out.format("Old number: %d, New number: %d, Thread id: %d\n", oldNumber, newNumber, Thread.currentThread().getId());
        writeNumberInFile(file, newNumber);
    }

    public static int readFile(File file) {
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) currentNumber = Integer.parseInt(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentNumber;
    }

    public static synchronized void writeNumberInFile(File file, int number) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(number));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
