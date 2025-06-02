package core.basesyntax.exception;

import org.junit.Test;

public class FileProcessingExceptionTest {
    @Test
    public void getMessage() {
        FileProcessingException exception = new FileProcessingException("message");
        String message = exception.getMessage();
        assert message.equals("message");
    }
}
