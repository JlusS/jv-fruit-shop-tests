package core.basesyntax.service;

import core.basesyntax.model.FruitStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.operation.OperationStrategy;
import core.basesyntax.operation.impl.OperationStrategyImpl;
import core.basesyntax.operation.impl.PurchaseOperation;
import core.basesyntax.operation.impl.SupplyOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceImplTest {
    private OperationStrategy strategy;
    @BeforeEach
    void setUp() {
        strategy = new OperationStrategyImpl(Map.of(FruitTransaction.Operation.SUPPLY, new SupplyOperation()));
    }
    @Test
    void process() {
        ShopServiceImpl service = new ShopServiceImpl(strategy);
        service.process(List.of(new FruitTransaction(FruitTransaction.Operation.SUPPLY, "apple", 5)));
        assertEquals(5, FruitStorage.getStorage().get("apple"));
    }
}