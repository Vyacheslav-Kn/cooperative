package by.bsuir.rest.document.controller;

import by.bsuir.document.model.template.CompositeTag;
import by.bsuir.document.service.tag.CompositeTagManager;
import by.bsuir.rest.common.controller.CrudController;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.document.model.CompositeTagEntity;
import by.bsuir.rest.registry.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to work with {@link CompositeTag} entities.
 *
 * @author Vladislav Novitskiy
 */
@Api
@RestController
@RequestMapping("/api/comptag")
public class CompositeTagController extends CrudController<CompositeTagManager, CompositeTagEntity, CompositeTag> {

    protected CompositeTagController(CompositeTagManager baseManager,
                                     EntityMapper<CompositeTagEntity, CompositeTag> entityMapper) {
        super(baseManager, entityMapper);
    }
}
