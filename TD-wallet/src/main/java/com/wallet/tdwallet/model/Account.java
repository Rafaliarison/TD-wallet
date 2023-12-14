package com.wallet.tdwallet.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    private int accountId;
    private String accountName;
    private String accountCurrency;
    private int accountSolde;
}
