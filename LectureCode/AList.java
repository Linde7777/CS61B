/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int[] items;
    private int size;
    final int RFactor = 2;

    public AList() {
        items=new int[100];
        size=0;
    }

    public void addFirst(int x){
        if(size==items.length){
            resize(RFactor*size);
        }

        int index=size-1;
        for(int i=index;i>=0;i--){
            items[index+1]=items[index];
        }

        items[0]=x;
    }

    private void resize(int capacity) {
        int[] a=new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items=a;
    }

    public void addLast(int x) {
        if(size==items.length){
            resize(RFactor*size);
        }
        size++;
        items[size] = x;
    }

    
    public int getLast() {
        return items[size];        
    }

    public int get(int i) {
        return items[i];        
    }

    public int size() {
        return size;        
    }

    public int removeLast() {
        int x=getLast();
        size--;
        //Set items[size]=0 is unnecessary
        return x;
    }

    
} 