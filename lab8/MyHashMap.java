import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int size = 0;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private BucketEntity<K, V>[] buckets;


    private class BucketEntity<k, v> {

        private k key;

        private v value;

        private BucketEntity<k, v> next;


        public BucketEntity(k key, v value) {
            this.key = key;
            this.value = value;
        }


        public k getKey() { return key; }


        public v getValue() { return value; }


        public void setValue(v value) { this.value = value; }


        public BucketEntity<k, v> getNext() { return this.next; }
    }

    public MyHashMap() {
        this.buckets = new BucketEntity[this.capacity];
    }

    public MyHashMap(int initialSize) {
        this.capacity = initialSize;
        this.buckets = new BucketEntity[this.capacity];
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.capacity = initialSize;
        this.loadFactor = loadFactor;
        this.buckets = new BucketEntity[this.capacity];
    }

    private int hash(K key, int length) {
        if (key == null) {
            throw new IllegalArgumentException("Key inputted is null.");
        } else {
            return (key.hashCode() & 0x7fffffff) % length;
        }
    }


    private void reSize(){
        capacity = 2*capacity;
        BucketEntity<K, V>[] newBuckets = new BucketEntity[capacity];
        for (int i = 0; i < buckets.length; i++) {
            BucketEntity<K, V> tempOld = buckets[i];
            while (tempOld != null) {
                int index = hash(tempOld.key, capacity);
                BucketEntity<K, V> tempNew = newBuckets[index];
                newBuckets[index] = new BucketEntity<>(tempOld.getKey(), tempOld.getValue());
                newBuckets[index].next = tempNew;
                tempOld = tempOld.next;
            }
        }
        buckets = newBuckets;
    }


    @Override
    public void clear() {
        this.buckets = new BucketEntity[this.capacity];
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key inputted is null.");
        } else {
            return get(key) != null;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key inputted is null.");
        } else {
            //BucketEntity entity = get(hash(key, capacity), key);
            BucketEntity<K, V> entity = get(hash(key, capacity), key);
            return entity == null ? null : entity.getValue();
        }
    }


    private BucketEntity<K, V> get(int index, K key) {
        BucketEntity<K, V> temp = buckets[index];
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                return temp;
            } else {
                temp = temp.getNext();
            }
        }
        return null;
    }



    @Override
    public int size() {
        return this.size;
    }


    @Override
    public void put(K key, V value) {
        int index = hash(key, capacity);
        BucketEntity<K, V> temp = buckets[index];
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                temp.setValue(value);
                return;
            }
            temp = temp.getNext();
        }
        temp = buckets[index];
        buckets[index] = new BucketEntity<>(key, value);
        buckets[index].next = temp;
        size += 1;
        if (size > loadFactor*capacity) {
            reSize();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for (BucketEntity<K, V> b : buckets) {
            BucketEntity<K, V> bTemp = b;
            while (bTemp != null) {
                allKeys.add(bTemp.getKey());
                bTemp = bTemp.getNext();
            }
        }
        return allKeys;
    }

    @Override
    public V remove(K key) {
        if (!this.containsKey(key)) {
            return null;
        }
        int index = hash(key, capacity);
        return remove(key, index);
    }


    private V remove(K key, int index) {
        BucketEntity<K, V> curr = buckets[index];
        BucketEntity<K, V> currNext = buckets[index].getNext();

        if (curr.getKey().equals(key)) {
            buckets[index] = currNext;
            return curr.getValue();
        } else {
            while (currNext != null) {
                if (currNext.getKey().equals(key)) {
                    curr.next = currNext.getNext();
                    return currNext.getValue();
                } else {
                    curr = currNext;
                    currNext = currNext.getNext();
                }
            }
            return null;
        }
    }


    @Override
    public V remove(K key, V value) {
        return null;
    }


    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
