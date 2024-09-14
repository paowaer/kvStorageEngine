import java.util.*;

public class skiplist<K extends Comparable<K>, V> {
    
    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        int level;
        ArrayList<Node<K, V>> forwards;
        
        Node(K key,V value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.forwards = new ArrayList<>(Collections.nCopies(level + 1, null));
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        int value = sc.nextInt();
        int level = sc.nextInt();
        Node<Integer, Integer> node = new Node<>(key, value, level);
        System.out.println(node.getKey() + " " + node.getValue());
        sc.close();
    } 
}