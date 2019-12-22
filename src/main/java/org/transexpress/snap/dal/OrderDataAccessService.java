package org.transexpress.snap.dal;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql_orders")
public class OrderDataAccessService implements OrderDal {

    @Override
    public int insertOrder(Order order) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `orders` (userId, jobId) " +
                    "VALUES ('" + order.getUserId() + "', '" + order.getJobId() +  "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public List<Order> selectAllOrders() {
        List<Order> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM orders;");
            ResultSet rst = ps.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();

            while (rst.next()) {
                int id = rst.getInt("id");
                int userId = rst.getInt("userId");
                int jobId = rst.getInt("jobId");

                result.add(new Order(id,
                        userId,
                        jobId));
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
    public Optional<Order> selectOrderByID(int id) {
        Connection handle = DatabaseManager.connect();
        Optional<Order> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM orders WHERE id = " + id + ";");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                int userId = rst.getInt("userId");
                int jobId = rst.getInt("jobId");


                result = Optional.of(new Order(id,
                        userId,
                        jobId));
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
    public int deleteOrderByID(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM `orders` WHERE `id` = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateOrderByID(int id, Order order) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE orders SET" +
                    " userId = '" + order.getUserId() + "'" +
                    ", jobId = '" + order.getJobId() + "'" +
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
