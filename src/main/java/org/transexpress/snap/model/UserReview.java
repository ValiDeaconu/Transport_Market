package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserReview {
    private final int id;
    @NotNull(message = "Please enter a description")
    private final String description;
    @NotNull(message = "Please enter the rate")
    private final int rate;
    @NotNull(message = "Please enter user id")
    private final int userId;

    public UserReview(@JsonProperty("id") int id,
                      @JsonProperty("description") String description,
                      @JsonProperty("rate") int rate,
                      @JsonProperty("userId") int userId){
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.userId = userId;
    }

    public int getId(){return this.id;}
    public String getDescription(){return this.description;}
    public int getRate(){return this.rate;}
    public int getUserId(){return this.userId;}


}
