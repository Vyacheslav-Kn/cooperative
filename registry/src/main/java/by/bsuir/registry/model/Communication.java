package by.bsuir.registry.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "communication")
@ParametersAreNonnullByDefault
@CompoundIndexes(
        @CompoundIndex(name = "communication_index", def = "{'personId' : 1, 'communicationType': 1, 'communicationValue': 1}", unique = true)
)
public class Communication {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field
    @NotNull
    @Indexed
    private String personId;

    // Enum?
    @Field
    @NotNull
    private String communicationType;

    @Field
    @NotNull
    private String communicationValue;
}
