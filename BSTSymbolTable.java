import java.util.Iterator;
import java.util.Stack;

class BSTSymbolTable<Key extends Comparable<Key>, Value> extends AbstractSymbleTable<Key,Value>{

    private Node root; 

    private class Node{
        private Key key;
        private Value value;
        private Node left,right;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
           
        }
    }
   

    @Override
    public void put(Key key, Value value) {
        // TODO Auto-generated method stub
        Node p = root;
        if(p == null){
            root = new Node(key, value);
        }
        while(p != null){
           int c = key.compareTo(p.key);
           if(c == 0){
               p.value = value;
               return;
           }
           else{
               if(c < 0){
                    if(p.left == null){
                        p.left = new Node(key, value);
                        return;
                    }
                    p = p.left;
               }
               else{
                    if(p.right == null){
                        p.right = new Node(key, value);
                        return;
                    }
                    p = p.right;
               }
           }
        }
        
    }

    // public Node put(Node node , Key key, Value value){
    //     if(node == null){
    //         return new Node(key, value);
    //     }
    //     else{
    //         int c = key.compareTo(node.key);
    //         if(c < 0){
    //             node.left = put(node.left, key, value);
    //         }
    //         else if(c > 0){
    //             node.right = put(node.right, key, value);
    //         }
    //         else node.value = value;
    //     }
    //     return node;
    // }

    

    private Node search(Key key){
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
    public Value get(Key key) {
        // TODO Auto-generated method stub
        if(isEmpty()){
            return null;
        }
        Node  p = search(key);
        if(p != null){
            return p.value;
        }
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        Stack<Node> stack = new Stack<>();
        int n = 0;
        stack.push(root);
        while(!stack.isEmpty()){
            Node p = stack.pop();
            if(p.value != null){
                n++;
            }
            if(p.left != null){
                stack.push(p.left);
            }
            if(p.right != null){
                stack.push(p.right);
            }
        }
        return n;
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
        
        return new BSTiterable();
    }

    class BSTiterable implements Iterable<Key> {

        @Override
        public Iterator<Key> iterator() {
            // TODO Auto-generated method stub
            return new BSTiterator();
        }

    }

    class BSTiterator implements Iterator<Key>{
        Queue<Key> queue;
        Node node = root;
        public BSTiterator(){
            queue = new LinklListQueue<>();
            inorder(node);
        }

        public void inorder(Node node){
            if(node != null){
                inorder(node.left);
                if(node.value != null){
                    queue.enqueue(node.key);
                }
                inorder(node.right);
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