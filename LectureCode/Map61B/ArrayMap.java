package Map61B;

import org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * An array based implementation of the Map61B class.
 */
public class ArrayMap<K, V> implements Map61B<K, V> {
    ArrayList<K> keys;
    ArrayList<V> values;
    private int index = 0;
    private int size = 0;
    private int capacity = 100;

    public ArrayMap() {
        keys = new ArrayList<>(capacity);
        values = new ArrayList<>(capacity);
    }


    /**
     * Returns the index of the given key if it exists,
     * -1 otherwise.
     */
    private int keyIndex(K key) {

        for (int i = 0; i < size(); i++) {
            if (keys.get(i).equals(key)) {
                return i;
            }
        }

        return -1;
    }


    public boolean containsKey(K key) {
        if(keys.contains(key)){
            return true;
        }

        return false;
    }

    public void put(K key, V value) {
        if (index < capacity) {
            keys.add(index, key);
            values.add(index, value);
            index += 1;
            size += 1;
        } else {
            //TODO: array resize
        }
    }

    public V get(K key) {
        for (int i = 0; i < size(); i++) {
            if (keys.get(i).equals(key)) {
                return values.get(i);
            }
        }

        System.out.println("Value not found");
        return null;
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        return keys;
    }

    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertEquals(Optional.of(expected), am.get(2));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        System.out.println(m.get("horse"));
    }
}