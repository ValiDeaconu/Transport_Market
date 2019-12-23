package org.transexpress.snap.misc;

public class Checker {
    private static Checker singleton;

    public static Checker getInstance() {
        if (singleton == null)
            singleton = new Checker();

        return singleton;
    }

    private Checker() { }

    /**
     * Checks if an id is valid (according to the database indexes)
     * @param id index to check
     * @return true if the index is between database indexes intervals
     */
    public boolean checkId(int id) {
        return id > 0;
    }

    // Online REGEX Tester: regex101.com
    /**
     * Checks if a string is empty
     * @param text string to check
     * @return true if text is empty
     */
    public boolean isEmpty(String text) {
        return text != null && text.equals("");
    }

    /**
     * Checks if a string contains only digits
     * @param text string to check
     * @return true if string is numeric
     */
    public boolean isNumeric(String text) {
        return text != null && !isEmpty(text) && text.matches("[0-9]+");
    }

    /**
     * Checks if a string contains only lowercase and uppercase letters (english dictionary)
     * @param text string to check
     * @return true if string is alphabetic
     */
    public boolean isAlphabetic(String text) {
        return text != null && !isEmpty(text) && text.matches("[a-zA-Z]+");
    }

    /**
     * Checks if a string contains only lowercase and uppercase letters (english dictionary) and also digits
     * @param text string to check
     * @return true if string is alphanumeric
     */
    public boolean isAlphanumeric(String text) {
        return text != null && !isEmpty(text) && text.matches("[a-zA-Z0-9]+");
    }

    /**
     * Checks if a string contains only letters (eng. dict.), digits, '.' and '_'
     * @param text string to check
     * @return true if string is an username
     */
    public boolean isUsername(String text) {
        return text != null && !isEmpty(text) && text.matches("[a-zA-Z0-9\\_\\.]+");
    }

    /**
     * Checks if a string is formatted as an email address (username@host.domain)
     * @param text string to check
     * @return true if string is an email
     */
    public boolean isEmail(String text) {
        return text != null && !isEmpty(text) && text.matches("[a-zA-Z0-9\\_\\.]+[\\@][a-zA-Z0-9]+[\\.][a-zA-Z]+");
    }

    /**
     * Checks if a string is a phone number (contains exactly 10 digits)
     * @param text string to check
     * @return true if string is a phone number
     */
    public boolean isPhoneNumber(String text) {
        return text != null && !isEmpty(text) && text.matches("[0-9]{10}");
    }

    /**
     * Checks if a string is an URL (protocol://website.domain<...>)
     * @param text string to check
     * @return true if string is an URL
     */
    public boolean isLink(String text) {
        return text != null && !isEmpty(text) && text.matches("(https?|ftp|file):\\/\\/[-a-zA-Z0-9+&@#\\/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#\\/%=~_|]");
    }

    /**
     * Checks if two dates are in the same day
     * @param date1 first date
     * @param date2 second date
     * @return true if dates are in the same day
     */
    public boolean isSameDay(String date1, String date2) {
        // date example: 2019-12-26 10:00:00
        return date1.substring(0, 10).equals(date2.substring(0, 10));
    }

    /**
     * Checks if a tag can be found in a tag list
     * @param tagList list of tags
     * @param tag tag to check
     * @return true if tagList contains tag
     */
    public boolean tagListContainsTag(String tagList, String tag) {
        for (String insideTag : tagList.split(";"))
            if (insideTag.equals(tag))
                return true;

        return false;
    }
}
