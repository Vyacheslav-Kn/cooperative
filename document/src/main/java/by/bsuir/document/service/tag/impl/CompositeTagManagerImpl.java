package by.bsuir.document.service.tag.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.impl.CrudManagerImpl;
import by.bsuir.document.model.template.CompositeTag;
import by.bsuir.document.repository.CompositeTagRepository;
import by.bsuir.document.service.tag.CompositeTagManager;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;

import static by.bsuir.document.model.template.EntityType.SPECIAL;

/**
 * Default implementation {@link CompositeTagManager}.
 *
 * @author Vladislav Novitskiy
 */
@ParametersAreNonnullByDefault
@Service
public class CompositeTagManagerImpl extends CrudManagerImpl<CompositeTagRepository, CompositeTag>
        implements CompositeTagManager {
    public CompositeTagManagerImpl(CompositeTagRepository mongoRepository) {
        super(mongoRepository);
    }

    @Override
    public void delete(String id) throws DataManipulateException {
        CompositeTag compositeTag = super.findOrThrow(id);
        if (SPECIAL.equals(compositeTag.getType())) {
            throw new DataManipulateException("Given composite tag (" + compositeTag + ") can not be deleted. " +
                    "No special composite tag required.");
        }
        super.delete(id);
    }
}
