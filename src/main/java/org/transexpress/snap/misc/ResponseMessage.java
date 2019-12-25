package org.transexpress.snap.misc;

public class ResponseMessage {
    private final String message;
    private final int code;

    public ResponseMessage(String message) {
        this(message, -1);
    }

    public ResponseMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
