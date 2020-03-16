package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.INDEX;

/**
 * {@link TagProcessor} of {@link Tag#INDEX}.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class IndexProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return paramValues.get(INDEX);
    }
}
