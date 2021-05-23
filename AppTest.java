import java.util.List;

public class AppTest {

    // public static void testQueue(Queue<Integer> queue) {
    // queue.enqueue(1);
    // queue.enqueue(3);
    // queue.enqueue(5);
    // queue.enqueue(7);

    // for (Integer i : queue) {
    // System.out.print(i + " ");
    // }
    // System.out.println();
    // System.out.println(queue.size());
    // queue.delete(3);
    // System.out.println(queue.dequeue());

    // for (Integer i : queue) {
    // System.out.print(i + " ");
    // }

    // }

    public static void main(String[] args) {
        // Queue<Integer> queue = new LinklListQueue<>();
        // testQueue(queue);
        OrderedSymbolTable<Integer, String> bst = new OrderedTree<>();
        bst.put(20, "Phan Thi Hang");
        bst.put(4, "Nguyen Tung Duong");
        bst.put(1, "Hoang Duc Hai");
        bst.put(10, "Nguyen Anh Tuan");
        bst.put(8, "Luong Xuan Anh");
        bst.put(16, "Bui Tien Dung");
        bst.put(24, "Pham Thi Huyen");

        System.out.println(bst.floor(15));
        System.out.println(bst.ceiling(21));
        System.out.println(bst.size());
        // bst.deleteMin();
        // bst.deleteMax();

        System.out.println(bst.rank(10));
        System.out.println(bst.select(3));

        System.out.println(bst.size(10, 16));

        bst.delete(4);
        for (Integer i : bst.Keys()) {
            System.out.println(i + " -> " + bst.get(i));
        }

        List<Integer> list = (List<Integer>) bst.keys(1,16);
        for(Integer key : list){
            System.out.println(key + " -> " + bst.get(key));
        }
    }
}
