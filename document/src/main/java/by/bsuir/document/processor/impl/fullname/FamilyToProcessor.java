package by.bsuir.document.processor.impl.fullname;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.service.PersonManager;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.TO_PERSON_ID;

/**
 * {@link TagProcessor} of {@link Tag#FAMILY_TO}.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class FamilyToProcessor extends FullNameProcessor {
    public FamilyToProcessor(PersonManager personManager) {
        super(personManager);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        String forPersonId = paramValues.get(TO_PERSON_ID);
        return processById(forPersonId);
    }
}
