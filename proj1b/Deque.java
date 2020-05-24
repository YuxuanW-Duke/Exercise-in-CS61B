public interface Deque<Item> {

    public void addFirst(Item x);

    public void addLast(Item x);

    public Item removeFirst();

    public Item removeLast();

    public int size();

    public void printDeque();

    public Item get(int index);

    public void insert(Item x, int index);

    default public boolean isEmpty() {
        return size() == 0;
    }
}
