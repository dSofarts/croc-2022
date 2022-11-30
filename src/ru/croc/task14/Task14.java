package ru.croc.task14;

public class Task14 {

    public static void main(String[] args) {

        CommentBlock commentBlock = new CommentBlock();

        commentBlock.addComment("Жал Зямка замшу, жевал Зямка жамку в замке.");
        commentBlock.addComment("Ехал Грека через реку, видит Грека — в реке рак. Сунул Грека руку в реку, рак за руку Греку — цап!");
        commentBlock.addComment("Самшит, самшит, как ты крепко сшит.");

        commentBlock.addBlacklistedWord("рак");

        commentBlock.getFilteredComments().forEach(System.out::println);
    }
}
