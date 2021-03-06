package es.datastructur.synthesizer;

public interface BoundedQueue<T> extends Iterable<T>{
    /** return size of the buffer */
    int capacity();

    /** return number of items currently in the buffer */
    int fillCount();

    /** add item x to the end */
    void enqueue(T x);

    /** delete and return item from the front */
    T dequeue();

    /** return but not delete item from the front */
    T peek();

    /** see if the buffer is empty */
    default boolean isEmpty(){
        return fillCount() == 0;
    }

    /** see if the buffer is full */
    default boolean isFull(){
        return capacity() == fillCount();
    }

}
