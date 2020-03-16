package by.bsuir.rest.registry.controller;

import by.bsuir.registry.model.Communication;
import by.bsuir.registry.service.CommunicationManager;
import by.bsuir.rest.common.mapper.EntityMapper;
import by.bsuir.rest.common.IDValidationGroup;
import by.bsuir.rest.registry.model.CommunicationEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/api/communication")
@RestController
public class CommunicationController extends BaseController<CommunicationManager, CommunicationEntity, Communication> {

    public CommunicationController(CommunicationManager baseManager, EntityMapper<CommunicationEntity, Communication> entityMapper) {
        super(baseManager, entityMapper);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@RequestBody @Validated(value = IDValidationGroup.class) @Valid CommunicationEntity communicationEntity) {
        baseManager.delete(entityMapper.fromDto(communicationEntity));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/person/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<CommunicationEntity>> getByPersonId(@PathVariable("id") String personID) {
        return ResponseEntity.ok(baseManager.findByPersonId(personID).stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "/person",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Collection<CommunicationEntity>> getByPersonIds(@RequestBody Collection<String> personIDs) {
        return ResponseEntity.ok(baseManager.findByPersonId(personIDs).stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }

}
