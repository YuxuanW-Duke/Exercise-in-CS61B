package bearmaps;


import java.util.ArrayList;
import java.util.HashMap;

public class ArrayHeapMinPQ<Item> implements ExtrinsicMinPQ<Item>{

    private ArrayList<Node> heap;
    private HashMap<Item, Integer> addressBook;

    private class Node {
        private Item item;
        private double priority;

        public Node(Item item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public Item getItem() {
            return this.item;
        }

        public double getPriority() {
            return this.priority;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }
    }


    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        addressBook = new HashMap<>();
    }

    @Override
    public void add(Item item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("This item already exist.");
        }
        Node node = new Node(item, priority);
        heap.add(node);
        addressBook.put(item, heap.size() - 1);
        swim(heap.size() - 1);
    }

    private void swim(int index) {
        if (heap.size() > 1) {
            int swimmerIndex = index;
            int parentIndex = getParent(swimmerIndex);

            while (parentIndex >= 0
                    && heap.get(swimmerIndex).getPriority() < heap.get(parentIndex).getPriority()) {
                swap(swimmerIndex, parentIndex);
                swimmerIndex = parentIndex;
                parentIndex = getParent(parentIndex);
            }
        }
    }

    private int getParent(int index) {
        return (int)Math.ceil((double)index / 2) - 1;
    }

    private void swap(int index1, int index2) {
        Node node1 = heap.get(index1);
        Node node2 = heap.get(index2);
        heap.remove(index1);
        heap.add(index1, node2);
        heap.remove(index2);
        heap.add(index2, node1);
        addressBook.replace(node1.getItem(), index1, index2);
        addressBook.replace(node2.getItem(), index2, index1);
    }

    @Override
    public boolean contains(Item item) {
        return get(item) != null;
    }

    private Node get(Item item) {
        if (addressBook.containsKey(item)) {
            int index = addressBook.get(item);
            return heap.get(index);
        }
        return null;
    }

    @Override
    public Item getSmallest() {
        return heap.get(0).getItem();
    }

    @Override
    public Item removeSmallest() {
        Item result = heap.remove(0).getItem();
        heap.add(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        addressBook.remove(result);
        addressBook.replace(heap.get(heap.size() - 1).getItem(), 0);
        sink(0);
        return result;
    }


    private void sink(int index) {
        if (heap.size() > 1) {
            int sinkerIndex = index;
            int childIndex = getChild(sinkerIndex);

            while (childIndex != -1
                    && heap.get(sinkerIndex).getPriority() > heap.get(childIndex).getPriority()) {
                swap(sinkerIndex, childIndex);
                sinkerIndex = childIndex;
                childIndex = getChild(childIndex);
            }
        }
    }

    private int getChild(int index) {
        int leftChild = index*2 + 1;
        int rightChild = index*2 + 2;
        if (leftChild >= heap.size()) { return -1; }
        if (rightChild < heap.size()
                && heap.get(leftChild).getPriority() > heap.get(rightChild).getPriority()) {
            return rightChild;
        } else {
            return leftChild;
        }
    }


    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void changePriority(Item item, double priority) {
        if (!contains(item)) {
            throw new IllegalArgumentException("The item does not exist.");
        } else {
            Node node = get(item);
            node.setPriority(priority);
            sink(heap.indexOf(node));
            swim(heap.indexOf(node));
        }
    }
}
