package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Order {
    private final int id;
    @NotNull(message = "Please enter user id")
    private final int userId;
    @NotNull(message = "Please enter job id")
    private final int jobId;

    public Order(@JsonProperty("id") int id,
                 @JsonProperty("userId") int userId,
                 @JsonProperty("jobId") int jobId) {
        this.id = id;
        this.userId = userId;
        this.jobId = jobId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getJobId() {
        return jobId;
    }
}
