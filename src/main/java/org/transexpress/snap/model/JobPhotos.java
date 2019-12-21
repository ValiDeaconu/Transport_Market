package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;

public class JobPhotos {
    private final int id;
    @NotNull(message = "Please enter the link")
    private final String link;
    @NotNull(message = "Please enter a jobId")
    private final int jobId;


    public JobPhotos(@JsonProperty("id") int id,
                      @JsonProperty("link") String link,
                      @JsonProperty("jobId") int jobId
                      ) {
        this.id = id;
        this.link = link;
        this.jobId = jobId;
    }

    public int getId() {
        return this.id;
    }

    public String getLink(){
        return this.link;
    }

    public int getJobId() {
        return this.jobId;
    }
}
