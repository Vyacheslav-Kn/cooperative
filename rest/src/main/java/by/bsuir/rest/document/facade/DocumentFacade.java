package by.bsuir.rest.document.facade;

import by.bsuir.document.model.template.Tag;
import by.bsuir.rest.document.model.AgreementEntity;
import by.bsuir.rest.document.model.DocumentEntity;
import by.bsuir.rest.registry.model.VicariousAuthorityEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Novitskiy
 */
public interface DocumentFacade {
    void generateAgreement(String documentName, AgreementEntity data, String outputPath);

    void generateAttorney(String documentName, VicariousAuthorityEntity data, String outputPath);

    void generateDocument(String documentName, String templateId, Map<Tag.Param, String> paramValues, String outputPath);

    List<DocumentEntity> findAll(int page, int limit);

    List<DocumentEntity> findAll();

    List<DocumentEntity> findByTemplateId(String id, int page, int limit);

    List<DocumentEntity> findByTemplateId(String id);

    void open(String id);
}
