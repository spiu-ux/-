import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class QueueStrategy implements CollectionStrategy {
    private final Queue<Integer> queue = new ArrayDeque<>();

    @Override
    public String getInitCode() {
        return "Queue<Integer> queue = new ArrayDeque<>();\n";
    }

    @Override
    public Object getRealCollection() {
        return queue;
    }

    @Override
    public String addElement(int keyOrIndex, int value) {
        queue.add(value);
        return String.format("queue.add(%d);\n", value);
    }

    @Override
    public String removeElement(int keyOrIndex) {
        if (queue.isEmpty()) {
            return "";
        }
        queue.remove();
        return "queue.remove();\n";
    }

    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public String getAnswer() {
        return queue.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}

