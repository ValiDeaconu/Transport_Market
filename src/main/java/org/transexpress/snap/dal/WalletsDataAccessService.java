package org.transexpress.snap.dal;

import org.transexpress.snap.misc.DatabaseManager;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.Wallets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletsDataAccessService implements WalletsDal {

    @Override
    public int insertWallets(Wallets wallet) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("INSERT INTO `wallets` (balance, userId) " +
                    "VALUES ('" + wallet.getBalance() + "', '" + wallet.getUserId() +  "');");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public List<Wallets> selectAllWallets() {
        List<Wallets> result = new ArrayList<>();

        Connection handle = DatabaseManager.connect();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM wallets;");
            ResultSet rst = ps.executeQuery();
            ResultSetMetaData rsmd = rst.getMetaData();

            while (rst.next()) {
                int id = rst.getInt("id");
                int balance = rst.getInt("balance");
                int userId = rst.getInt("userId");

                result.add(new Wallets(id,
                        balance,
                        userId));
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
    public Optional<Wallets> selectWalletByID(int id) {
        Connection handle = DatabaseManager.connect();
        Optional<Wallets> result = Optional.empty();
        try {
            PreparedStatement ps = handle.prepareStatement("SELECT * FROM wallets WHERE id = " + id + ";");
            ResultSet rst = ps.executeQuery();

            if (rst.next()) {
                int balance = rst.getInt("balance");
                int userId = rst.getInt("userId");

                result = Optional.of(new Wallets(id,
                        balance,
                        userId));
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
    public int deleteWalletByID(int id) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("DELETE FROM `wallets` WHERE `id` = " + id + ";");

            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("Operation failed: " + e);
        }

        DatabaseManager.close(handle);

        return rowCount;
    }

    @Override
    public int updateWallet(int id, Wallets wallet) {
        Connection handle = DatabaseManager.connect();

        int rowCount = 0;
        try {
            Statement stmt = handle.createStatement();
            rowCount = stmt.executeUpdate("UPDATE wallets SET" +
                    " balance = '" + wallet.getBalance() + "'" +
                    ", userId = '" + wallet.getUserId() + "'" +
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
