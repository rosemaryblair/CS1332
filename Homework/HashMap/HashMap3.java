import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Rosemary Blair
 * @userid rblair8
 * @GTID 903358318
 * @version 3.0
 */
public class HashMap3<K, V> {

    // DO NOT MODIFY OR ADD NEW GLOBAL/INSTANCE VARIABLES
    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap3() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap3(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the HashMap.
     * If an entry in the HashMap already has this key, replace the entry's
     * value with the new one passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * At the start of the method, you should check to see if the array
     * violates the max load factor. For example, let's say the array is of
     * length 5 and the current size is 3 (LF = 0.6). For this example, assume
     * that no elements are removed in between steps. If a non-duplicate key is
     * added, the size will increase to 4 (LF = 0.8), but the resize shouldn't
     * occur until the next put operation.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                "Cannot enter null data for key or value.");
        }

        if (((size + 1.0) / (double) (table.length)) > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }

        int index = Math.abs(key.hashCode() % table.length);
        V removedValue = null;

        // case 1 - place entry at index
        if (table[index] == null) {
            table[index] = new MapEntry<K, V>(key, value);
            size++;
            return null;
        }

        MapEntry<K, V> temp = table[index];
        boolean saved = false;
        int savedIndex = -1;
        int i = index;

        do {
            // case 3 - replace entry at index
            if (temp.getKey().equals(key) && !temp.isRemoved()) {
                removedValue = temp.getValue();
                temp.setValue(value);
                size++;
                return removedValue;
            // case 6 - if saved is false, save index & keep probing
            } else if (!temp.getKey().equals(key) && temp.isRemoved()) {
                if (!saved) { // only save first time
                    savedIndex = i;
                    saved = true;
                }
            } else if (temp.getKey().equals(key) && temp.isRemoved()) {
                if (saved) { // case 5 - place entry at saved index
                    table[savedIndex] = new MapEntry<K, V>(key, value);
                    size++;
                    return null;
                } else if (!saved) { // case 4 - place entry at current index
                    table[i] = new MapEntry<K, V>(key, value);
                    size++;
                    return null;
                }
            }
            i = (i + 1) % table.length;
            if (table[i] == null) {
                if (saved) { //case 2 - place entry at saved index
                    table[savedIndex] = new MapEntry<K, V>(key, value);
                    size++;
                    return null;
                } else { //case 1
                    table[i] = new MapEntry<K, V>(key, value);
                    size++;
                    return null;
                }
            }
            temp = table[i];
        } while (temp != null && i != index);
        return removedValue;
    }

    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Cannot have null as argument for remove().");
        }

        V returnValue = null;
        int index = Math.abs(key.hashCode() % table.length);
        int i = index;
        MapEntry<K, V> temp = table[index];
        boolean found = false;
        boolean continueProbing = true;

        do {
            if (temp == null) {
                continueProbing = false;
            } else {
                if (temp.isRemoved()) {
                    if (temp.getKey().equals(key)) {
                        continueProbing = false;
                    }
                } else if (!temp.isRemoved()) {
                    if (temp.getKey().equals(key)) {
                        returnValue = temp.getValue();
                        temp.setRemoved(true);
                        size--;
                        found = true;
                    }
                }
                i = (i + 1) % table.length;
                temp = table[i];
                if (i == index) {
                    continueProbing = false;
                }
            }
        } while (!found && continueProbing);
        if (found) {
            return returnValue;
        } else {
            throw new java.util.NoSuchElementException(
                "Cannot remove - key does not exist in map.");
        }
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Cannot have null as argument in get().");
        }

        int index = Math.abs(key.hashCode() % table.length);
        int i = index;
        MapEntry<K, V> temp = table[index];
        boolean continueProbing = true;

        do {
            if (temp == null) {
                continueProbing = false;
            } else if (temp.getKey().equals(key) && !temp.isRemoved()) {
                return temp.getValue();
            } else if (temp.isRemoved() && temp.getKey().equals(key)) {
                continueProbing = false;
            }
            i = (i + 1) % table.length;
            temp = table[i];
            if (i == index) {
                continueProbing = false;
            }
        } while (continueProbing);
        throw new java.util.NoSuchElementException(
            "Cannot get entry - key does not exist in map.");
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Cannot have null as argument for containsKey().");
        }

        int index = Math.abs(key.hashCode() % table.length);
        int i = index;
        MapEntry<K, V> temp = table[index];

        do {
            if (temp == null) {
                return false;
            } else if (temp.getKey().equals(key) && !temp.isRemoved()) {
                return true;
            }
            i = (i + 1) % table.length;
            temp = table[i];
        } while (i != index);
        return false;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     *
     * @return set of keys in this map
     */
    public Set<K> keySet() {

    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use {@code java.util.ArrayList} or {@code java.util.LinkedList}.
     *
     * You should iterate over the table in order of increasing index and add 
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {

    }

    /**
     * Resize the backing table to {@code length}.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Remember that you cannot just simply copy the entries over to the new
     * array.
     *
     * Also, since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't need to check for duplicates.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is non-positive or less than
     * the number of items in the hash map.
     */
    public void resizeBackingTable(int length) {

    }

    /**
     * Clears the table and resets it to the default length.
     */
    public void clear() {
        table = new (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }
    
    /**
     * Returns the number of elements in the map.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return number of elements in the HashMap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE. IT IS FOR TESTING ONLY.
     *
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

}