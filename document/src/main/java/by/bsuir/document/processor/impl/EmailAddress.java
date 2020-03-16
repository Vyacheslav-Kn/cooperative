package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.reguisites.model.OrganizationEntity;
import by.bsuir.reguisites.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#ORG_EMAIL}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class EmailAddress implements TagProcessor {
    private OrganizationService organizationService;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        OrganizationEntity organization = organizationService.getLast();
        return organization.getEmail();
    }
}
