package org.zhadaev.vdcomtest.foobar;

import java.util.Scanner;
import java.util.stream.IntStream;

public class FooBar {

    private static final int n1 = 3;
    private static final int n2 = 5;

    public static void main(String... args) {
        String solutionNumber = solutionInput();
        choiceSolutionAndExecute(solutionNumber);
    }

    private static String solutionInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите номер решения (1-3): ");
        return sc.nextLine();
    }

    private static void choiceSolutionAndExecute(String solutionNumber) {
        switch (solutionNumber) {
            case "1":
                firstSolution();
                break;
            case "2":
                secondSolution();
                break;
            case "3":
                thirdSolution();
                break;
            default:
                System.out.format("Решения '%s' не существует", solutionNumber);
        }
    }

    private static void firstSolution() {
        int n = numberInput();
        for (int i = 1; i <= n; i++) {
            if (i % (n1 * n2) == 0) {
                System.out.println("FooBar");
                continue;
            }
            if (i % n1 == 0) {
                System.out.println("Foo");
                continue;
            }
            if (i % n2 == 0) {
                System.out.println("Bar");
            } else {
                System.out.println(i);
            }
        }
    }

    private static void secondSolution() {
        int n = numberInput();
        IntStream.rangeClosed(1, n).forEach(num -> {
            System.out.println(num % (n1 * n2) == 0 ? "FooBar" : num % n1 == 0 ? "Foo" : num % n2 == 0 ? "Bar" : num);
        });
    }

    private static void thirdSolution() {
        int n = numberInput();
        String outputString;
        for (int i = 1; i <= n; i++) {
            outputString = "";
            if (i % n1 == 0) {
                outputString = outputString.concat("Foo");
            }
            if (i % n2 == 0) {
                outputString = outputString.concat("Bar");
            }
            if (outputString.isEmpty()) {
                System.out.println(i);
            } else {
                System.out.println(outputString);
            }
        }
    }

    private static int numberInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        try {
            int number = Integer.parseInt(sc.nextLine());
            if (number > 0) {
                return number;
            } else {
                System.out.println("Число должно быть больше 0");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Введено не число");
            System.exit(1);
        }
        return 0;
    }

}
