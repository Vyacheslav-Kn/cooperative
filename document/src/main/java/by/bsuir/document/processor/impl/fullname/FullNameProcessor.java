package by.bsuir.document.processor.impl.fullname;

import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.model.Person;
import by.bsuir.registry.service.PersonManager;
import lombok.AllArgsConstructor;

/**
 * General functionality for {@link PersonManager#getFullName(Person)} processors.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
public abstract class FullNameProcessor implements TagProcessor {
    private PersonManager personManager;

    public Object processById(String personId) {
        Person person = personManager.findOrThrow(personId);
        return personManager.getFullName(person);
    }
}
