package org.zhadaev.vdcomtest.incremenator;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class IncThreadTest {

    @Test
    public void testThreadExecution() throws IOException, InterruptedException {
        File file = File.createTempFile("temp", ".txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("0");
        fileWriter.close();
        Thread incThread = new IncThread(file, 0, 100, 1);

        incThread.start();
        incThread.join();
        Scanner scanner = new Scanner(new FileReader(file));

        assertThat(scanner.hasNext()).isTrue();
        assertThat(scanner.nextLine()).isEqualTo("100");

        file.delete();
    }
}
