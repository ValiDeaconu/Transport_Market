package org.transexpress.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Wallet {
    private final int id;
    @NotNull(message = "Please enter the balance")
    private final int balance;
    @NotNull(message = "Please enter user id")
    private final int userId;


    public Wallet(@JsonProperty("id") int id,
                  @JsonProperty("balance") int balance,
                  @JsonProperty("userId") int userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public int getId(){return this.id;}
    public int getBalance(){return this.balance;}
    public int getUserId(){return this.balance;}
}
