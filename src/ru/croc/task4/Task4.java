package ru.croc.task4;

public class Task4 {

    public static void main(String[] args) {

        String source = """
                /*
                 * My first ever program in Java!
                 */
                class Hello { // class body starts here\s
                 \s
                  /* main method */
                  public static void main(String[] args/* we put command line arguments here*/) {
                    // this line prints my first greeting to the screen
                    System.out.println("Hi!"); // :)
                  }
                } // the end
                // to be continued...
                """;

        System.out.println(removeJavaComment(source));
    }

    public static String removeJavaComment(String source) {
        return source.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
    }
}
