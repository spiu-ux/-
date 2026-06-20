import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ListStrategy implements CollectionStrategy {
    private final List<Integer> list;
    private final String className;

    public ListStrategy() {
        List<Supplier<List<Integer>>> factories = List.of(
                ArrayList::new,
                LinkedList::new
        );

        int choice = new Random().nextInt(factories.size());
        this.list = factories.get(choice).get();
        this.className = this.list.getClass().getSimpleName();
    }

    @Override
    public String getInitCode() {
        return String.format("List<Integer> list = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() { return list; }

    @Override
    public String addElement(int keyOrIndex, int value) {
        list.add(value);
        return String.format("list.add(%d);\n", value);
    }

    @Override
    public String removeElement(int keyOrIndex) {
        if (list.isEmpty()) return "";
        int safeIndex = Math.abs(keyOrIndex) % list.size();
        list.remove(safeIndex);
        return String.format("list.remove(%d);\n", safeIndex);
    }

    @Override
    public int getSize() { return list.size(); }

    @Override
    public String getAnswer() {
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}



