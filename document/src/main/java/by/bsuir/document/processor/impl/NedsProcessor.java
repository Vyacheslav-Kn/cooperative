package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.NEDS_PARAM;

/**
 * {@link TagProcessor} of {@link Tag#NEDS}.
 *
 * @author Vladislav Novitskiy
 */
public class NedsProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return paramValues.get(NEDS_PARAM);
    }
}
