package org.zhadaev.vdcomtest.converter;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    public void testMain() throws FileNotFoundException {
        Converter.main("test-classes" + File.separator + "in.txt");

        File result = new File("./target/result.txt");
        Scanner scanner = new Scanner(new FileReader(result));

        assertThat(scanner.nextLine()).isEqualTo("1 pyramid = 1.4 bar");
        assertThat(scanner.nextLine()).isEqualTo("1 giraffe = 40 hare");
        assertThat(scanner.nextLine()).isEqualTo("Конвертация невозможна");
        assertThat(scanner.nextLine()).isEqualTo("2 kilobyte = 16384 bit");
    }

}
