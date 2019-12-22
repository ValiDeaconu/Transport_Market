package org.transexpress.snap.dal;

import org.transexpress.snap.model.UserReview;

import java.util.List;

public interface UserReviewDal {
     int insertUserReview(UserReview userReview);
     int deleteUserReview(int id);
     int updateUserReviewByID(int id, UserReview userReview);
    List<UserReview> selectAllUserReviews();

}
