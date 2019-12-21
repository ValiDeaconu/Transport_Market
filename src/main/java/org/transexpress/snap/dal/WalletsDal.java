package org.transexpress.snap.dal;

import org.transexpress.snap.model.Wallets;

import java.util.List;
import java.util.Optional;

public interface WalletsDal {

    int insertWallets(Wallets wallet);
    List<Wallets> selectAllWallets();
    Optional<Wallets> selectWalletByID(int id);
    int deleteWalletByID(int id);
    int updateWallet(int id, Wallets wallet);

}
