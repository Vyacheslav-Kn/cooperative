package by.bsuir.document.processor.impl.id;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.ID;

/**
 * General implementation for {@link Tag.Param#ID} processors.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class IdProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return paramValues.get(ID);
    }
}
