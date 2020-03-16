package by.bsuir.rest.document.model;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.model.template.EntityType;
import by.bsuir.rest.common.IDValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author Vladislav Novitskiy
 */
@Data
public class TemplateEntity {
    @Null
    @NotBlank(groups = IDValidationGroup.class)
    private String id;

    @NotNull
    private EntityType type;

    @NotBlank
    private String name;

    @NotNull
    private List<Tag> tags;

    @NotNull
    private List<String> compositeTags;

    @NotBlank
    private String path;
}
