package by.bsuir.rest.registry.model;

import by.bsuir.rest.common.IDValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class CommunicationEntity {

    @Null
    @NotBlank(groups = IDValidationGroup.class)
    private String id;
    @NotBlank
    private String personId;
    @NotBlank
    private String communicationType;
    @NotBlank
    private String communicationValue;
}
