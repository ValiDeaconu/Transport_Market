package org.transexpress.snap.dal;

import org.transexpress.snap.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletDal {
    int insertWallet(Wallet wallet);
    List<Wallet> selectAllWallets();
    Optional<Wallet> selectWalletByID(int id);
    int deleteWalletByID(int id);
    int updateWallet(int id, Wallet wallet);
}
