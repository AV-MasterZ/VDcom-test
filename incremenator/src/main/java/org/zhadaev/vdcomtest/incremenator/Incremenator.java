package org.zhadaev.vdcomtest.incremenator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Incremenator {

    private static File out;
    private static int enteredNumber;
    private static List<Thread> threads;

    public static void main(String... args) {
        out = FileWorker.createOutFile();
        FileWorker.writeNumberInFile(out, 0);
        waitForNumberInput();
        createThreads(2);
        execute();
        System.out.format("Текущее число: '%d'", FileWorker.readFile(out));
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
            threads.add(new IncThread(out, n, enteredNumber, numberOfThreads));
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
}
