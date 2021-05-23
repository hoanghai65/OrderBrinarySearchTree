
public  interface Queue<Key extends Comparable<Key>> extends Iterable<Key>{
    void enqueue(Key key);
    Key dequeue();
    void delete(Key key);
    Key poll();
    boolean isEmpty();
    int size();
}