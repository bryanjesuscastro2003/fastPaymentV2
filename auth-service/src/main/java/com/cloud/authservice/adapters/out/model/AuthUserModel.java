package com.cloud.authservice.adapters.out.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Id;

@Document(collection = "_fastPaymentV3")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthUserModel {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String lastname;
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
    private String role;
}
