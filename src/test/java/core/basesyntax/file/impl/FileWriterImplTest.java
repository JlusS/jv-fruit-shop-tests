package core.basesyntax.file.impl;

import static org.junit.Assert.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterImplTest {
    @TempDir
    private Path tempDir;

    private FileWriterImpl fileWriter;

    @BeforeEach
    void setUp() {
        fileWriter = new FileWriterImpl();
    }

    @Test
    void write_ValidData_Ok() {
        Path tempFile = tempDir.resolve("test.txt");
        fileWriter.write("line1, line2", tempFile.toString());
        Assertions.assertTrue(tempFile.toFile().exists());
    }

    @Test
    void writeIoExceptionOk() {
        Path tempFile = tempDir.resolve("/nonexistent/directory/test.txt");
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> fileWriter.write("line1, line2", tempFile.toString())
        );
        Assertions.assertTrue(exception.getMessage().startsWith("Can't write data to file"));
    }
}
