package pl.jz.timecounter;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeCounter {

    private final AtomicBoolean inProgress = new AtomicBoolean();
    private long startTime = 0;

    public synchronized void startProcess() {
        this.startTime = System.currentTimeMillis();
        this.inProgress.setPlain(true);
    }

    public synchronized void stopProcess() {
        this.startTime = 0;
        this.inProgress.setPlain(false);
    }

    public synchronized long getExecutionTime() {
        return System.currentTimeMillis() - startTime;
    }

    public synchronized boolean isInProgress() {
        return inProgress.getPlain();
    }
}
