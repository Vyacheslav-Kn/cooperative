package by.bsuir.rest.registry.controller;

import by.bsuir.registry.parser.CSVRegisterParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/restore")
public class RestoringController {

    private final CSVRegisterParser csvRegisterParser;

    public RestoringController(CSVRegisterParser csvRegisterParser) {
        this.csvRegisterParser = csvRegisterParser;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<Void> restore(@RequestParam("file") MultipartFile file) throws IOException {
        csvRegisterParser.restoreFromSource(file.getResource());
        return ResponseEntity.ok().build();
    }

}
