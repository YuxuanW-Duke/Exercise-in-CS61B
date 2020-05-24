public class LinkedListDeque<Item> implements Deque<Item> {

    private int size;

    private Node sentinelF;

    private Node sentinelL;

    private class Node {

        private Item item;

        private Node front;

        private Node next;


        public Node(Item x, Node f, Node n) {
            item = x;
            front = f;
            next = n;
        }
    }

    /** Empty List Constructor */
    public LinkedListDeque() {
        // 构造函数需要先给出来里面的member都是个啥
        sentinelF = new Node(null, null, null);
        sentinelL = new Node(null, null, null);
        // 下面这两行给出来的是member的关系，没说member是个啥
        sentinelF.next = sentinelL;
        sentinelL.front = sentinelF;
        size = 0;
    }

    /** List Constructor with 1 item */
    public LinkedListDeque(Item x) {
        new LinkedListDeque<>();
        addFirst(x);

    }

    /** Copy List Constructor */
    public LinkedListDeque(LinkedListDeque<Item> link) {
        new LinkedListDeque<Item>();
        Node temp = link.sentinelF;
        while (temp.next != null) {
            addLast(temp.next.item);
        }

    }


    public void addFirst(Item x) {
        sentinelF.next.front = new Node(x, sentinelF, sentinelF.next);
        sentinelF.next = sentinelF.next.front;
        size++;
    }


    public void addLast(Item x) {
        sentinelL.front.next = new Node(x, sentinelL.front, sentinelL);
        sentinelL.front = sentinelL.front.next;
        size++;
    }


    public Item removeFirst() {
        Item temp = sentinelF.next.item;
        sentinelF.next = sentinelF.next.next;
        sentinelF.next.front = sentinelF;
        size--;
        return temp;
    }


    public Item removeLast() {
        Item temp = sentinelL.front.item;
        sentinelL.front = sentinelL.front.front;
        sentinelF.front.next = sentinelL;
        size--;
        return temp;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        Node temp = sentinelF.next;
        System.out.print(temp.item + " ");
        if (temp.next == sentinelL) {
            System.out.println();
            return;
        }
        temp = temp.next;
    }


    public Item get(int index) {
        int cnt = 0;
        Node temp = sentinelF;
        while (cnt != index) {
            temp = temp.next;
            cnt++;
        }
        return temp.item;
    }

    /** Helper Method
     *  Recursive method的必要条件不是返回值，而是形参里面有没有recursive type*/
    public Item getRecursive(int index) {
        return getRecursive(index, sentinelF);
    }


    private Item getRecursive(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);

    }

    public void insert(Item x, int index) {
        Node temp = sentinelF;
        while (index != 0) {
            temp = temp.next;
            index--;
        }
        temp.front.next = new Node(x, temp.front, temp);
        temp.front = temp.front.next;
        size++;
    }
}
