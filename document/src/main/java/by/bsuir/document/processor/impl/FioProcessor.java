package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#FIO}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class FioProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        // TODO: 12.03.2020
        //  - Add apartment number to property
        //  - Fix frontend
        //  - Then implements this
        throw new UnsupportedOperationException();
    }
}
