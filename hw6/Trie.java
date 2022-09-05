import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private static final int R=128;

    private class Node{
        boolean exists;
        Node[] links;

        public Node(){
            links=new Node[R];
            exists=false;
        }
    }

    private Node root=new Node();

    public void put(String key){
        put(root,key,0);
    }

    private Node put(Node x,String key,int d){
        if(x==null){
            x=new Node();
        }

        if(d==key.length()){
            x.exists=true;
            return x;
        }

        char c=key.charAt(d);
        x.links[c]=put(x.links[c],key,d+1);

        return x;
    }
}
