package org.transexpress.snap.misc;

public class Formatter {
    private static Formatter singleton;

    public static Formatter getInstance() {
        if (singleton == null)
            singleton = new Formatter();

        return singleton;
    }

    private Formatter() { }

    /**
     * Adds a '\' character in front of all characters received as parameter
     * @param text string which needs to be braced
     * @param character character which needs to be braced
     * @return braced text
     */
    public String brace(String text, char character) {
        String charStr = character + "";
        return text.replace(charStr, "\\" + character);
    }

    /**
     * Brace a text by escaping ''' and '"' characters
     * @param text string which needs to be braced
     * @return braced text
     */
    public String secureQuotes(String text) {
        String inter = brace(text, '\'');
        return brace(inter, '\"');
    }
}
