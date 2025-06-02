package core.basesyntax.operation.impl;

import core.basesyntax.model.FruitStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.operation.OperationHandler;

public class SupplyOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        if (!FruitStorage.getStorage().containsKey(transaction.getFruit())) {
            FruitStorage.getStorage().put(transaction.getFruit(), transaction.getQuantity());
            return;
        }
        FruitStorage.getStorage()
                .put(transaction.getFruit(), FruitStorage.getStorage()
                        .get(transaction.getFruit()) + transaction.getQuantity());
    }
}
