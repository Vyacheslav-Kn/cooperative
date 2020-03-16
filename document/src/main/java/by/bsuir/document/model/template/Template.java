package by.bsuir.document.model.template;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Vladislav Novitskiy
 */
@Data
@Document(collection = "template")
public class Template {
    @Id
    @MongoId(value = FieldType.STRING, targetType = FieldType.STRING)
    private String id;

    @Field
    @NotNull
    private EntityType type;

    @Field
    @NotNull
    @Indexed(unique = true)
    private String name;

    @Field
    @NotNull
    private List<Tag> tags;

    @Field
    @NotNull
    private List<String> compositeTags;

    @Field
    @NotNull
    @Indexed(unique = true)
    private String path;
}
