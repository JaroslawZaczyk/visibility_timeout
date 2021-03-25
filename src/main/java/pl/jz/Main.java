package pl.jz;


import pl.jz.process.Message;
import pl.jz.process.Process;
import pl.jz.process.QueueListener;
import pl.jz.timecounter.TimeCounter;

public class Main {

    public static void main(String[] args) {

        QueueListener listener = new QueueListener(new Process(), new TimeCounter());
        listener.listen(new Message(3000));

    }
}
