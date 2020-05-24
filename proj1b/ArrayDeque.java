public class ArrayDeque<Item> implements Deque<Item>{

    private Item[] list;

    private int size;


    public ArrayDeque() {
        list = (Item[]) new Object[100];
        size = 0;
    }


    public ArrayDeque(ArrayDeque another) {
        list = (Item[]) new Object[another.list.length];
        System.arraycopy(another, 0, list, 0, another.list.length);
        size = another.size;
    }


    private double calcRatio() {
        double ratio = size/list.length;
        return ratio;
    }


    private void upSize() {
        Item[] temp = (Item[]) new Object[size*2];
        System.arraycopy(list, 0, temp, 0, size);
        list = temp;
    }


    private void downSize() {
        Item[] temp = (Item[]) new Object[list.length/2];
        System.arraycopy(list, 0, temp, 0, size);
        list = temp;
    }


    public void addFirst(Item x) {
        if(size == list.length) {
            upSize();
        }
        Item[] temp = (Item[]) new Object[list.length];
        System.arraycopy(list,0, temp, 1, size);
        temp[0] = x;
        list = temp;
        size++;
    }


    public void addLast(Item x) {
        if (size == list.length) {
            upSize();
        }
        list[size] = x;
        size++;
    }


    public Item get(int index) {
        return list[index];
    }


    public Item removeFirst() {
        Item[] tempList = (Item[]) new Object[list.length];
        Item temp = list[0];
        System.arraycopy(list,1, tempList, 0, size);
        list = tempList;
        size--;
        if (calcRatio() < 0.25) {
            downSize();
        }
        return temp;
    }


    public Item removeLast() {
        Item temp = get(size - 1);
        list[size - 1] = null;
        size--;
        if (calcRatio() < 0.25) {
            downSize();
        }
        return temp;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public void insert(Item x, int index) {
        if (size == list.length) {
            upSize();
        }
        Item[] tempList = (Item[]) new Object[size - index + 1];
        System.arraycopy(list, index, tempList, 0, size - index + 1);
        list[index] = x;
        System.arraycopy(tempList, 0, list, index + 1, tempList.length);
    }
}
