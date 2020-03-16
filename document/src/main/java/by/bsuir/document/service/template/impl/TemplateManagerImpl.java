package by.bsuir.document.service.template.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.impl.CrudManagerImpl;
import by.bsuir.document.model.template.Template;
import by.bsuir.document.repository.TemplateRepository;
import by.bsuir.document.service.template.TemplateManager;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;

import static by.bsuir.document.model.template.EntityType.SPECIAL;

/**
 * Default implementation of {@link TemplateManager}.
 *
 * @author Vladislav Novitskiy
 */
@ParametersAreNonnullByDefault
@Service
public class TemplateManagerImpl extends CrudManagerImpl<TemplateRepository, Template> implements TemplateManager {
    public TemplateManagerImpl(TemplateRepository mongoRepository) {
        super(mongoRepository);
    }

    @Override
    public void delete(String id) throws DataManipulateException {
        Template template = super.findOrThrow(id);
        if (SPECIAL.equals(template.getType())) {
            throw new DataManipulateException("Given template (" + template + ") can not be deleted. " +
                    "No special template required.");
        }
        super.delete(id);
    }
}
