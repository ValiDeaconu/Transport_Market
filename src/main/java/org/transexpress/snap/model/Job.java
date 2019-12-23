package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Job {
    private final int id;
    @NotEmpty(message = "Please enter description")
    private final String description;
    @NotBlank(message = "Please enter price")
    private final int price;
    @NotEmpty(message = "Please enter route")
    private final String route;
    @NotEmpty(message = "Please enter tags")
    private final String tags;
    @NotEmpty(message = "Please enter postDate")
    private final String postDate;
    @NotEmpty(message = "Please enter departureDate")
    private final String departureDate;
    @NotEmpty(message = "Please enter arrivalDate")
    private final String arrivalDate;
    @NotBlank(message = "Please enter sale")
    private final int sale;
    @NotBlank(message = "Please insert ownerId")
    private final int ownerId;


    public Job(@JsonProperty("id")int id,
               @JsonProperty("description") String description,
               @JsonProperty("price") int price,
               @JsonProperty("route") String route,
               @JsonProperty("tags") String tags,
               @JsonProperty("postDate") String postDate,
               @JsonProperty("departureDate") String departureDate,
               @JsonProperty("arrivalDate") String arrivalDate,
               @JsonProperty("sale") int sale,
               @JsonProperty("ownerId") int ownerId) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.route = route;
        this.tags = tags;
        this.postDate = postDate;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.sale = sale;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getRoute() {
        return route;
    }

    public String getTags() {
        return tags;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public int getSale() {
        return sale;
    }

    public int getOwnerId() { return ownerId; }
}
