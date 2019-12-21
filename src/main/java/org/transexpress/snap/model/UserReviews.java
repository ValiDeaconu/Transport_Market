package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserReviews {
    private final int id;
    @NotNull(message = "Please enter a description")
    private final String description;
    @NotNull(message = "Please enter the rate")
    private final byte rate;
    @NotNull(message = "Please enter user id")
    private final int userId;

    public UserReviews(@JsonProperty("id") int id,
                       @JsonProperty("description") String description,
                       @JsonProperty("rate") byte rate,
                       @JsonProperty("userId") int userId){
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.userId = userId;
    }

    public int getId(){return this.id;}
    public String getDescription(){return this.description;}
    public byte getRate(){return this.rate;}
    public int getUserId(){return this.userId;}


}
