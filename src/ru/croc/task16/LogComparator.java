package ru.croc.task16;

import java.util.Comparator;

public class LogComparator implements Comparator<Log> {

    @Override
    public int compare(Log o1, Log o2) {
        return Long.compare(o1.getTime(), o2.getTime());
    }
}
