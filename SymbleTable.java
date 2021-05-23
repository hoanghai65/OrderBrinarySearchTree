interface SymbleTable<Key extends Comparable<Key>,Value>{
    void put(Key key, Value value);
    Value get(Key key);
    void delete(Key key);
    boolean isEmpty();
    int size();
    boolean constain(Key key);
    Iterable<Key> Keys();
}