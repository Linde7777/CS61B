import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private static final int R = 128;

    private class Node {
        boolean exists;
        Node[] links;

        public Node() {
            links = new Node[R];
            exists = false;
        }
    }

    private Node root = new Node();

    public void put(String key) {
        put(root, key, 0);
    }

    private Node put(Node x, String key, int index) {
        if (x == null) {
            x = new Node();
        }

        if (index == key.length()) {
            x.exists = true;
            return x;
        }

        char c = key.charAt(index);
        x.links[c] = put(x.links[c], key, index + 1);

        return x;
    }

    public boolean findKey(String key) {
        return findKey(root, key, 0);
    }

    private boolean findKey(Node x, String key, int index) {
        if (index + 1 == key.length() && x.exists) {
            return true;
        }

        char c = key.charAt(index);
        if (x.links[c] != null) {
            return findKey(x.links[c], key, index + 1);
        }

        return false;
    }
}
