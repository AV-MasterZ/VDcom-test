package org.zhadaev.vdcomtest.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Converter {

    public static void main(String... args) {
        String filePath = getCurrentPath();
        if (args.length == 0) {
            filePath = filePath.concat("in.txt");
        } else {
            filePath = filePath.concat(args[0]);
        }
        List<Equality> equalities = FileParser.parseFile(filePath);
        UnitGraph unitGraph = UnitGraph.fromEqualityList(equalities);
        List<Equality> notFilledEqualities = equalities.stream()
                                                        .filter(equality -> equality.getRightValue() == null)
                                                        .collect(Collectors.toList());
        notFilledEqualities.forEach(equality -> {
            Optional<Double> rightValue = unitGraph.calculateRightValueOfEquality(equality);
            rightValue.ifPresent(equality::setRightValue);
        });
        writeResultFile(notFilledEqualities);
    }

    private static void writeResultFile(List<Equality> equalities) {
        File out = new File(getCurrentPath() + "result.txt");
        try {
            FileWriter fileWriter = new FileWriter(out);
            for (Equality equality: equalities) {
                if (equality.getRightValue() == null) {
                    fileWriter.write("Конвертация невозможна");
                } else {
                    fileWriter.write(equality.toString());
                }
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentPath() {
        String path = Converter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        for (int i = 1; i <= 2; i++) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }
        path = path.concat(File.separator);
        return path;
    }

}
