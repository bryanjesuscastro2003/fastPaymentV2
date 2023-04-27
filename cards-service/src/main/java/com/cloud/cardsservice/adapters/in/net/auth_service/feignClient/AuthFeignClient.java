package com.cloud.cardsservice.adapters.in.net.auth_service.feignClient;


import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.EncryptDataCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.in.ValidateUserCommand;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.EncryptDataCommandResNet;
import com.cloud.cardsservice.adapters.in.net.auth_service.dto.out.ValidateUserCommandResNet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service")
public interface AuthFeignClient {
    @PostMapping("/portA1/encryptData")
    EncryptDataCommandResNet encryptData(
            @RequestBody EncryptDataCommand request
    );
    @PostMapping("/portA1/validateUser")
    ValidateUserCommandResNet validateUser(
            @RequestBody ValidateUserCommand request
    );
}
