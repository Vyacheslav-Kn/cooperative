package by.bsuir.core.service.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.CrudManager;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Base implementation of {@link CrudManager}.
 *
 * @author Vladislav Novitskiy
 */
@ParametersAreNonnullByDefault
public abstract class CrudManagerImpl<Y extends MongoRepository<T, String>, T> extends BaseManagerImpl<Y, T>
        implements CrudManager<T> {

    public CrudManagerImpl(Y mongoRepository) {
        super(mongoRepository);
    }

    @Override
    public void delete(String id) throws DataManipulateException {
        if (mongoRepository.existsById(id)) {
            mongoRepository.deleteById(id);
        } else {
            throw new DataManipulateException();
        }
    }

    @Override
    public T update(T t) throws DataManipulateException {
        if (mongoRepository.findOne(Example.of(t)).isPresent()) {
            return mongoRepository.save(t);
        } else {
            throw new DataManipulateException();
        }
    }
}
