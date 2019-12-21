package org.transexpress.snap.dal;

import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.JobPhotos;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobPhotosDataAccessService implements JobPhotosDal {
    @Override
    public int insertJobPhotos(JobPhotos jobPhotos) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `job_photos` (link, jobId) " +
                    "VALUES ('" + jobPhotos.getLink() + "', '" + jobPhotos.getJobId() + "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int deleteJobPhotos(int id) {
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

    @Override
    public List<JobPhotos> selectAllJobPhotos() {
        List<JobPhotos> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM job_photos;");
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String link = rst.getString("link");
                int jobId = rst.getInt("jobId");

                result.add(new JobPhotos(id, link, jobId));
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
