package by.bsuir.document.lowlevel;

import by.bsuir.document.exception.DocumentGenerationException;
import by.bsuir.document.exception.DocumentOpeningException;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * Low level operations with physical document.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class DocumentOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentOperations.class);

    public static void generate(String templatePath, String outputPath, Map<String, Object> tagValues) {
        try {
            String fullPath = DocumentUtils.getDocumentsPath(outputPath);
            DocumentUtils.createDirs(fullPath);
            LOGGER.info("File '{}' created", outputPath);

            // Load Docx file by filling Velocity template engine and cache it to the registry
            InputStream in = new FileInputStream(new File(DocumentUtils.getTemplate(templatePath)));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            // Create context Java model
            IContext context = report.createContext(tagValues);

            // Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream(fullPath);
            report.process(context, out);
            LOGGER.info("File '{}' successfully saved", outputPath);
        } catch (XDocReportException | IOException e) {
            throw new DocumentGenerationException(e);
        }
    }

    public static void open(String path) {
        try {
            String fullPath = DocumentUtils.getDocumentsPath(path);
            File file = new File(fullPath);
            if (!file.exists()) {
                throw new DocumentOpeningException("File " + path + " does'nt exist");
            }

            OS os = DocumentUtils.getOS();
            switch (os) {
                case WINDOWS:
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "
                            + file.getAbsolutePath());
                    break;
                case UNIX:
                case MAC:
                    Runtime.getRuntime().exec("open " + file.getAbsolutePath());
                    break;
                case OTHER:
                    throw new DocumentOpeningException("Current OS not supports file opening");
                default:
                    throw new EnumConstantNotPresentException(OS.class, os.name());
            }
        } catch (IOException e) {
            throw new DocumentOpeningException(e);
        }
    }


}
