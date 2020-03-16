package by.bsuir.document.service.document;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.model.template.Template;

import java.util.Map;

/**
 * Service to working with physical documents.
 *
 * @author Vladislav Novitskiy
 */
public interface PhysicalDocumentService {
    /**
     * Method generates document of specified type by tag values at specified directory.
     *
     * @param template   Type of generated document
     * @param params     Values of parameters for tags
     * @param outputPath Output dir
     */
    void generate(Template template, Map<Tag.Param, String> params, String outputPath);

    /**
     * Method open physical document at specified path.
     *
     * @param path Path to document
     */
    void open(String path);
}
