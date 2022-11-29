package ru.croc.task16;

public class Log {

    private long time;
    private String log;

    public Log(long time, String log) {
        this.time = time;
        this.log = log;
    }

    /**
     * Получить время лога
     * @return время лога
     */
    public long getTime() {
        return time;
    }

    /**
     * Получить весь лог
     * @return лог
     */
    public String getLog() {
        return log;
    }
}
