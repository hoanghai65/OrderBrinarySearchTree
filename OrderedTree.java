import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class OrderedTree<Key extends Comparable<Key> ,Value> implements OrderedSymbolTable<Key,Value>{

    private class Node{
        private Key key;
        private Value value;
        private Node left , right;
        private int count;

        private Node(Key key,Value value){
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    private Node root;

    @Override
    public void put(Key key, Value value) {
        // TODO Auto-generated method stub
        root = put(root, key, value);
        
    }

    private Node put(Node node , Key key, Value value){
        if(node == null){
            return new Node(key, value);
        }
        int c = key.compareTo(node.key);
        if(c == 0){
            node.value = value;
        }
        else if(c < 0){
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Value get(Key key) {
        // TODO Auto-generated method stub
        Node p =search(key);
        if(p != null){
            return p.value;
        }
        return null;
    }

    private Node search(Key key){
        if(key == null || isEmpty()){
            return null;
        }
        Node p = root;
        while(p != null){
            int c = key.compareTo(p.key);
            if(c == 0){
                return p;
            }
            else if(c < 0){
                p = p.left;
            }
            else p = p.right;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        // TODO Auto-generated method stub
        root = delete(root, key);
        
    }

    private Node delete(Node node , Key key){
        if(node == null){
            return null;
        }
        int c = key.compareTo(node.key);
        if(c < 0){
            node.left = delete(node.left, key);
        }
        else if(c > 0){
            node.right = delete(node.right, key);
        }
        else {
           if(node.left == null){
               return node.right;
           }
           else if(node.right == null){
               return node.left;
           }
           node.key = min(node.right);
           node.right = delete(node.right, node.key);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Key min(Node node){
        if(node.left == null){
            return node.key;
        }
        return min(node.left);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return root == null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        
        return size(root);
    }

    private int size(Node node){
        if(node == null){
            return 0;
        }
        return node.count;
    }

  

    @Override
    public boolean constain(Key key) {
        // TODO Auto-generated method stub
        if(search(key) != null){
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Key> Keys() {
        // TODO Auto-generated method stub
        return new BstIterable();
    }

    @Override
    public Key min() {
        // TODO Auto-generated method stub
        Node p = root;
        if(p == null){
            return null;
        }
        while(p.left != null){
            p = p.left;
        }
        return p.key;
    }

    @Override
    public Key max() {
        // TODO Auto-generated method stub
        Node p = root;
        if(p == null){
            return null;
        }
        while(p.right != null){
            p = p.right;
        }
        return p.key;
    }

    @Override
    public Key floor(Key key) {
        // TODO Auto-generated method stub
        if(key == null){
            return null;
        }
        Node p = root;
        
        return floor(p,key).key;
    }

    private Node floor(Node node , Key key){
        if(node == null){
            return null;
        }
        int c = key.compareTo(node.key);
        if(c == 0){
            return node;
        }
        else if(c < 0){
            return floor(node.left, key);
        }
        Node p = floor(node.right, key);
        if(p != null){
            return p;
        }
        return node;
    }

   

    @Override
    public Key ceiling(Key key) {
        Node p = root;
        return celling(p, key).key;
    }

    private Node celling(Node node , Key key){
        if(node == null){
            return null;
        }
        int c = key.compareTo(node.key);
        if(c == 0){
            return node;
        }
        else if(c > 0){
            return celling(node.right, key);
        }
        Node p = celling(node.left, key);
        if(p != null){
            return p;
        }
        return node;
    }

    @Override
    public int rank(Key key) {
        // TODO Auto-generated method stub
        Node p = root;
        return rank(p, key);
    }

    private int rank(Node node , Key key){
        if(node == null){
            return 0;
        }
        int c = key.compareTo(node.key);
        if(c < 0){
            return rank(node.left, key);
        }
        else if(c > 0){
            return 1 + size(node.left) + rank(node.right, key);
        }
        else return size(node.left);

    }

    @Override
    public Key select(int rank) {
        // TODO Auto-generated method stub
        Node node = root;
        return select(node, rank).key;
    }

    private Node select(Node node , int rank){
        if(node == null)
             return null;
        int c = size(node.left);
        if(c > rank){
            return select(node.left, rank);
        }
        else if(c < rank){
            return select(node.right,rank - c - 1);
        }
        else return node;
    }

    @Override
    public void deleteMin() {
        // TODO Auto-generated method stub
        Node p = root;
        root = deleteMin(p);
        
    }

    public Node deleteMin(Node node){
        if(node.left == null){
            return node.right;
        }
       node.left = deleteMin(node.left);
       node.count = 1 + size(node.left) + size(node.right);
       return node;
    }
   

    @Override
    public void deleteMax() {
        // TODO Auto-generated method stub
        root = deleteMax(root);
    } 

    private Node deleteMax(Node node){
        if(node.right == null){
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public int size(Key u, Key v) {
        // TODO Auto-generated method stub
        return Math.abs(rank(v) - rank(u)) + 1;
    }

    private Node addNode(Node node ,Key u, Key v,List<Key> list){
        if(node == null || u.compareTo(node.key) > 0){
            return null;
        }
        
        int c  = v.compareTo(node.key);
        if(c >= 0){
            list.add(node.key);
            addNode(node.left, u, v, list);
            addNode(node.right, u, v, list);
        }
        else {
            node.left = addNode(node.left, u, v, list);
        }
        return node;
        
    }
    @Override
    public Iterable<Key> keys(Key u, Key v) {
        // TODO Auto-generated method stub
        List<Key> list = new LinkedList<>();
        Node node = root;
        addNode(node, u, v, list);
        return list;
    }
    class BstIterable implements Iterable<Key>{

        @Override
        public Iterator<Key> iterator() {
            // TODO Auto-generated method stub
            return new BstIterator();
        }

    }

    class BstIterator implements Iterator<Key>{
        private Queue<Key> queue;
        Node node = root;
        public BstIterator(){
            queue = new LinklListQueue<>();
            prents(node);
        }

        public void prents(Node node){
            if(node != null){
                queue.enqueue(node.key);
                prents(node.left);
                prents(node.right);
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return !queue.isEmpty();
        }



        @Override
        public Key next() {
            // TODO Auto-generated method stub
            return queue.dequeue();
        }

    }
    
}
