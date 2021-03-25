package pl.jz.process;

import pl.jz.timecounter.TimeCounter;

import java.util.concurrent.*;

public class QueueListener {

    private final Process process;
    private final TimeCounter timeCounter;

    public QueueListener(Process process, TimeCounter timeCounter) {
        this.process = process;
        this.timeCounter = timeCounter;
    }

    public void listen(Message message) {
        ExecutorService executorService = new ThreadPoolExecutor(1,1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        Runnable runnable = this::startProcess;
        Future<?> submit = executorService.submit(runnable);

        while (!submit.isDone()) {
            long timeLeft = Math.abs(message.getVisibilityTimeout() - timeCounter.getExecutionTime());
            if (timeCounter.isInProgress() & timeLeft < 999) {
                System.out.println("Execution time: " + timeCounter.getExecutionTime());
                System.out.println("Increasing time - time left: " + timeLeft);
                message.setVisibilityTimeout(message.getVisibilityTimeout() + 5000);
                System.out.println("Time left: " + message.getVisibilityTimeout());
            }
        }

        System.out.println("TOTAL VISIBILITY TIMEOUT: " + message.getVisibilityTimeout());
        System.out.println("done");
        executorService.shutdown();

    }

    private void startProcess() {
        try {
            timeCounter.startProcess();
            process.performALongProcess();
            timeCounter.stopProcess();
        } catch (Exception ex) {
            timeCounter.stopProcess();
            System.out.println(ex);
        }
    }

}
