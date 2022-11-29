package ru.croc.task14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter {

    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments  list of comments;
     * @param blackList list of words that should not
     *                  be present in a comment
     */
    default <T> List<T> filterComments(Iterable<T> comments, Predicate<T> blackList) {
        List<T> filteredComments = new ArrayList<>();
        for (T comment : comments) {
            if (!blackList.test(comment)) {
                filteredComments.add(comment);
            }
        }
        return filteredComments;
    }
}

