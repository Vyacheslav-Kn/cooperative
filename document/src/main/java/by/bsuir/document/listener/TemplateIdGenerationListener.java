package by.bsuir.document.listener;

import by.bsuir.document.model.template.Template;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Listener to generate unique {@link String} id for {@link Template} entities.
 * It is necessary because at one hand we need to import initial {@link Template}s
 * with specified {@link String} ids, but at other one we need to generate this ids for new custom templates.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class TemplateIdGenerationListener extends AbstractMongoEventListener<Template> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Template> event) {
        Template template = event.getSource();
        if (template.getId() == null) {
            template.setId(UUID.randomUUID().toString());
        }
    }
}
