package by.bsuir.document.exception;

import java.io.IOException;

/**
 * @author Vladislav Novitskiy
 */
public class DocumentOpeningException extends RuntimeException {
    public DocumentOpeningException(IOException e) {
        super(e);
    }

    public DocumentOpeningException(String message) {
        super(message);
    }
}
