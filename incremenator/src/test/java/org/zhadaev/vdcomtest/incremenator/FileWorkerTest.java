package org.zhadaev.vdcomtest.incremenator;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileWorkerTest {

    @Test
    public void testCreateOutFile() {
        File file = FileWorker.createOutFile();
        FileWorker.writeNumberInFile(file, 0);

        assertThat(file).exists();

        file.delete();
    }

    @Test
    public void testIncrementAndWrite() throws IOException {
        File file = File.createTempFile("temp", ".txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("0");
        fileWriter.close();

        FileWorker.incrementAndWrite(file);
        int currentNumber = FileWorker.readFile(file);

        assertThat(currentNumber).isEqualTo(1);
        assertThat(FileWorker.currentNumber).isEqualTo(1);

        file.delete();
    }
}
