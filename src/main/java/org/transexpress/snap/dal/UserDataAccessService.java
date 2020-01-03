package org.transexpress.snap.dal;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.User;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Repository("mysql_users")
public class UserDataAccessService implements UserDal {


    @Override
    public int insertUser(User user) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `users` (username, password, phone, email, description, profile_picture_link, isProvider, isAdmin) " +
                    "VALUES ('" + user.getUsername() + "', MD5('" + user.getPassword() + "'), '" + user.getPhone() + "', '" + user.getEmail() + "', " +
                    "'" + user.getDescription() + "', '" + user.getProfilePictureLink() + "', " + user.isProvider() + ", " + user.isAdmin() + ");");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM users;");
            ResultSet rst = ps.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();

            while (rst.next()) {
                int id = rst.getInt("id");
                String username = rst.getString("username");
                String password = rst.getString("password");
                String phone = rst.getString("phone");
                String email = rst.getString("email");
                String description = rst.getString("description");
                String profile_picture_link = rst.getString("profile_picture_link");
                boolean isProvider = rst.getBoolean("isProvider");
                boolean isAdmin = rst.getBoolean("isAdmin");

                result.add(new User(id,
                        username,
                        password,
                        phone,
                        email,
                        description,
                        profile_picture_link,
                        isProvider,
                        isAdmin));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);

        return (result.size() == 0) ? null : result;
    }

    @Override
    public Optional<User> selectUserByID(int id) {
        Connection handle = DatabaseManager.connect();
        Optional<User> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM users WHERE id = " + id + ";");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                String username = rst.getString("username");
                String password = rst.getString("password");
                String phone = rst.getString("phone");
                String email = rst.getString("email");
                String description = rst.getString("description");
                String profile_picture_link = rst.getString("profile_picture_link");
                boolean isProvider = rst.getBoolean("isProvider");
                boolean isAdmin = rst.getBoolean("isAdmin");

                result = Optional.of(new User(id,
                        username,
                        password,
                        phone,
                        email,
                        description,
                        profile_picture_link,
                        isProvider,
                        isAdmin));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);

        return result.equals(Optional.empty()) ? Optional.empty() : result;
    }

    @Override
    public Optional<User> selectUserByName(String username) {
        Connection handle = DatabaseManager.connect();
        Optional<User> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement(" SELECT * FROM users WHERE username = '" + username + "' ; ");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                int id = rst.getInt("id");
                String password = rst.getString("password");
                String phone = rst.getString("phone");
                String email = rst.getString("email");
                String description = rst.getString("description");
                String profile_picture_link = rst.getString("profile_picture_link");
                boolean isProvider = rst.getBoolean("isProvider");
                boolean isAdmin = rst.getBoolean("isAdmin");

                result = Optional.of(new User(id,
                        username,
                        password,
                        phone,
                        email,
                        description,
                        profile_picture_link,
                        isProvider,
                        isAdmin));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);

        return result.equals(Optional.empty()) ? Optional.empty() : result;    }

    @Override
    public Optional<User> selectUserByUsernameAndPassword(String username, String password) {
        Connection handle = DatabaseManager.connect();
        Optional<User> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM users " +
                    "WHERE username = '" + username + "' " +
                    "AND password = MD5('" + password + "');");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                int id = rst.getInt("id");
                String dbusername = rst.getString("username");
                String md5password = rst.getString("password");
                String phone = rst.getString("phone");
                String email = rst.getString("email");
                String description = rst.getString("description");
                String profile_picture_link = rst.getString("profile_picture_link");
                boolean isProvider = rst.getBoolean("isProvider");
                boolean isAdmin = rst.getBoolean("isAdmin");

                result = Optional.of(new User(id,
                        dbusername,
                        md5password,
                        phone,
                        email,
                        description,
                        profile_picture_link,
                        isProvider,
                        isAdmin));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);

        return result.equals(Optional.empty()) ? Optional.empty() : result;
    }

    @Override
    public int deleteUserByID(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM `users` WHERE `id` = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int deleteUserByUsername(String username) {
        Connection handle = DatabaseManager.connect();
        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "' " +";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateUserByID(int id, User user) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE users SET" +
                    " username = '" + user.getUsername() + "'" +
                    ", password = '" + user.getPassword() + "'" +
                    ", phone = '" + user.getPhone() + "'" +
                    ", email = '" + user.getPhone() + "'" +
                    ", description = '" + user.getDescription() + "'" +
                    ", profile_picture_link = '" + user.getProfilePictureLink() + "'" +
                    ", isProvider = " + (user.isProvider() ? "TRUE" : "FALSE") +
                    ", isAdmin = " + (user.isAdmin() ? "TRUE" : "FALSE") +
                    " WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }
}
