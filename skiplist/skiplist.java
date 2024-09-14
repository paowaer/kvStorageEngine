import java.util.*;

public class SkipList<K extends Comparable<K>, V> {
    
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
    
    /*
    the SkipList attributes
    */
    
    public static final int MAX_LEVEL = 32; // the maximum level that could reach
    private Node<K, V> header; // the head node
    private int nodeCount; // recording the numbers of the nodes
    private int skipListLevel; // recording the current level 
    
    // the construct method
    public SkipList() {
        this.header = new Node<>(null, null, MAX_LEVEL);
        this.nodeCount = 0;
        this.skipListLevel = 0;
    }
    
    /**
     * Method to create a Node in the SkipList class
     * @param key The key of the node to be inserted
     * @param value The value of the node to be inserted
     * @param level The level where the node is to be inserted
     * @return Returns the created node
     */
    private Node<K, V> createNode(K key, V value, int level) {
        return new Node<>(key, value, level);
    }
    
    
    private static int generateRandomLevel() {
        int level = 1;
        Random rd = new Random();
        while (rd.nextInt(2) == 1) {
            // 50% of possibility
            level++;
        }
        return Math.min(level, MAX_LEVEL);
    }
    
    public int size() {
        return this.nodeCount;
    }
    
    /**
     * Inserts a key-value pair into the skip list. If a node with the same key already exists in the skip list,
     * it updates the value of that node.
     * @param key The key of the node to be inserted
     * @param value The value of the node to be inserted
     * @return Returns the result of the insertion. Returns true if the insertion is successful, false otherwise.
     */
 
    public synchronized boolean insertNode(K key, V value) {
        Node<K, V> current = this.header;
        ArrayList<Node<K, V>> update = new ArrayList<>(Collections.nCopies(MAX_LEVEL + 1, null));
        
        for (int i = this.skipListLevel; i >= 0; i--) {
            while (current.forwards.get(i) != null && current.forwards.get(i).getKey().compareTo(key) < 0) {
                current = current.forwards.get(0);
            }
            update.set(i, current);
        }
        
        current = current.forwards.get(0);
        
        if (current != null && current.getKey().compareTo(key) == 0) { // if it already exisit
            // just update the val;
            current.setValue(value);
            return true;
        }
        
        int randomLevel = generateRandomLevel();
        
        if (current == null || current.getKey().compareTo(key) != 0) {

            if (randomLevel > skipListLevel) {
                for (int i = skipListLevel + 1; i < randomLevel + 1; i++) {
                    update.set(i, header);
                }
                skipListLevel = randomLevel;  // update the level
            }

            Node<K, V> insertNode = createNode(key, value, randomLevel);

            // update the pointer
            for (int i = 0; i <= randomLevel; i++) {
                insertNode.forwards.set(i, update.get(i).forwards.get(i));
                update.get(i).forwards.set(i, insertNode);
            }
            nodeCount++;
            return true;
        }
        return false;
        
    }
    
    
    public boolean searchNode(K key) {
        // initial a node to store the header
        Node<K, V> current = this.header;
        // search from the top layer
        for (int i = this.skipListLevel; i >= 0; i--) {
            // the operation of going down
            while (current.forwards.get(i) != null && current.forwards.get(i).getKey().compareTo(key) < 0) {
                current = current.forwards.get(i);
            }
        }
        // the operation of traverse the current layer
        current = current.forwards.get(0);
        
        return current != null && current.getKey().compareTo(key) == 0;
    }
    
    
    public static void main(String[] args) {
        SkipList<Integer, Integer> skiplist = new SkipList<>();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();
            if (skiplist.insertNode(key, value)) {
                System.out.println("Insert Success");
            } else {
                System.out.println("Insert Failed");
            }
        }

        for (int i = 0; i < M; i++) {
            int key = scanner.nextInt();
            if (skiplist.searchNode(key)) {
                System.out.println("Search Success");
            } else {
                System.out.println("Search Failed");
            }
        }
        
        scanner.close();
    } 
}