package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.Wallets;
import org.transexpress.snap.service.OrderService;
import org.transexpress.snap.service.WalletsService;

import javax.validation.Valid;
import java.util.List;

public class WalletsController {
    private final WalletsService walletsService;

    @Autowired
    public WalletsController(WalletsService walletsService) {
        this.walletsService = walletsService;
    }

    @PostMapping
    public void addWallet(@Valid @NonNull @RequestBody Wallets wallets) {
        walletsService.addWallet(wallets);
    }


    @GetMapping(path = "{id}")
    public Wallets getOrderById(@PathVariable("id") int id) {
        return walletsService.getWalletByID(id).orElse(null);
    }


    @GetMapping(path = "{id}")
    public List<Wallets> getAllWalletsForUserID(@PathVariable("id") int id) {
        return walletsService.getAllWalletsForUserId(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteWalletsByID(@PathVariable("id") int id) {
        walletsService.removeWallet(id);
    }

    @PutMapping(path = "{id}")
    public void updateWalletsByID(@PathVariable("id") int id, @Valid @NonNull @RequestBody Wallets wallets) {
        walletsService.updateWalletByID(id, wallets);
    }
}
