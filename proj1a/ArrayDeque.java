public class ArrayDeque<T> {
    int size;
    int nextFirst;
    T[] items;
    int nextLast;
    final double USAGE_FACTOR = 0.25;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        items = (T[]) new Object[8];
        nextLast = 1;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;

        if (nextFirst != 0) {
            nextFirst -= 1;
        } else {
            nextFirst = items.length - 1;
        }

        size += 1;
        resize();
    }

    public void addLast(T item) {
        items[nextLast] = item;
        if (nextLast != items.length - 1) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }
        size += 1;
        resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        int count = size;


        while (count>0) {
            if (i > items.length - 1) {
                i = 0;
            }
            System.out.print(" " + items[i]);
            i+=1;
            count-=1;
        }

    }

    public T removeFirst() {
        T temp = items[nextFirst + 1];
        nextFirst += 1;
        size -= 1;
        resize();
        return temp;
    }

    public T removeLast() {
        T temp = items[nextLast - 1];
        nextLast -= 1;
        size -= 1;
        resize();
        return temp;
    }

    public T get(int index) {
        return items[index];
    }

    public boolean needExtend() {
        return size + 2 == items.length;
        //size+2 refer to the data and two pointer
    }

    public boolean needReduce() {
        return size + 2 < items.length * USAGE_FACTOR;
    }

    /*
    Java itself comes with a function to copy an array,
    I didn't use it because I was worried that
    it would affect my score
    */

    //needed to be fixed, the value of nextFirst and nextLast
    //the data position after extending
    public void arrayExtend(T[] items) {
        int extendedLength = items.length * 2;
        T[] extendedArray = (T[]) new Object[extendedLength];
        if(nextLast+1==nextFirst){
            for(int i=0;i<nextLast;i++){
                extendedArray[i]=items[i];
            }

            int numberBetweenNextFirstAndTheEndOfArray=items.length-2-nextLast;
            nextFirst+=items.length;
            for(int i=0;i<numberBetweenNextFirstAndTheEndOfArray;i++){
                extendedArray[nextFirst+1+i]=items[nextFirst- items.length+1+i];
            }
        }

        for (int i = 0; i < items.length; i++) {
            extendedArray[i] = items[i];
        }
        items = extendedArray;
        //bug? return extendedArray?
    }


    public void arrayReduce(T[] items) {
        int reduceLength = items.length / 2;
        T[] reducedArray = (T[]) new Object[reduceLength];
        for (int i = 0; i < reducedArray.length; i++) {
            reducedArray[i] = items[i];
        }
        items = reducedArray;
    }

    public void resize() {
        if (items.length >= 16) {

            if (needExtend()) {
                arrayExtend(items);
            }
            if (needReduce()) {
                arrayReduce(items);
            }

        } else {
            if (needExtend()) {
                arrayExtend(items);
            }
        }

    }

}
