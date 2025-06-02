package core.basesyntax.converter;

import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DataConverterImplTest {
    private DataConverter converter;

    @BeforeEach
    void setUp() {
        converter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_ValidData_Ok() {
        assertNotNull(converter.convertToTransaction(new ArrayList<>(Collections.singleton("first, second, third"))));
    }

    @Test
    void convertToTransaction_InvalidData_Ok() {
        assertTrue(converter.convertToTransaction(new ArrayList<>(Collections.singleton("first second third"))).isEmpty());
    }

    @Test
    void convertToTransaction_NullData_Ok() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> converter.convertToTransaction(null));
        assertEquals("Cannot invoke \"java.util.List.stream()\" because \"rawData\" is null", exception.getMessage());
    }

    @Test
    void convertToTransaction_EmptyData_Ok() {
        assertTrue(converter.convertToTransaction(new ArrayList<>()).isEmpty());
    }

    @Test
    void convertToTransaction_ValidMultipleData_Ok() {
        List<String> input = List.of(
                "header",
                "b,apple,10",
                "s,orange,20"
        );
        List<FruitTransaction> result = converter.convertToTransaction(input);
        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getQuantity());
        assertEquals(20, result.get(1).getQuantity());
    }

}