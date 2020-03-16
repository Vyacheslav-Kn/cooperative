package by.bsuir.registry.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "vicarious_authority")
public class VicariousAuthority {

    // TODO: 15.03.2020 Change FiledType to Integer
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field
    @NotNull// From
    private String proprietorId;
    @Field
    @NotNull // TimeUnit seconds since 1970....
    private long startDate;
    @Field
    @NotNull // TimeUnit days
    private long duration;
    @Field
    @NotNull// To
    private String personId;
}
