

public abstract class AbstractSymbleTable<Key extends Comparable<Key>, Value> implements SymbleTable<Key,Value> {
        @Override
        public boolean isEmpty() {
            return size() == 0;
        }
        @Override
        public void delete(Key key) {
          if(constain(key)){
              put(key , null);
          }
        }
}
