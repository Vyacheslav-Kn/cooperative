package by.bsuir.rest.registry.controller;

import by.bsuir.core.service.BaseManager;
import by.bsuir.rest.common.IDValidationGroup;
import by.bsuir.rest.common.mapper.EntityMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseController<X extends BaseManager<T>, Y, T> {

    protected final X baseManager;
    protected final EntityMapper<Y, T> entityMapper;

    protected BaseController(X baseManager, EntityMapper<Y, T> entityMapper) {
        this.baseManager = baseManager;
        this.entityMapper = entityMapper;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Y>> get() {
        return ResponseEntity.ok(baseManager.findAll().stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Y> update(@RequestBody @Validated(value = IDValidationGroup.class) Y y) {
        return ResponseEntity.ok(entityMapper.toDto(baseManager.update(entityMapper.fromDto(y))));
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Y> get(@PathVariable(name = "id") String id) {
        T t = baseManager.find(id);
        return t != null ? ResponseEntity.ok(Objects.requireNonNull(entityMapper.toDto(t))) : ResponseEntity.ok().build();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/pages/{page}/{limit}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Y>> get(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        return ResponseEntity.ok(baseManager.findAll(page, limit).stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }

    @RequestMapping(
            value = "/batch",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Y>> batchUpdate(@RequestBody Collection<String> ids) {
        return ResponseEntity.ok(baseManager.find(ids).stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Y> post(@RequestBody @Valid Y y) {
        return ResponseEntity.of(Optional.ofNullable(entityMapper.toDto(baseManager.register(Objects.requireNonNull(entityMapper.fromDto(y))))));
    }

}
