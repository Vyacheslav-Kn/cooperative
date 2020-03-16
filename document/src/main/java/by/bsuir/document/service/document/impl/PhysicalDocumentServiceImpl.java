package by.bsuir.document.service.document.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.document.lowlevel.DocumentOperations;
import by.bsuir.document.model.template.CompositeTag;
import by.bsuir.document.model.template.Tag;
import by.bsuir.document.model.template.Template;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.service.document.PhysicalDocumentService;
import by.bsuir.document.service.tag.CompositeTagManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link PhysicalDocumentService}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Service
public class PhysicalDocumentServiceImpl implements PhysicalDocumentService {
    private Map<Tag, TagProcessor> tagProcessors;
    private CompositeTagManager compositeTagManager;

    @Override
    public void generate(Template template, Map<Tag.Param, String> params, String outputPath) {
        Map<String, Object> tagValues = new HashMap<>();

        List<Tag> tags = template.getTags();
        for (Tag tag : tags) {
            TagProcessor processor = tagProcessors.get(tag);
            tagValues.put(tag.name(), processor.process(params));
        }

        List<String> compositeTags = template.getCompositeTags();
        for (String compositeTagId : compositeTags) {
            CompositeTag compositeTag = compositeTagManager.findOrThrow(compositeTagId);
            List<Tag> innerTags = compositeTag.getTags();
            Object compositeTagValue = innerTags.stream()
                    .map(innerTag -> {
                        String innerTagName = innerTag.name();
                        String innerTagValue = String.valueOf(tagValues.get(innerTagName));
                        if (innerTagValue == null) {
                            TagProcessor processor = tagProcessors.get(innerTag);
                            innerTagValue = String.valueOf(processor.process(params));
                        }
                        tagValues.put(innerTagName, innerTagValue);
                        return innerTagValue;
                    })
                    .reduce((res, cur) -> res + "\n" + cur).orElseThrow(DataManipulateException::new);
            tagValues.put(compositeTag.getId(), compositeTagValue);
        }

        DocumentOperations.generate(template.getPath(), outputPath, tagValues);
    }

    @Override
    public void open(String path) {
        DocumentOperations.open(path);
    }
}
