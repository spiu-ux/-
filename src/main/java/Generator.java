import java.util.Random;

import java.util.List;
import java.util.Random;

public class Generator {
    private static final Random random = new Random();
    private static final int[] NUMBER_POOL = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final List<Producer<CollectionStrategy>> STRATEGY_FACTORIES = List.of(
            MapStrategy::new,
            ListStrategy::new,
            SetStrategy::new,
            QueueStrategy::new,
            DequeStrategy::new
    );

    public Result generateValidTask() {
        while (true) {
            CollectionStrategy strategy = getRandomStrategy();
            String code = generateCodeForStrategy(strategy);
            if (strategy.getSize() >= 3 && strategy.getSize() <= 5) {
                return new Result(code, strategy.getAnswer());
            }
        }
    }

    private String generateCodeForStrategy(CollectionStrategy strategy) {
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append(strategy.getInitCode());

        appendAddOperations(strategy, codeBuilder);
        appendRemoveOperations(strategy, codeBuilder);

        return codeBuilder.toString();
    }

    private void appendAddOperations(CollectionStrategy strategy, StringBuilder codeBuilder) {
        int addCount = 5 + random.nextInt(2);
        for (int i = 0; i < addCount; i++) {
            int key = getRandomNumber();
            int value = getRandomNumber();
            codeBuilder.append(strategy.addElement(key, value));
        }
    }

    private void appendRemoveOperations(CollectionStrategy strategy, StringBuilder codeBuilder) {
        int removeCount = 1 + random.nextInt(2);
        for (int i = 0; i < removeCount; i++) {
            int keyToRemove = getRandomNumber();
            codeBuilder.append(strategy.removeElement(keyToRemove));
        }
    }

    private int getRandomNumber() {
        return NUMBER_POOL[random.nextInt(NUMBER_POOL.length)];
    }

    private CollectionStrategy getRandomStrategy() {
        int choice = random.nextInt(STRATEGY_FACTORIES.size());
        Producer<CollectionStrategy> producer = STRATEGY_FACTORIES.get(choice);
        return producer.produce();
    }
}
