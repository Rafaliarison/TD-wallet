package com.wallet.tdwallet.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    private int accountId;
    private String accountName;
    private String  accountType;
    private int accountCurrency;
    private double accountSolde;
    private List<Transaction> transactions;
}
