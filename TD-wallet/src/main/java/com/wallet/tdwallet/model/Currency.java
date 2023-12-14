package com.wallet.tdwallet.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Currency {
    private int currencyId;
    private String curreencyName;
    private String currencyCode;
}
