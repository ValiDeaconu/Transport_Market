package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.WalletDal;
import org.transexpress.snap.misc.ResponseMessage;
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
        List<Wallet> wallets = walletDal.selectAllWallets();
        List<Wallet> allWalletsForUser = wallets.stream().
                filter(w -> w.getUserId() == userId).
                collect(Collectors.toList());
        return allWalletsForUser;
    }

    public ResponseMessage updateDeposit(int userId, int balance){
        List<Wallet> wallet = getAllWalletsForUserId(userId);
        Wallet walletToUpdate;
        int code = 0;
        if (wallet.size() == 0){
            walletToUpdate = new Wallet(0, balance, userId);
            code = addWallet(walletToUpdate);
            if (code == 1) return new ResponseMessage("Depunere reusita!", 0);
            else return new ResponseMessage("Database error", -1);
        }
        walletToUpdate = new Wallet(wallet.get(0).getId(), wallet.get(0).getBalance() + balance, userId);
        if (updateWalletByID(walletToUpdate.getId(), walletToUpdate) != 1)
            return new ResponseMessage("Database error", -1);
        else return new ResponseMessage("Depunere completa", 0);

    }

    public ResponseMessage updateWithdraw(int userId, int balance){
        List<Wallet> wallet = getAllWalletsForUserId(userId);
        if (wallet.size() == 0)
            return new ResponseMessage("Fonduri insuficiente.",-1);
        Wallet walletToUpdate = new Wallet(wallet.get(0).getId(),
                wallet.get(0).getBalance() - balance,
                userId);
        if (updateWalletByID(walletToUpdate.getId(), walletToUpdate) != 1)
            return new ResponseMessage("Database error", -1);
        else return new ResponseMessage("Extragere completa");

    }
}
