package org.transexpress.snap.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager singleton;

    private DatabaseManager() { }

    public static DatabaseManager getInstance(){
        if (singleton == null){
            singleton = new DatabaseManager();
        }
        return singleton;
    }

    private Connection initConnection() {
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

    private boolean stopConnection(Connection handle) {
        try {
            handle.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
            return false;
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            return false;
        }

        return true;
    }

    public static Connection connect() {
        DatabaseManager db = getInstance();
        return db.initConnection();
    }

    public static boolean close(Connection handle) {
        DatabaseManager db = getInstance();
        return db.stopConnection(handle);
    }


}
