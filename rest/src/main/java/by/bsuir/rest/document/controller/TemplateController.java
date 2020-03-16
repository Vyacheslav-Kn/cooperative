package by.bsuir.rest.document.controller;

import by.bsuir.document.model.template.Template;
import by.bsuir.document.service.template.TemplateManager;
import by.bsuir.rest.common.controller.CrudController;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.document.model.TemplateEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to work with {@link Template} entities.
 *
 * @author Vladislav Novitskiy
 */
@Api
@RestController
@RequestMapping("/api/template")
public class TemplateController extends CrudController<TemplateManager, TemplateEntity, Template> {
    protected TemplateController(
            TemplateManager baseManager,
            EntityMapper<TemplateEntity, Template> entityMapper) {
        super(baseManager, entityMapper);
    }
}
