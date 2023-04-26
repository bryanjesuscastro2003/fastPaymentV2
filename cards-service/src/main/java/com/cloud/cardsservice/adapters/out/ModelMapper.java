package com.cloud.cardsservice.adapters.out;

import com.cloud.cardsservice.adapters.out.model.WalletModel;
import com.cloud.cardsservice.domain.WalletDomain;

public class ModelMapper {

    public static WalletModel domainToEntity(WalletDomain walletDomain){
         return WalletModel.builder()

                 .build();
    }

}
