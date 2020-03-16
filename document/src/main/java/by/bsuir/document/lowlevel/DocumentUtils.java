package by.bsuir.document.lowlevel;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static by.bsuir.document.lowlevel.OS.*;

@Component
public class DocumentUtils {
    private static final String OS = SystemUtils.OS_NAME.toLowerCase();

    private static String templatesPath;
    private static String documentsPath;

    @Value("${templates.path}")
    public void setTemplatesPath(String templatesPath) {
        DocumentUtils.templatesPath = templatesPath;
    }

    @Value("${documents.path}")
    public void setDocumentsPath(String documentsPath) {
        DocumentUtils.documentsPath = documentsPath;
    }

    public static void createDirs(String path) throws IOException {
        Files.createDirectories(Paths.get(path).getParent());
    }

    public static String getDocumentsPath(String path) {
        return documentsPath + "/" + path;
    }

    public static String getTemplate(String templateName) {
        return templatesPath + "/" + templateName;
    }

    public static OS getOS() {
        if (OS.contains("win")) {
            return WINDOWS;
        } else if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0) {
            return UNIX;
        } else if (OS.contains("mac")) {
            return MAC;
        } else {
            return OTHER;
        }
    }
}
