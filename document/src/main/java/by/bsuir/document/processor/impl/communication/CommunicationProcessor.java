package by.bsuir.document.processor.impl.communication;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.registry.model.Communication;
import by.bsuir.registry.service.CommunicationManager;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.PROPERTY_ID;

/**
 * General functionality for {@link Communication} processors.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
public abstract class CommunicationProcessor implements TagProcessor {
    private CommunicationManager communicationManager;

    protected Object processByCommunicationType(Map<Tag.Param, String> paramValues, String communicationType) {
        String propertyId = paramValues.get(PROPERTY_ID);
        Collection<Communication> communications = communicationManager.findByPersonId(propertyId);
        return communications.stream()
                .filter(communication -> communicationType.equals(communication.getCommunicationType()))
                .findFirst()
                .orElseThrow(DataManipulateException::new);
    }
}
