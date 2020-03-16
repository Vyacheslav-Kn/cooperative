package by.bsuir.rest.common.controller;

import by.bsuir.core.service.CrudManager;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.registry.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Vladislav Novitskiy
 */
public abstract class CrudController<X extends CrudManager<T>, Y, T> extends BaseController<X, Y, T> {
    protected CrudController(X baseManager, EntityMapper<Y, T> entityMapper) {
        super(baseManager, entityMapper);
    }

    @DeleteMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        baseManager.delete(id);
        return ResponseEntity.ok().build();
    }
}
