package core.basesyntax.operation.impl;

import core.basesyntax.model.FruitStorage;
import core.basesyntax.model.FruitTransaction;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceOperationTest {
    BalanceOperation operation;

    @BeforeEach
    void setUp() {
        operation = new BalanceOperation();
    }


    @Test
    void handle_Ok() {
        FruitTransaction transaction = new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 10);
        operation.handle(transaction);
        assertEquals(10, FruitStorage.getStorage().get("apple"));
    }
}