package pl.jz.process;

public class Message {

    private long visibilityTimeout;

    public Message(long visibilityTimeout) {
        this.visibilityTimeout = visibilityTimeout;
    }

    public long getVisibilityTimeout() {
        return visibilityTimeout;
    }

    public void setVisibilityTimeout(long visibilityTimeout) {
        this.visibilityTimeout = visibilityTimeout;
    }
}
