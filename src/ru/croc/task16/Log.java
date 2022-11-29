package ru.croc.task16;

public class Log {

    private long time;
    private String log;

    public Log(long time, String log) {
        this.time = time;
        this.log = log;
    }

    public long getTime() {
        return time;
    }

    public String getLog() {
        return log;
    }
}
