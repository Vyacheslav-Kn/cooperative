package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#ADDRESS}.
 *
 * @author Vladislav Novitskiy
 */
public class AddressProcessor implements TagProcessor {

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        // TODO: 14.03.2020
        //  - Add address attribute to person
        //  - Fix frontend
        //  - Then implements this
        throw new UnsupportedOperationException();
    }
}
