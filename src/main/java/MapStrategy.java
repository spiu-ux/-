import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MapStrategy implements CollectionStrategy {
    private final Map<Integer, Integer> map;
    private final String className;

    public MapStrategy() {
        List<Supplier<Map<Integer, Integer>>> factories = List.of(
                LinkedHashMap::new,
                TreeMap::new
        );

        int choice = new Random().nextInt(factories.size());
        this.map = factories.get(choice).get();
        this.className = this.map.getClass().getSimpleName();
    }

    @Override
    public String getInitCode() {
        return String.format("Map<Integer, Integer> map = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() { return map; }

    @Override
    public String addElement(int key, int value) {
        map.put(key, value);
        return String.format("map.put(%d,%d);\n", key, value);
    }

    @Override
    public String removeElement(int key) {
        map.remove(key);
        return String.format("map.remove(%d);\n", key);
    }

    @Override
    public int getSize() { return map.size(); }

    @Override
    public String getAnswer() {
        return map.values().stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}

