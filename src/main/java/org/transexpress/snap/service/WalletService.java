package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.WalletDal;
import org.transexpress.snap.model.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletService {
    public final WalletDal walletDal;

    public WalletService(@Qualifier("mysql_wallets") WalletDal walletDal) { this.walletDal = walletDal;}

    public int addWallet(Wallet wallet){
        return walletDal.insertWallet(wallet);
    }

    public Optional<Wallet> getWalletByID(int id){
        return walletDal.selectWalletByID(id);
    }

    public int removeWallet(int id){
        return walletDal.deleteWalletByID(id);
    }

    public int updateWalletByID(int id, Wallet wallet){return walletDal.updateWallet(id, wallet);}

    public List<Wallet> getAllWalletsForUserId(int userId){
        List<Wallet> allWallets= walletDal.selectAllWallets();

        List<Wallet> result = allWallets.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
        return result;
    }
}
