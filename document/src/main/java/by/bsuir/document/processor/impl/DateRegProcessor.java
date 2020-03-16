package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#DATE_REG}.
 *
 * @author Vladislav Novitskiy
 */
public class DateRegProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        // TODO: 14.03.2020
        //  - Add Certificate of state registration to property
        //  - Add registration date attribute to Certificate
        //  - Fix frontend
        //  - Then implements this
        throw new UnsupportedOperationException();
    }
}
