package pl.jz.process;

public class Process {

    public void performALongProcess() throws InterruptedException {
        System.out.println("Starting....");
        Thread.sleep(1000);
        System.out.println("Ending...");
    }
}
