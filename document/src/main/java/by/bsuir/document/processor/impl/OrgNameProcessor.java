package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.reguisites.model.StateRegistrationOfLegalEntity;
import by.bsuir.reguisites.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#ORG_NAME}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class OrgNameProcessor implements TagProcessor {
    private RegistrationService registrationService;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        StateRegistrationOfLegalEntity registration = registrationService.getLast();
        return registration.getRussianFullName();
    }
}
