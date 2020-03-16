package by.bsuir.rest.document.model;

import by.bsuir.rest.common.IDValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author Vladislav Novitskiy
 */
@Data
public class AgreementEntity {
    @NotBlank(groups = IDValidationGroup.class)
    @Null
    private String id;

    @NotBlank
    private String index;

    @NotBlank
    private String proprietorId;

    @NotBlank
    private String propertyId;
}
