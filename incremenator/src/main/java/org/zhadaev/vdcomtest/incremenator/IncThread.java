package org.zhadaev.vdcomtest.incremenator;

public class IncThread extends Thread {

    private final int startNumber;
    private final int endNumber;
    private final int step;

    public IncThread(int startNumber, int endNumber, int step) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.step = step;
    }

    @Override
    public void run() {
        do {
            if (numberIsValid()) {
                Incremenator.incrementAndWrite();
            }
        } while (Incremenator.readFile() < endNumber);
    }

    private boolean numberIsValid() {
        int currentNumber = Incremenator.readFile();
        return currentNumber % step == startNumber && currentNumber < endNumber;
    }

}
