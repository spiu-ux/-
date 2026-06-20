import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SetStrategy implements CollectionStrategy {
    private final Set<Integer> set;
    private final String className;

    public SetStrategy() {
        List<Supplier<Set<Integer>>> factories = List.of(
                LinkedHashSet::new,
                TreeSet::new
        );

        int choice = new Random().nextInt(factories.size());
        this.set = factories.get(choice).get();
        this.className = this.set.getClass().getSimpleName();
    }

    @Override
    public String getInitCode() {
        return String.format("Set<Integer> set = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() { return set; }

    @Override
    public String addElement(int keyOrIndex, int value) {
        set.add(value);
        return String.format("set.add(%d);\n", value);
    }

    @Override
    public String removeElement(int keyOrIndex) {
        set.remove(keyOrIndex);
        return String.format("set.remove(%d);\n", keyOrIndex);
    }

    @Override
    public int getSize() { return set.size(); }

    @Override
    public String getAnswer() {
        return set.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}


