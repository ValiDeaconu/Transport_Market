package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.model.UserReviews;
import org.transexpress.snap.service.MessageService;
import org.transexpress.snap.service.UserReviewsService;

import javax.validation.Valid;
import java.util.List;

public class UserReviewsController {
    private final UserReviewsService userReviewsService;

    @Autowired
    public UserReviewsController(UserReviewsService userReviewsService) {
        this.userReviewsService = userReviewsService;
    }

    @PostMapping
    public void addUserReview(@Valid @NonNull @RequestBody UserReviews userReviews) {
        userReviewsService.insertUserReview(userReviews);
    }

    @GetMapping(path = "{id}")
    public List<UserReviews> getUserReviewsByID(@PathVariable("id") int id) {
        return userReviewsService.getAllUserReviews(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserReviewByID(@PathVariable("id") int id) {
        userReviewsService.removeUserReview(id);
    }

    @PutMapping(path = "{id}")
    public void updateUserReviewByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody UserReviews userReview) {
        userReviewsService.updateUserReview(id, userReview);
    }
}
