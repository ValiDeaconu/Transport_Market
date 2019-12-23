package org.transexpress.snap.dal;


import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql_jobs")
public class JobDataAccessService implements JobDal {

    @Override
    public int insertJob(Job job) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `jobs` (description, price, route, tags, postDate, departureDate, arrivalDate, sale, ownerId) " +
                    "VALUES ('" + job.getDescription() + "', MD5('" + job.getPrice() + "'), '" + job.getRoute() + "', '" + job.getTags() + "', " +
                    "'" + job.getPostDate() + "', '" + job.getDepartureDate() + "', " + job.getArrivalDate() + ", " + job.getSale() + ", " + job.getOwnerId() + ");");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }
    @Override
    public List<Job> selectAllJobs() {
        List<Job> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM jobs;");
            ResultSet rst = ps.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();

            while (rst.next()) {
                int id = rst.getInt("id");
                String description = rst.getString("description");
                int price = rst.getInt("price");
                String route = rst.getString("route");
                String tags = rst.getString("tags");
                String postDate = rst.getString("postDate");
                String departureDate = rst.getString("departureDate");
                String arrivalDate = rst.getString("arrivalDate");
                int sale = rst.getInt("sale");
                int ownerId = rst.getInt("ownerId");

                result.add(new Job(id,
                        description,
                        price,
                        route,
                        tags,
                        postDate,
                        departureDate,
                        arrivalDate,
                        sale,
                        ownerId));
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }

        DatabaseManager.close(handle);

        return (result.size() == 0) ? null : result;
    }

    @Override
    public Optional<Job> selectJobByID(int id) {
        Connection handle = DatabaseManager.connect();
        Optional<Job> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT FROM jobs WHERE id = " + id + ";");
            ResultSet rst = ps.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();

            while (rst.next()) {
                String description = rst.getString("description");
                int price = rst.getInt("price");
                String route = rst.getString("route");
                String tags = rst.getString("tags");
                String postDate = rst.getString("postDate");
                String departureDate = rst.getString("departureDate");
                String arrivalDate = rst.getString("arrivalDate");
                int sale = rst.getInt("sale");
                int ownerId = rst.getInt("ownerId");

                result = Optional.of(new Job(id,
                        description,
                        price,
                        route,
                        tags,
                        postDate,
                        departureDate,
                        arrivalDate,
                        sale,
                        ownerId));
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
    public int deleteJobByID(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM jobs WHERE id = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateJobByID(int id, Job job) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE jobs SET" +
                    " description = '" + job.getDescription() + "'" +
                    ", price = " + job.getPrice() +
                    ", route = '" + job.getRoute() + "'" +
                    ", tags = '" + job.getTags() + "'" +
                    ", postDate = '" + job.getPostDate() + "'" +
                    ", departureDate = '" + job.getDepartureDate() + "'" +
                    ", arrivalDate = '" + job.getArrivalDate() + "'" +
                    ", sale = " + job.getSale() +
                    ", ownerId = " + job.getOwnerId() +
                    " WHERE id =" + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }
}
