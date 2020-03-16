package by.bsuir.rest.document.facade.impl;

import by.bsuir.document.model.document.Agreement;
import by.bsuir.document.model.document.Document;
import by.bsuir.document.model.template.Tag;
import by.bsuir.document.model.template.Template;
import by.bsuir.document.service.document.AgreementManager;
import by.bsuir.document.service.document.DocumentManager;
import by.bsuir.document.service.document.PhysicalDocumentService;
import by.bsuir.document.service.template.TemplateManager;
import by.bsuir.registry.model.VicariousAuthority;
import by.bsuir.registry.service.VicariousAuthorityManager;
import by.bsuir.rest.document.facade.DocumentFacade;
import by.bsuir.rest.document.mapper.AgreementMapper;
import by.bsuir.rest.document.mapper.DocumentMapper;
import by.bsuir.rest.document.model.AgreementEntity;
import by.bsuir.rest.document.model.DocumentEntity;
import by.bsuir.rest.document.populator.impl.AgreementTagValuesPopulator;
import by.bsuir.rest.document.populator.impl.VicariousAuthorityTagValuesPopulator;
import by.bsuir.rest.registry.mapper.VicariousAuthorityEntityMapper;
import by.bsuir.rest.registry.model.VicariousAuthorityEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.bsuir.document.model.template.SpecialTemplate.AGREEMENT;
import static by.bsuir.document.model.template.SpecialTemplate.VICARIOUS_AUTHORITY;

/**
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class DocumentFacadeImpl implements DocumentFacade {
    private PhysicalDocumentService physicalDocumentService;
    private TemplateManager templateManager;
    private DocumentManager documentManager;
    private VicariousAuthorityManager vicariousAuthorityManager;
    private VicariousAuthorityEntityMapper vicariousAuthorityEntityMapper;
    private VicariousAuthorityTagValuesPopulator vicariousAuthorityTagValuesPopulator;
    private AgreementManager agreementManager;
    private AgreementMapper agreementMapper;
    private AgreementTagValuesPopulator agreementTagValuesPopulator;
    private DocumentMapper documentMapper;

    @Override
    public void generateAgreement(String documentName, AgreementEntity data, String outputPath) {
        Map<Tag.Param, String> tagValues = new HashMap<>();
        Agreement saved = agreementManager.register(agreementMapper.fromDto(data));
        agreementTagValuesPopulator.populate(agreementMapper.toDto(saved), tagValues);
        generateDocument(documentName, AGREEMENT, tagValues, outputPath);
    }

    @Override
    public void generateAttorney(String documentName, VicariousAuthorityEntity data, String outputPath) {
        Map<Tag.Param, String> tagValues = new HashMap<>();
        VicariousAuthority saved = vicariousAuthorityManager.register(vicariousAuthorityEntityMapper.fromDto(data));
        vicariousAuthorityTagValuesPopulator.populate(vicariousAuthorityEntityMapper.toDto(saved), tagValues);
        generateDocument(documentName, VICARIOUS_AUTHORITY, tagValues, outputPath);
    }

    @Override
    public void generateDocument(String documentName, String templateId, Map<Tag.Param, String> paramValues,
                                 String outputPath) {
        Template template = templateManager.findOrThrow(templateId);
        physicalDocumentService.generate(template, paramValues, outputPath);

        Document document = new Document();
        document.setName(documentName);
        document.setPath(outputPath);
        document.setTemplateId(template.getId());

        documentManager.register(document);
    }

    @Override
    public List<DocumentEntity> findAll(int page, int limit) {
        return documentManager.findAll(page, limit)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentEntity> findAll() {
        return documentManager.findAll()
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentEntity> findByTemplateId(String id, int page, int limit) {
        return documentManager.find(document -> id.equals(document.getId()), page, limit)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentEntity> findByTemplateId(String id) {
        return documentManager.find(document -> id.equals(document.getId()))
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void open(String id) {
        Document document = documentManager.findOrThrow(id);
        physicalDocumentService.open(document.getPath());
    }
}
