package core.basesyntax.operation.impl;

import core.basesyntax.model.FruitStorage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OperationStrategyImplTest {

    @Test
    void processTransaction() {
        FruitStorage.getStorage().put("apple", 10);
        FruitTransaction transactionPurchase = new FruitTransaction(FruitTransaction.Operation.PURCHASE, "apple", 5);
        OperationStrategyImpl strategy = new OperationStrategyImpl(Map.of(FruitTransaction.Operation.PURCHASE, new PurchaseOperation()));
        strategy.processTransaction(transactionPurchase);
    }
}