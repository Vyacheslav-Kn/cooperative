package by.bsuir.rest.document.controller;

import by.bsuir.document.model.template.Tag;
import by.bsuir.rest.document.facade.DocumentFacade;
import by.bsuir.rest.document.model.AgreementEntity;
import by.bsuir.rest.document.model.DocumentEntity;
import by.bsuir.rest.registry.model.VicariousAuthorityEntity;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller to work with physical documents and its virtual representation
 * as {@link by.bsuir.document.model.document.Document} entities.
 *
 * @author Vladislav Novitskiy
 */
@Api
@AllArgsConstructor
@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private DocumentFacade documentFacade;

    /**
     * Method generates agreement doc.
     *
     * @param data Data for generation this type of document
     * @param name Name of generated agreement {@link by.bsuir.document.model.document.Document} in system
     * @param path Path to saving file
     * @return Void ok response
     */
    @PostMapping("/agreement")
    public ResponseEntity<Void> generateAgreement(@RequestBody @Valid AgreementEntity data,
                                                  @RequestParam String name, @RequestParam String path) {
        documentFacade.generateAgreement(name, data, path);
        return ResponseEntity.ok().build();
    }

    /**
     * Method generates attorney doc.
     *
     * @param data Data for generation this type of document
     * @param name Name of generated attorney {@link by.bsuir.document.model.document.Document} in system
     * @param path Path to saving file
     * @return Void ok response
     */
    @PostMapping("/attorney")
    public ResponseEntity<Void> generateAttorney(@RequestBody @Valid VicariousAuthorityEntity data,
                                                 @RequestParam String name, @RequestParam String path) {
        documentFacade.generateAttorney(name, data, path);
        return ResponseEntity.ok().build();
    }

    /**
     * Method generate custom doc.
     *
     * @param paramValues {@link Map} with params for tags and their values
     * @param path        Path to saving file
     * @param name        Name of generated custom {@link by.bsuir.document.model.document.Document} in system
     * @param templateId  Id of used template
     * @return Void ok response
     */
    @PostMapping("/custom")
    public ResponseEntity<Void> generateCustomDocument(@RequestBody Map<Tag.Param, String> paramValues,
                                                       @RequestParam String path, @RequestParam String name,
                                                       @RequestParam String templateId) {
        documentFacade.generateDocument(name, templateId, paramValues, path);
        return ResponseEntity.ok().build();
    }

    /**
     * Method find info about all documents in system.
     *
     * @return Response with {@link List} of documents
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentEntity>> getAll() {
        return ResponseEntity.ok(documentFacade.findAll());
    }

    /**
     * Method find info about some number documents from specified page.
     *
     * @param page  From page
     * @param limit Required number of documents at response
     * @return Response with {@link List} of documents
     */
    @GetMapping(
            path = "/pages/{page}/{limit}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<DocumentEntity>> get(@PathVariable("page") int page,
                                                    @PathVariable("limit") int limit) {
        return ResponseEntity.ok(documentFacade.findAll(page, limit));
    }

    /**
     * Method find info about all documents of template with specified id.
     *
     * @param id Identifier of documents template
     * @return Response with {@link List} of documents
     */
    @GetMapping(
            path = "/of/template/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<DocumentEntity>> getByTemplateId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(documentFacade.findByTemplateId(id));
    }

    /**
     * Method find info about some number documents of template with specified id from specified page.
     *
     * @param id    Identifier of documents template
     * @param page  From page
     * @param limit Required number of documents at response
     * @return Response with {@link List} of documents
     */
    @GetMapping(
            path = "/of/template/{id}/pages/{page}/{limit}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<DocumentEntity>> getByTemplateId(@PathVariable(name = "id") String id,
                                                                @PathVariable(name = "page") int page,
                                                                @PathVariable(name = "limit") int limit) {
        return ResponseEntity.ok(documentFacade.findByTemplateId(id, page, limit));
    }

    /**
     * Method open physical document in default program.
     *
     * @param id Identifier of document that contains path to physical document which should be opened
     * @return Void ok response
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> open(@PathVariable(name = "id") String id) {
        documentFacade.open(id);
        return ResponseEntity.ok().build();
    }
}
