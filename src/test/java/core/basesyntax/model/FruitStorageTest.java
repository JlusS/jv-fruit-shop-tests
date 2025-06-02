package core.basesyntax.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitStorageTest {

    @Test
    void getStorage() {
        assertNotNull(FruitStorage.getStorage());
    }
}