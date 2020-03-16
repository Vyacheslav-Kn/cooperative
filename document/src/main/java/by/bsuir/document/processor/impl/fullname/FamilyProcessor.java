package by.bsuir.document.processor.impl.fullname;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.service.PersonManager;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#FAMILY}.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class FamilyProcessor extends FullNameProcessor {
    public FamilyProcessor(PersonManager personManager) {
        super(personManager);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        String ownerId = paramValues.get(Tag.Param.PERSON_ID);
        return processById(ownerId);
    }
}
