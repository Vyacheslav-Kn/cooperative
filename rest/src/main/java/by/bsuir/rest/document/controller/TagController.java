package by.bsuir.rest.document.controller;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.service.tag.TagManager;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to work with {@link Tag} entities.
 *
 * @author Vladislav Novitskiy
 */
@Api
@AllArgsConstructor
@RestController
@RequestMapping("/api/tag")
public class TagController {
    private TagManager tagManager;

    @GetMapping
    public ResponseEntity<List<Tag>> getAll() {
        return ResponseEntity.ok(tagManager.findAll());
    }
}
