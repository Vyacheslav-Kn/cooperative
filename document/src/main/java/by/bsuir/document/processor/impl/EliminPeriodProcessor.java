package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.service.tag.DateFormatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.ELIMIN_PERIOD_PARAM;

/**
 * {@link TagProcessor} of {@link Tag#ELIMIN_PERIOD}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class EliminPeriodProcessor implements TagProcessor {
    private DateFormatService dateFormatService;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        long eliminationPeriod = Long.parseLong(paramValues.get(ELIMIN_PERIOD_PARAM));
        return dateFormatService.formatDays(eliminationPeriod);
    }
}
