package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.UserReviewDal;
import org.transexpress.snap.model.UserReview;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserReviewService {
    private final UserReviewDal userReviewDal;

    public UserReviewService(@Qualifier("mysql_user_reviews") UserReviewDal userReviewDal) {
        this.userReviewDal = userReviewDal;
    }

    public int insertUserReview(UserReview userReview){
        return userReviewDal.insertUserReview(userReview);
    }

    public int updateUserReview(int id, UserReview userReview){
        return userReviewDal.updateUserReviewByID(id,userReview);
    }

    public int removeUserReview(int id ){
        return userReviewDal.deleteUserReview(id);
    }

    public List<UserReview> getAllUserReviews(int userId){
        List<UserReview> allUserReviews= userReviewDal.selectAllUserReviews();

        List<UserReview> result = allUserReviews.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
        return result;
    }
}
