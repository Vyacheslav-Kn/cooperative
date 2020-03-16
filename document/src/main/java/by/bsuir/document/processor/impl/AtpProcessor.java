package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.service.tag.DateFormatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.DURATION;
import static by.bsuir.document.model.template.Tag.Param.START_DATE;

/**
 * {@link TagProcessor} of {@link Tag#ATP}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class AtpProcessor implements TagProcessor {
    private DateFormatService dateFormatService;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        long startDate = Long.parseLong(paramValues.get(START_DATE));
        long duration = Long.parseLong(paramValues.get(DURATION));
        return dateFormatService.formatDelay(startDate, duration);
    }
}
