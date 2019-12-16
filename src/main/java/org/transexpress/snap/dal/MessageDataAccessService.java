package org.transexpress.snap.dal;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql_messages")
public class MessageDataAccessService implements MessageDal {

    @Override
    public int insertMessage(Message message) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `messages` (message, date, senderId, receiverId) " +
                    "VALUES ('" + message.getMessage() + "', '" + message.getDate() + "', '" + message.getSenderId() + "', '" + message.getReceiverId() + "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public Optional<Message> selectMessageByID(int id) {
        Connection handle = DatabaseManager.connect();
        Optional<Message> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM messages WHERE id = " + id + ";");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                String message = rst.getString("message");
                String date = rst.getString("date");
                int senderId = rst.getInt("senderId");
                int receiverId = rst.getInt("receiverId");

                result = Optional.of(new Message(id, message, date, senderId, receiverId));
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
    public List<Message> selectAllMessages() {
        List<Message> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM messages;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String message = rst.getString("message");
                String date = rst.getString("date");
                int senderId = rst.getInt("senderId");
                int receiverId = rst.getInt("receiverId");
                result.add(new Message(id, message, date, senderId, receiverId));
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
    public int deleteMessageByID(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM messages WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateMessageByID(int id, Message message) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE messages " +
                    "SET message = '" + message.getMessage() + "', " +
                    "date = '" + message.getDate() + "', " +
                    "senderId = " + message.getSenderId() + ", " +
                    "receiverId = " + message.getReceiverId() + " " +
                    "WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }
}
