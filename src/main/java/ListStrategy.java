import java.util.*;
import java.util.stream.Collectors;

public class ListStrategy implements CollectionStrategy{
    private final List<Integer> list;
    private final String className;

    public ListStrategy(){
        if (new Random().nextBoolean()) {
            this.list = new ArrayList<>();
            this.className = "ArrayList";
        } else {
            this.list = new LinkedList<>();
            this.className = "LinkedList";
        }
    }

    @Override
    public String getInitCode() {
        return String.format("List<Integer> list = new %s<>();\n", className);
    }

    @Override
    public Object getRealCollection() {return list;}

    @Override
    public String addElement(int keyOrIndex, int value) {
        list.add(value);
        return String.format("list.add(%d);\n", value);
    }

    @Override
    public String removeElement(int keyOrIndex) {
        if (list.isEmpty()) {
            return "";
        }
        int safeIndex = Math.abs(keyOrIndex) % list.size();

        list.remove(safeIndex);
        return String.format("list.remove(%d);\n", safeIndex);
    }

    @Override
    public int getSize() {return list.size();}

    @Override
    public String getAnswer() {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}



