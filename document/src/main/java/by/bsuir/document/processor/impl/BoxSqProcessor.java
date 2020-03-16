package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.model.Property;
import by.bsuir.registry.service.PropertyManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.PROPERTY_ID;

/**
 * {@link TagProcessor} of {@link Tag#BOX_SQ}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class BoxSqProcessor implements TagProcessor {
    private PropertyManager propertyManager;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        String propertyId = paramValues.get(PROPERTY_ID);
        Property property = propertyManager.findOrThrow(propertyId);
        return property.getSquare();
    }
}
