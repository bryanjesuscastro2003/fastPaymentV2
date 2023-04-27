package com.cloud.authservice.adapters.in.controller.net;

import com.cloud.authservice.application.config.WebAdapter;
import com.cloud.authservice.application.port.in.commands.net.EncryptDataCommand;
import com.cloud.authservice.application.port.in.commands.net.ValidateUserCommand;
import com.cloud.authservice.application.port.in.ports.net.EncryptDataPort;
import com.cloud.authservice.application.port.in.ports.net.ValidateUserPort;
import com.cloud.authservice.application.port.out.commands.net.EncryptDataCommandResNet;
import com.cloud.authservice.application.port.out.commands.net.ValidateUserCommandResNet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/portA1")
@AllArgsConstructor
public class NetControllerPortA1 {

    private final EncryptDataPort encryptDataPort;
    private final ValidateUserPort validateUserPort;

    @PostMapping("/encryptData")
    public ResponseEntity<EncryptDataCommandResNet> encryptData(
            @RequestBody EncryptDataCommand request
    ){
         return ResponseEntity.ok(encryptDataPort.encryptData(request));
    }

    @PostMapping("/validateUser")
    public ResponseEntity<ValidateUserCommandResNet> validateUser(
            @RequestBody ValidateUserCommand request
    ){
        return ResponseEntity.ok(validateUserPort.validateUser(request));
    }


}
