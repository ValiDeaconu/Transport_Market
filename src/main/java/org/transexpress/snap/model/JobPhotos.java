package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class JobPhotos {

    @NotNull(message = "Please enter the links")
    private final String links;

    @NotNull(message = "Please enter the jobId")
    private final int jobId;

    public JobPhotos(@JsonProperty("links") String links,
                     @JsonProperty("jobId") int jobId) {
        this.links = links;
        this.jobId = jobId;
    }

    public String getLinks() {
        return links;
    }

    public int getJobId() {
        return jobId;
    }
}
