package com.cloud.cardsservice.adapters.in.net.auth_service.service;

import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.EncryptDataCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.ValidateUserCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.EncryptDataCommandResNet;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.ValidateUserCommandResNet;
import com.cloud.cardsservice.adapters.in.net.auth_service.feignClient.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceFeign {
    @Autowired
    private AuthFeignClient authFeignClient;

    public ValidateUserCommandResNet validateUser(ValidateUserCommand validateUserCommand){
        return authFeignClient.validateUser(validateUserCommand);
    }

    public EncryptDataCommandResNet encryptData(EncryptDataCommand encryptDataCommand){
        return authFeignClient.encryptData(encryptDataCommand);
    }

}
