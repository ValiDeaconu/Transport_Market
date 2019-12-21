package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.transexpress.snap.dal.UserReviewsDataAccessService;
import org.transexpress.snap.dal.UserReviewsDal;
import org.transexpress.snap.model.UserReviews;
import org.transexpress.snap.model.Wallets;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserReviewsService {
    private final UserReviewsDal userReviewsDal;

    public UserReviewsService(@Qualifier("mysql_user_reviews") UserReviewsDal userReviewsDal) {
        this.userReviewsDal = userReviewsDal;
    }

    public int insertUserReview(UserReviews userReview){
        return userReviewsDal.insertUserReview(userReview);
    }

    public int updateUserReview(int id, UserReviews userReview){
        return userReviewsDal.updateUserReviewByID(id,userReview);
    }

    public int removeUserReview(int id ){
        return userReviewsDal.deleteUserReview(id);
    }

    public List<UserReviews> getAllUserReviews(int userId){
        List<UserReviews> allUserReviews= userReviewsDal.selectAllUserReviews();

        List<UserReviews> result = allUserReviews.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
        return result;
    }
}
