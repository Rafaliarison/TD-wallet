package com.wallet.tdwallet.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction {
    private int transactionId;
    private String transactionLabel;
    private int transactionAmount;
    private String transactionType;
    private LocalDateTime transactionDate;
}
