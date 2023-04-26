package com.cloud.cardsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDomain {
   private String id;
   private String user;
   private String createdOn;
   private List<String> cards;
}
