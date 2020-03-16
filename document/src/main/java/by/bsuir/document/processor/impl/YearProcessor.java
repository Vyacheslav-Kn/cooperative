package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.DATE_PARAM;

/**
 * {@link TagProcessor} of {@link Tag#YEAR}.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class YearProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        long epoch = Long.parseLong(paramValues.get(DATE_PARAM));
        LocalDate date = LocalDate.ofEpochDay(epoch);
        return String.valueOf(date.getYear());
    }
}
