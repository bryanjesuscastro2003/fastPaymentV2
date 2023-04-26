package com.cloud.cardsservice.adapters.out.model;

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

@Document(collection = "_fastPayment_card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardModel {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String tuitionCard;
    private String nipCard;
    private double mount;
    private String createdOn;
    private String description;
    private String walledId;

}
