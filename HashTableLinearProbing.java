class HashTableLinearProbing {
    private String[] table;
    private int size;
    private int comparisons; //Counter

    public HashTableLinearProbing(int capacity) {
        table = new String[capacity];
        size = capacity;
        comparisons = 0;
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
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = key;
    }

    public boolean search(String key, int multiplier) {
        comparisons = 0; // Reset counter
        int index = hash(key, multiplier);
        while (table[index] != null) {
            comparisons++;
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % size;
        }
        return false;
    }

    public int getComparisons() {
        return comparisons;
    }
}
