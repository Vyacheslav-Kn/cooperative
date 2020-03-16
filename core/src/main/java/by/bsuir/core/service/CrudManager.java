package by.bsuir.core.service;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Added supporting delete method.
 *
 * @author Vladislav Novitskiy
 */
@ParametersAreNonnullByDefault
public interface CrudManager<T> extends BaseManager<T> {
    void delete(String id);
}
