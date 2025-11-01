import java.util.*;

class MyHashMap {
    private static class Pair {
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 1000;
    private List<Pair>[] buckets;

    public MyHashMap() {
        buckets = new List[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int hashKey = hash(key);
        if (buckets[hashKey] == null) {
            buckets[hashKey] = new LinkedList<>();
        }
        for (Pair pair : buckets[hashKey]) {
            if (pair.key == key) {
                pair.value = value;  // Update existing key
                return;
            }
        }
        buckets[hashKey].add(new Pair(key, value));  // Insert new pair
    }

    public int get(int key) {
        int hashKey = hash(key);
        List<Pair> bucket = buckets[hashKey];
        if (bucket == null) return -1;

        for (Pair pair : bucket) {
            if (pair.key == key) return pair.value;
        }
        return -1;
    }

    public void remove(int key) {
        int hashKey = hash(key);
        List<Pair> bucket = buckets[hashKey];
        if (bucket == null) return;

        Iterator<Pair> it = bucket.iterator();
        while (it.hasNext()) {
            if (it.next().key == key) {
                it.remove();
                return;
            }
        }
    }
}
