package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.DATE_PARAM;

/**
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class DateProcessor implements TagProcessor {
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        long epoch = Long.parseLong(paramValues.get(DATE_PARAM));
        LocalDate date = LocalDate.ofEpochDay(epoch);
        return dateTimeFormatter.format(date);
    }
}
