package org.transexpress.snap.dal;

import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.model.UserReviews;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserReviewsDataAccessService implements UserReviewsDal {

    @Override
    public int insertUserReview(UserReviews userReviews) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `user_reviews` (description, rate, userId) " +
                    "VALUES ('" + userReviews.getDescription() + "', '" + userReviews.getRate() + "', '" + userReviews.getUserId() + "');");

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
    public int updateUserReviewByID(int id, UserReviews userReviews) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE user_reviews " +
                    "SET description = '" + userReviews.getDescription() + "', " +
                    "rate = '" + userReviews.getRate() + "', " +
                    "userId = " + userReviews.getUserId() + " " +
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
    public List<UserReviews> selectAllUserReviews() {
        List<UserReviews> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM user_reviews;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String description = rst.getString("description");
                byte rate = rst.getByte("rate");
                int userId = rst.getInt("userId");
                result.add(new UserReviews(id, description, rate, userId));
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
