package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.model.Wallet;
import org.transexpress.snap.service.WalletService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/wallet")
@RestController
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public void addWallet(@Valid @NonNull @RequestBody Wallet wallet) {
        walletService.addWallet(wallet);
    }

    @GetMapping(path = "{id}")
    public List<Wallet> getAllWalletsForUserID(@PathVariable("id") int id) {
        return walletService.getAllWalletsForUserId(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteWalletsByID(@PathVariable("id") int id) {
        walletService.removeWallet(id);
    }

    /*@PutMapping(path = "{id}")
    public void updateWalletsByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody Wallet wallet) {
        walletService.updateWalletByID(id, wallet);
    }*/

    @PutMapping(path = "extragere/")
    public ResponseMessage updateWithdraw( @RequestBody Wallet wallet){
        return walletService.updateWithdraw(wallet.getUserId(), wallet.getBalance());
    }

    @PutMapping(path = "depunere/{userId}")
    public ResponseMessage updateDeposit(@PathVariable("userId") int userId,  @RequestBody Wallet wallet){
        return walletService.updateDeposit(userId, wallet.getBalance());
    }

}
