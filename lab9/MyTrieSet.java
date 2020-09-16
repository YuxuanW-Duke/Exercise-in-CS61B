import edu.princeton.cs.algs4.BST;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyTrieSet implements TrieSet61B {

    private static final int R = 256;

    private int size;
    private Node root;


    private class Node {

        private boolean isKey;
        private BST<Character, Node> table;

        public Node() {
            this.isKey = false;
            this.table = new BST<>();
        }

        public void clear() {
            this.isKey = false;
            this.table = new BST<>();
        }

        public void setNext(char c, boolean isKey) {
            Node next = new Node();
            next.setKey(isKey);
            this.table.put(c, next);
        }

        // if this node contains char c in its children. Iterate through its BST to check out.
        public boolean containChar(char c) {
            return this.table.contains(c);
        }

        public Node getNext(char c) {
            if (!containChar(c)) {
                throw new IllegalArgumentException("This key is not contained in this node.");
            } else {
                return this.table.get(c);
            }
        }
        // Return all the keys in the table.
        public Iterable<Character> keySet() {
            Iterable<Character> results;
            results = table.keys();
            return results;
        }

        public void setKey(boolean isKey) {
            this.isKey = isKey;
        }

    }

    public MyTrieSet() {
        this.size = 0;
        this.root = new Node();
    }

    @Override
    public void clear() {
        root.clear();
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is null.");
        } else {
            Node node = root;
            for (int i = 0; i < key.length(); i++) {
                char a = key.charAt(i);
                if (!node.containChar(a)) { return false; }
                node = node.getNext(a);
            }
            return true;
        }
    }


    @Override
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is null.");
        }
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            char a = key.charAt(i);
            if (!node.containChar(a)) {
                node.setNext(a, false);
            }
            node = node.getNext(a);
        }
        node.setKey(true);
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("The key is null.");
        }
        Node node = root;
        List<String> results = new LinkedList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char a = prefix.charAt(i);
            if (!node.containChar(a)) {
                return null;
            }
            node = node.getNext(a);
        }
        results = colHelp(prefix, results, node);
        return results;
    }

    private List<String> colHelp(String curr, List<String> list, Node node) {
        if (node.isKey) {
            list.add(curr);
        }
        for (char c:node.keySet()) {
            list = colHelp(curr + c, list, node.getNext(c));
        }
        return list;
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }
}
