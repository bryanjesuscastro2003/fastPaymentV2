package com.cloud.cardsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDomain {
    private String id;
    private String tuitionCard;
    private String nipCard;
    private double mount;
    private String createdOn;
    private String description;
    private String WalledId;
}
