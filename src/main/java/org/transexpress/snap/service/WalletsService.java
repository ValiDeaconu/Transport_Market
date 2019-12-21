package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.transexpress.snap.dal.WalletsDal;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.Wallets;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WalletsService {
    public final WalletsDal walletsDal;

    public WalletsService(@Qualifier("mysql_wallets") WalletsDal walletsDal) { this.walletsDal = walletsDal;}

    public int addWallet(Wallets wallet){
        return walletsDal.insertWallets(wallet);
    }

    public Optional<Wallets> getWalletByID(int id){
        return walletsDal.selectWalletByID(id);
    }

    public int removeWallet(int id){
        return walletsDal.deleteWalletByID(id);
    }

    public int updateWalletByID(int id, Wallets wallets){return walletsDal.updateWallet(id, wallets);}

    public List<Wallets> getAllWalletsForUserId(int userId){
        List<Wallets> allWallets= walletsDal.selectAllWallets();

        List<Wallets> result = allWallets.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
        return result;
    }
}
