package com.cloud.authservice.adapters.out.persistence.net;

import com.cloud.authservice.adapters.out.model.AuthUserModel;
import com.cloud.authservice.adapters.out.repository.AuthUserRepository;
import com.cloud.authservice.application.config.PersistenceAdapter;
import com.cloud.authservice.application.port.in.commands.net.EncryptDataCommand;
import com.cloud.authservice.application.port.in.commands.net.ValidateUserCommand;
import com.cloud.authservice.application.port.out.LoadNetData;
import com.cloud.authservice.application.port.out.commands.net.EncryptDataCommandResNet;
import com.cloud.authservice.application.port.out.commands.net.ValidateUserCommandResNet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@PersistenceAdapter
public class NetPersistenceAdapter implements LoadNetData {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EncryptDataCommandResNet encryptData(EncryptDataCommand encryptDataCommand) {
        try{
            Map<String, String> outData = new HashMap<>();
            encryptDataCommand.getData().forEach((k,v) ->
                        outData.put(k,passwordEncoder.encode(v))
                    );
            return EncryptDataCommandResNet.builder()
                    .ok(true)
                    .message("Data encrypted ok")
                    .data(outData)
                    .build();
        }catch (Exception e){
            return EncryptDataCommandResNet.builder()
                    .ok(false)
                    .message("Unexpected error try again later")
                    .data(null)
                    .build();
        }
    }

    @Override
    public ValidateUserCommandResNet validateUser(ValidateUserCommand validateUserCommand) {
        try{
            Optional<AuthUserModel> isUser = authUserRepository.findByUsername(validateUserCommand.getUser());
            if(isUser.isEmpty())
                 throw new Exception("Unhealthy user");
            return ValidateUserCommandResNet.builder()
                    .ok(true)
                    .message("Healthy user")
                    .build();
        }catch (Exception e){
            return ValidateUserCommandResNet.builder()
                    .ok(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}
