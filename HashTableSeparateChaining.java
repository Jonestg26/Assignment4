import java.util.LinkedList;

class HashTableSeparateChaining {
    private LinkedList<String>[] table;
    private int size;
    private int comparisons; // Counter

    public HashTableSeparateChaining(int capacity) {
        table = new LinkedList[capacity];
        size = capacity;
        comparisons = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public int hash(String key, int multiplier) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * multiplier + key.charAt(i)) % size;
        }
        return hash;
    }

    public void insert(String key, int multiplier) {
        int index = hash(key, multiplier);
        if (!table[index].contains(key)) {
            table[index].add(key);
        }
    }

    public boolean search(String key, int multiplier) {
        comparisons = 0; // Reset counter
        int index = hash(key, multiplier);
        for (String word : table[index]) {
            comparisons++;
            if (word.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int getComparisons() {
        return comparisons;
    }
}
