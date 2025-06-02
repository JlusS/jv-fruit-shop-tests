package core.basesyntax.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FruitStorageTest {
    @Test
    void getStorage() {
        assertNotNull(FruitStorage.getStorage());
    }
}
