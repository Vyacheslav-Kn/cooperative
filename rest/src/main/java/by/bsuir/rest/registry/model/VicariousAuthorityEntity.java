package by.bsuir.rest.registry.model;

import by.bsuir.rest.common.IDValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class VicariousAuthorityEntity {

    @NotBlank(groups = IDValidationGroup.class)
    @Null
    private String id;
    @ApiModelProperty(required = true, value = "Person which issued vicarious authority.")
    @NotBlank// From
    private String proprietorId;
    private long startDate;
    private long duration;
    @NotBlank // To
    private String personId;
}
