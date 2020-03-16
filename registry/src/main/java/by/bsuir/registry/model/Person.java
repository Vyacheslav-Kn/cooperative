package by.bsuir.registry.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "person")
@CompoundIndexes(
        @CompoundIndex(name = "fio", def = "{'name' : 1, 'surname': 1, 'lastName': 1}", unique = true)
)
public class Person {

    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    @Field
    @NotNull
    private String name;
    @Field
    @NotNull
    private String surname;
    @Field
    @NotNull
    private String lastName;
    @Field
    @NotNull
    private String documentType;
    @Field
    private PassportData passportData;
    @Field
    private String livingAddress;

    // TODO: 12.03.2020
    //  - Fix frontend

    @Data
    public static class PassportData {

        @Field
        @NotNull
        private String number;
        @Field
        @NotNull
        private long data;
        @Field
        @NotNull
        private String issuingAuthority;
        @Field
        @NotNull
        private String personalNumber;
    }
}
