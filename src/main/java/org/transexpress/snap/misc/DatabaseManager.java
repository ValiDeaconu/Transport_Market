package org.transexpress.snap.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection connect() {
        String dbHost = "remotemysql.com";
        String dbPort = "3306";
        String dbName = "gpltniDy4M";
        String dbUser = "gpltniDy4M";
        String dbPass = "0fxiXCFhSw";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e);
            return null;
        }

        Connection dbHandle = null;
        try {
            dbHandle = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false",
                    dbUser,
                    dbPass
            );
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
            return null;
        }

        return dbHandle;
    }

    public static boolean close(Connection handle) {
        try {
            handle.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
            return false;
        }

        return true;
    }


}
