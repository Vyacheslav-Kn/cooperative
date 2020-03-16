package by.bsuir.document.model.template;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Vladislav Novitskiy
 */
@Data
@Document(collection = "composite_tag")
public class CompositeTag {
    @Id
    @MongoId(value = FieldType.STRING)
    private String id;

    @Field
    @NotNull
    private EntityType type;

    @Field
    @NotNull
    private String name;

    @Field
    @NotEmpty
    private List<Tag> tags;
}
