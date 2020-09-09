import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        public K key;            //sorted by key
        public V value;        //associated value
        public Node left, right;   //left and right subtrees
        public int size;           //number of nodes in subtree

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) { throw new IllegalArgumentException("Key is null"); }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
            return get(root, key);
    }

    private V get(Node node, K key) {
        if (key == null) { throw new IllegalArgumentException("Key is null");}
        if (node == null) { return null; }
        int cmp = key.compareTo(node.key);
        if (cmp > 0)        return get(node.right, key);
        else if (cmp < 0)   return get(node.left, key);
        else                return node.value;
    }

    @Override
    public int size(){
        return size(root);
    }

    public int size(Node node) {
        return node.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) { throw new IllegalArgumentException("Key is null."); }
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null)   return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp > 0)        node.right = put(node.right, key, value);
        else if (cmp < 0)   node.left = put(node.left, key, value);
        else                node.value = value;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            BSTSet.add(select(i).key);
        }
        return null;
    }

    private Node select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new  IllegalArgumentException();
        }
        return select(root, rank);
    }

    private Node select(Node node, int rank) {
        if (node == null) { return null; }
        int sizeOfLeft = size(node.left);
        if (sizeOfLeft > rank) {
            return select(node.left, rank);
        } else if (sizeOfLeft < rank) {
            return select(node.right, rank - sizeOfLeft - 1);
        } else {
            return node;
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("This operation is undefined.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This operation is undefined.");
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node src) {
            while (src != null) {
                stack.push(src);
                src = src.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next(){
            Node curr = stack.pop();
            if (curr.right != null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }
}
