package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.model.Person;
import by.bsuir.registry.service.PersonManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.PERSON_ID;

/**
 * {@link TagProcessor} of {@link Tag#PD}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class PdProcessor implements TagProcessor {
    private PersonManager personManager;
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        String ownerId = paramValues.get(PERSON_ID);
        Person person = personManager.findOrThrow(ownerId);
        Person.PassportData passportData = person.getPassportData();
        return LocalDate.ofEpochDay(passportData.getData()).format(dateTimeFormatter);
    }
}
