import java.util.*;
import java.util.stream.Collectors;

public class SetStrategy implements CollectionStrategy {
    private final Set<Integer> set;
    private final String className;

    public SetStrategy() {
        int choice = new Random().nextInt(2);
        if (choice == 0) {
            this.set = new LinkedHashSet<>();
            this.className = "LinkedHashSet";
        } else {
            this.set = new TreeSet<>();
            this.className = "TreeSet";
        }
    }

    @Override
    public String getInitCode() {
        return String.format("Set<Integer> set = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() {return set;}

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
    public int getSize() {return set.size();}

    @Override
    public String getAnswer() {
        return set.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}

