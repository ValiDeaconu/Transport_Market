package org.transexpress.snap.dal;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.UserReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("mysql_user_reviews")
public class UserReviewDataAccessService implements UserReviewDal {

    @Override
    public int insertUserReview(UserReview userReview) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `user_reviews` (description, rate, userId) " +
                    "VALUES ('" + userReview.getDescription() + "', '" + userReview.getRate() + "', '" + userReview.getUserId() + "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int deleteUserReview(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM user_reviews WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateUserReviewByID(int id, UserReview userReview) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE user_reviews " +
                    "SET description = '" + userReview.getDescription() + "', " +
                    "rate = '" + userReview.getRate() + "', " +
                    "userId = " + userReview.getUserId() + " " +
                    "WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public List<UserReview> selectAllUserReviews() {
        List<UserReview> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM user_reviews;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String description = rst.getString("description");
                byte rate = rst.getByte("rate");
                int userId = rst.getInt("userId");
                result.add(new UserReview(id, description, rate, userId));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);

        return (result.size() == 0) ? null : result;
    }
}
