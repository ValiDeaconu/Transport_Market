package org.transexpress.snap.dal;

import org.springframework.stereotype.Repository;
import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.JobPhoto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("mysql_job_photos")
public class JobPhotoDataAccessService implements JobPhotoDal {
    @Override
    public int insertJobPhoto(JobPhoto jobPhoto) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `job_photos` (link, jobId) " +
                    "VALUES ('" + jobPhoto.getLink() + "', '" + jobPhoto.getJobId() + "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int deleteJobPhoto(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM `job_photos` WHERE `id` = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    private int getLastId(){
        List<Integer> result = new ArrayList<>();
        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM jobs;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                result.add(id);
            }

            ps.close();
            rst.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }

        DatabaseManager.close(handle);
        System.out.println(result.get(result.size() - 1));

        return (result.size() == 0) ? 0 : result.get(result.size() - 1);

    }

    @Override
    public List<JobPhoto> selectAllJobPhotos() {
        List<JobPhoto> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM job_photos;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String link = rst.getString("link");
                int jobId = rst.getInt("jobId");

                result.add(new JobPhoto(id, link, jobId));
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
