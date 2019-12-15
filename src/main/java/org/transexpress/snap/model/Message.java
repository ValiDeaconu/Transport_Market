package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Message {

    private final int id;
    @NotEmpty(message = "Please enter message")
    private final String message;
    @NotEmpty(message = "Please enter date")
    private final String date;
    @NotNull(message = "Please enter sender id")
    private final int senderId;
    @NotNull(message = "Please enter receiver id")
    private final int receiverId;

    public Message(@JsonProperty("id") int id,
                   @JsonProperty("message") String message,
                   @JsonProperty("date") String date,
                   @JsonProperty("senderId") int senderId,
                   @JsonProperty("receiverId") int receiverId) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }
}
