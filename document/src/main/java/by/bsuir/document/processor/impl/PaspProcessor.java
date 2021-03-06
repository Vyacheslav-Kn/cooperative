package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.model.Person;
import by.bsuir.registry.service.PersonManager;
import lombok.AllArgsConstructor;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.PERSON_ID;

/**
 * {@link TagProcessor} of {@link Tag#PASP}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
public class PaspProcessor implements TagProcessor {
    private PersonManager personManager;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        String ownerId = paramValues.get(PERSON_ID);
        Person person = personManager.findOrThrow(ownerId);
        return person.getPassportData().getNumber();
    }
}
