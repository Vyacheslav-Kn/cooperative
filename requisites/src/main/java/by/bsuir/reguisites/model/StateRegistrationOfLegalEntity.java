package by.bsuir.reguisites.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
@Document(collection = "state_registration_of_legal_entity")
public class StateRegistrationOfLegalEntity {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field
    private String name;

    @Field
    private String pdfScanContent;

    @Field
    private String russianFullName;

    @Field
    private String belarusianFullName;

    @Field
    private String russianShortName;

    @Field
    private String belarusianShortName;

    // TODO: 15.03.2020
    //  - Remove short names
    //  - Rename full names to names
    //  - Add organization type (full and short)
    //  - Fix db
    //  - Fix back
    //  - Fix front
    //  - Create full name by organization full type and name
    //  - Create short name by organization short type and name

    @Field
    private Date registrationDate;

    @Field
    private String organName;

    @Field
    private Date givenDate;

    @Field
    private String payerAccNumber;
}
