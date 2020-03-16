package by.bsuir.rest.document.model;

import by.bsuir.rest.common.IDValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author Vladislav Novitskiy
 */
@Data
public class DocumentEntity {
    @NotBlank(groups = IDValidationGroup.class)
    @Null
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String path;

    @NotBlank
    private String templateId;
}
