package org.transexpress.snap.dal;

import org.transexpress.snap.model.UserReviews;

import java.util.List;

public interface UserReviewsDal {
     int insertUserReview(UserReviews userReviews);
     int deleteUserReview(int id);
     int updateUserReviewByID(int id, UserReviews userReviews);
    List<UserReviews> selectAllUserReviews();

}
