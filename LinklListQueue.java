import java.util.Iterator;

public class LinklListQueue<Key extends Comparable<Key> > implements Queue<Key> {

    class Node{
        Node next;
        Key key;

        public Node(Key key){
            this.key = key;
            next = null;
        }
    }

    private Node list;
    private Node rear;

    @Override
    public void enqueue(Key key) {
        // TODO Auto-generated method stub
        Node p = new Node(key);
        if(list == null){
            list = p;
        }
        else{
            rear.next = p;
        }
        rear = p;
        
    }

    @Override
    public Key dequeue() {
        // TODO Auto-generated method stub
        Key key = list.key;
        list = list.next;
        return key;
    }

    @Override
    public void delete(Key key) {
        
        if(key == null || isEmpty()){
            return;
        }
        if(key.equals(list.key)){
            list = list.next;
            return;
        }
        if(constain(key)){
            Node p = list;
            while(p.next != null){
                if(key.equals(p.next.key)){
                    break;
                }
                p = p.next;
            }
            p.next = p.next.next;
        }

    }

    private boolean constain(Key key){
        if(key == null || isEmpty()){
            return false;
        }
        Node p = list;
        while(p != null){
            if(key.equals(p.key)){
                return true;
            }
            p = p.next;
        }

        return false;
        
    }

    @Override
    public Key poll() {
        // TODO Auto-generated method stub
        Key key = list.key;
        list = list.next;
        return key;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return list == null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        Node p = list;
        int n = 0;
        while(p != null){
            n++;
            p = p.next;
        }
        return n;
    }

    @Override
    public Iterator<Key> iterator() {
        // TODO Auto-generated method stub
        return new QueueIterator();
    }

    class QueueIterator implements Iterator<Key>{
        Node node = list;
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return node != null;
        }

        @Override
        public Key next() {
            // TODO Auto-generated method stub
            Key key = node.key;
            node = node.next;
            return key;
        }

    }
    
    
}
