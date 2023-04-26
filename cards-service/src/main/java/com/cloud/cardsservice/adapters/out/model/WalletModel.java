package com.cloud.cardsservice.adapters.out.model;

import com.cloud.cardsservice.domain.CardDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "_fastPayment_wallet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletModel {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String user;
    private String createdOn;
    private List<String> cards;
}
