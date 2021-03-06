package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.model.UserReview;
import org.transexpress.snap.service.UserReviewService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/reviews")
@RestController
public class UserReviewController {
    private final UserReviewService userReviewService;

    @Autowired
    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @PostMapping
    public void addUserReview(@Valid @NonNull @RequestBody UserReview userReview) {
        userReviewService.insertUserReview(userReview);
    }

    @GetMapping(path = "{id}")
    public List<UserReview> getUserReviewsForUserID(@PathVariable("id") int userId) {
        return userReviewService.getAllUserReviewsForUserId(userId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserReviewByID(@PathVariable("id") int id) {
        userReviewService.removeUserReview(id);
    }

    @PutMapping(path = "{id}")
    public void updateUserReviewByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody UserReview userReview) {
        userReviewService.updateUserReview(id, userReview);
    }
}
