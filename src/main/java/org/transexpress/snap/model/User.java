package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private final int id;
    @NotEmpty(message = "Please enter username")
    private final String username;
    @NotEmpty(message = "Please enter password")
    private final String password;
    @NotEmpty(message = "Please enter phone")
    private final String phone;
    @NotEmpty(message = "Please enter email")
    private final String email;
    @NotEmpty(message = "Please enter description")
    private final String description;
    @NotEmpty(message = "Please enter profile picture link")
    private final String profile_picture_link;
    @NotNull(message = "Please enter provider status")
    private final boolean isProvider;
    @NotNull(message = "Please enter admin status")
    private final boolean isAdmin;

    public User(@JsonProperty("id") int id,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("phone") String phone,
                @JsonProperty("email") String email,
                @JsonProperty("description") String description,
                @JsonProperty("profile_picture_link") String profile_picture_link,
                @JsonProperty("isProvider") boolean isProvider,
                @JsonProperty("isAdmin") boolean isAdmin
                ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.profile_picture_link = profile_picture_link;
        this.isProvider = isProvider;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getProfilePictureLink() {
        return profile_picture_link;
    }

    public boolean isProvider() {
        return isProvider;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
