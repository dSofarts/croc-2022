package ru.croc.task14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class CommentBlock implements BlackListFilter {

    private List<String> comments = new ArrayList<>();
    private Set<String> blackList = new HashSet<>();

    /**
     * Добавить комментарий в блок комментариев
     * @param comment комментарий
     */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
     * Добавить слово в черный список
     * @param blacklistedWord слово
     */
    public void addBlacklistedWord(String blacklistedWord) {
        blackList.add(blacklistedWord.toLowerCase());
    }

    /**
     * Получить все отфильтрованные комментарии
     * @return все отфильтрованные комментарии
     */
    public List<String> getFilteredComments() {
        // организуем метод проверки комментариев
        String[] item = blackList.toArray(new String[blackList.size()]);
        Predicate<String> blackListPredicate = comment -> Arrays.stream(item).anyMatch(comment.toLowerCase()::contains);

        // возвращаем новый лист проверенных комментариев
        return filterComments(comments, blackListPredicate);
    }
}
