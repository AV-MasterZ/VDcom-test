package org.zhadaev.vdcomtest.incremenator;

import java.io.File;

public class IncThread extends Thread {

    private final File file;
    private final int startNumber;
    private final int endNumber;
    private final int step;

    public IncThread(File file, int startNumber, int endNumber, int step) {
        this.file = file;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.step = step;
    }

    @Override
    public void run() {
        do {
            if (numberIsValid()) {
                FileWorker.incrementAndWrite(file);
            }
        } while (FileWorker.readFile(file) < endNumber);
    }

    private boolean numberIsValid() {
        return FileWorker.currentNumber % step == startNumber && FileWorker.currentNumber < endNumber;
    }

}
