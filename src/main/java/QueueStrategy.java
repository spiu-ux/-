import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class QueueStrategy implements CollectionStrategy {
    private final Queue<Integer> queue;
    private final String className;

    public QueueStrategy() {
        List<Supplier<Queue<Integer>>> factories = List.of(
                ArrayDeque::new,
                LinkedList::new
        );

        int choice = new Random().nextInt(factories.size());
        this.queue = factories.get(choice).get();
        this.className = this.queue.getClass().getSimpleName();
    }

    @Override
    public String getInitCode() {
        return String.format("Queue<Integer> queue = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() { return queue; }

    @Override
    public String addElement(int keyOrIndex, int value) {
        queue.add(value);
        return String.format("queue.add(%d);\n", value);
    }

    @Override
    public String removeElement(int keyOrIndex) {
        if (queue.isEmpty()) return "";
        queue.remove();
        return "queue.remove();\n";
    }

    @Override
    public int getSize() { return queue.size(); }

    @Override
    public String getAnswer() {
        return queue.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}


