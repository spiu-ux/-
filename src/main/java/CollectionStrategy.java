public interface CollectionStrategy {
    String getInitCode();
    Object getRealCollection();
    String addElement(int keyOrIndex, int value);
    String removeElement(int keyOrIndex);
    int getSize();
    String getAnswer();
}

