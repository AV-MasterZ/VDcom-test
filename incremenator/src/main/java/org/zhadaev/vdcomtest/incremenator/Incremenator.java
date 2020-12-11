package org.zhadaev.vdcomtest.incremenator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Incremenator {

    private static RandomAccessFile randomAccessFile;
    private static int enteredNumber;
    private static List<Thread> threads;
    private static int currentNumber;

    public static void main(String... args) throws IOException {
        createTempFile();
        waitForNumberInput();
        createThreads(2);
        execute();
        randomAccessFile.close();
        System.out.format("Текущее число: '%d'", currentNumber);
    }

    private static void createTempFile() {
        try {
            File out = File.createTempFile("out", ".txt");
            randomAccessFile = new RandomAccessFile(out, "rw");
            writeNumberInFile(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void waitForNumberInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        try {
            int number = Integer.parseInt(sc.nextLine());
            if (number > 0 && number % 2 == 0) {
                enteredNumber = number;
            } else {
                System.out.println("Число должно быть больше 0 и кратно 2");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введено не число");
        }
    }

    private static void createThreads(int numberOfThreads) {
        threads = new ArrayList<>(numberOfThreads);
        if (numberOfThreads < 1) return;
        IntStream.range(0, numberOfThreads).forEach(n -> {
            threads.add(new IncThread(n, enteredNumber, numberOfThreads));
        });
    }

    private static void execute() {
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void incrementAndWrite() {
        int oldNumber = readFile();
        int newNumber = oldNumber + 1;
        System.out.format("Old number: %d, New number: %d, Thread id: %d\n", oldNumber, newNumber, Thread.currentThread().getId());
        writeNumberInFile(newNumber);
    }

    public static synchronized int readFile() {
        try {
            randomAccessFile.seek(0);
            String line = randomAccessFile.readLine();
            currentNumber = Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentNumber;
    }

    private static synchronized void writeNumberInFile(int number) {
        try {
            randomAccessFile.setLength(0);
            randomAccessFile.writeBytes(String.valueOf(number));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
