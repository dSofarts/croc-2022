package ru.croc.task12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        filterComments(comments, blackList);
        return comments;
    }

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        for (int commentIndex = 0; commentIndex < comments.size(); commentIndex++) {
            for (String blacklistedWord : blackList) {
                if (comments.get(commentIndex).toLowerCase().contains(blacklistedWord)) {
                    comments.remove(commentIndex);
                }
            }
        }
    }
}
