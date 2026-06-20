import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.stream.Collectors;

public class DequeStrategy implements CollectionStrategy {
    private final Deque<Integer> deque = new ArrayDeque<>();
    private final Random random = new Random();

    @Override
    public String getInitCode() {
        return "Deque<Integer> deque = new ArrayDeque<>();\n";
    }

    @Override
    public Object getRealCollection() {
        return deque;
    }

    @Override
    public String addElement(int keyOrIndex, int value) {
        if (random.nextBoolean()) {
            deque.addFirst(value);
            return String.format("deque.addFirst(%d);\n", value);
        } else {
            deque.addLast(value);
            return String.format("deque.addLast(%d);\n", value);
        }
    }

    @Override
    public String removeElement(int keyOrIndex) {
        if (deque.isEmpty()) {
            return "";
        }
        if (random.nextBoolean()) {
            deque.removeFirst();
            return "deque.removeFirst();\n";
        } else {
            deque.removeLast();
            return "deque.removeLast();\n";
        }
    }

    @Override
    public int getSize() {
        return deque.size();
    }

    @Override
    public String getAnswer() {
        return deque.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}

