package core.basesyntax.exception;

import org.junit.Test;

public class InvalidDataExceptionTest {
    @Test
    public void getMessage() {
        InvalidDataException exception = new InvalidDataException("message");
        String message = exception.getMessage();
        assert message.equals("message");
    }
}
