import java.util.*;
import java.util.stream.Collectors;

public class MapStrategy implements CollectionStrategy {
    private final Map<Integer, Integer> map;
    private final String className;

    public MapStrategy() {
        int choice = new Random().nextInt(3);
        if (choice == 0) {
            this.map = new HashMap<>();
            this.className = "HashMap";
        } else if (choice == 1) {
            this.map = new LinkedHashMap<>();
            this.className = "LinkedHashMap";
        } else {
            this.map = new TreeMap<>();
            this.className = "TreeMap";
        }
    }

    @Override
    public String getInitCode() {return String.format("Map<Integer, Integer> map = new %s<>();\n", className);}

    @Override
    public Object getRealCollection() {return map;}

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
    public int getSize() {return map.size();}

    @Override
    public String getAnswer(){
        return map.values().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}

