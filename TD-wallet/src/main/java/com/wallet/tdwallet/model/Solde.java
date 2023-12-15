package com.wallet.tdwallet.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Solde {
    private int soldeId;
    private double solde_amount;
    private LocalDateTime soldeDate;
}
