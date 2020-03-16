package by.bsuir.rest.registry.model;

import by.bsuir.rest.common.IDValidationGroup;
import by.bsuir.rest.registry.controller.CommunicationController;
import by.bsuir.rest.registry.controller.VicariousAuthorityController;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class PersonEntity {

    @Null
    @NotBlank(groups = IDValidationGroup.class)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String lastName;
    @NotBlank
    private String documentType;
    private PassportData passportData;
    private String livingAddress;

    public String getCommunications() {
        // HateOas isn't work with swagger......
        return CommunicationController.class.getAnnotation(RequestMapping.class).value()[0] + "/person/" + id;
    }

    public String getIssuedVicariousAuthority() {
        // HateOas isn't work with swagger......
        return VicariousAuthorityController.class.getAnnotation(RequestMapping.class).value()[0] + "/proprietor/" + id;
    }

    public String getReceivedVicariousAuthority() {
        // HateOas isn't work with swagger......
        return VicariousAuthorityController.class.getAnnotation(RequestMapping.class).value()[0] + "/person/" + id;
    }

    @Data
    public static class PassportData {

        @NotBlank
        private String number;
        @NotBlank
        private long data;
        @NotBlank
        private String issuingAuthority;
        @NotBlank
        private String personalNumber;
    }
}
