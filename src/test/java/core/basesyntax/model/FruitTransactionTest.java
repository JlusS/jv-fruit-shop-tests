package core.basesyntax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FruitTransactionTest {
    private FruitTransaction transaction1;
    private FruitTransaction transaction2;

    @BeforeEach
    void setUp() {
        transaction1 = new FruitTransaction(FruitTransaction.Operation.PURCHASE, "APPLE", 1);
        transaction2 = new FruitTransaction(FruitTransaction.Operation.BALANCE, "BANANA", 5);
    }

    @Test
    void getOperation_NutNull_Ok() {
        assertNotNull(transaction1.getOperation());
    }

    @Test
    void getOperation_Ok() {
        assertEquals(FruitTransaction.Operation.BALANCE, transaction2.getOperation());
    }

    @Test
    void getFruit_Ok() {
        assertEquals("APPLE", transaction1.getFruit());
    }

    @Test
    void getQuantity_Ok() {
        assertEquals(1, transaction1.getQuantity());
    }

    @Test
    void toString_AllOperations_Ok() {
        FruitTransaction t1 = new FruitTransaction(FruitTransaction.Operation.PURCHASE, "APPLE", 1);
        String expected1 = "FruitTransaction{operation=PURCHASE, fruit='APPLE', quantity=1}";
        assertEquals(expected1, t1.toString());

        FruitTransaction t2 = new FruitTransaction(FruitTransaction.Operation.BALANCE, "BANANA", 5);
        String expected2 = "FruitTransaction{operation=BALANCE, fruit='BANANA', quantity=5}";
        assertEquals(expected2, t2.toString());

        FruitTransaction t3 = new FruitTransaction(FruitTransaction.Operation.SUPPLY, "ORANGE", 3);
        String expected3 = "FruitTransaction{operation=SUPPLY, fruit='ORANGE', quantity=3}";
        assertEquals(expected3, t3.toString());

        FruitTransaction t4 = new FruitTransaction(FruitTransaction.Operation.RETURN, "GRAPE", 2);
        String expected4 = "FruitTransaction{operation=RETURN, fruit='GRAPE', quantity=2}";
        assertEquals(expected4, t4.toString());
    }

    @Test
    void fromCode_exhaustiveErrorPath_Ok() {
        // Test that we go through all valid codes and still throw exception for invalid
        String invalidCode = "z"; // A code that doesn't match any operation
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode(invalidCode));
        assertEquals("Invalid code: " + invalidCode, exception.getMessage());
    }

    @Test
    void fromCode_validCode_Ok() {
        assertEquals(transaction1.getOperation(), FruitTransaction.Operation.fromCode("p"));
    }

    @Test
    void fromCode_completePathCoverage_Ok() {
        // First verify we can match each code in sequence
        for (FruitTransaction.Operation op : FruitTransaction.Operation.values()) {
            assertEquals(op, FruitTransaction.Operation.fromCode(op.getCode()));
        }

        // Now verify the exception path with different invalid inputs
        String[] invalidCodes = {"x", "z", "q", "", "abc"};
        for (String invalidCode : invalidCodes) {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> FruitTransaction.Operation.fromCode(invalidCode));
            assertEquals("Invalid code: " + invalidCode, e.getMessage());
        }

        // Also test null specifically
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode(null));
        assertEquals("Invalid code: " + null, e.getMessage());
    }

    @Test
    void fromCode_nullCode_Ok() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode(null));
        assertTrue(exception.getMessage().startsWith("Invalid code:"));
    }

    @Test
    void fromCode_emptyCode_Ok() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode(""));
        assertTrue(exception.getMessage().startsWith("Invalid code:"));
    }
}
