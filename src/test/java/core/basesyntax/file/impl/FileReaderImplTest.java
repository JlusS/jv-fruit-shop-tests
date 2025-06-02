package core.basesyntax.file.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.FileProcessingException;
import core.basesyntax.file.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileReaderImplTest {
    @TempDir
    private Path tempDir;
    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileReaderImpl();
    }

    @Test
    void read_ValidFile_Ok() throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        List<String> expectedContent = List.of("line1", "line2");
        Files.write(tempFile, expectedContent);

        List<String> actualContent = fileReader.read(tempFile.toString());

        Assertions.assertEquals(expectedContent, actualContent);
    }

    @Test
    void read_FileNotFound_Ok() {
        String nonExistentPath = tempDir.resolve("non-existent.txt").toString();

        FileProcessingException exception = assertThrows(
                FileProcessingException.class,
                () -> fileReader.read(nonExistentPath)
        );
        Assertions.assertTrue(exception.getMessage().startsWith("File not found:"));
    }

    @Test
    void read_UnreadableFile_Ok() throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        Files.write(tempFile, List.of());

        try (FileChannel channel = FileChannel
                .open(tempFile, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            FileLock lock = channel.lock();

            FileProcessingException exception = assertThrows(
                    FileProcessingException.class,
                    () -> fileReader.read(tempFile.toString())
            );
            Assertions.assertTrue(exception.getMessage().startsWith("Can't read file:"));

            lock.release();
        }
    }
}
