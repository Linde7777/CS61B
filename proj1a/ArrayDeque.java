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

        if (nextFirst != 0 ) {
            nextFirst -= 1;
        } else {
            nextFirst = items.length - 1;
        }


        size += 1;
        //resize();

    }

    public T removeFirst() {
        int index = nextFirst + 1;
        if (index > items.length - 1) {
            index = 0;
        }
        T temp = items[index];
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        size -= 1;
        //resize();
        return temp;

    }

    public void addLast(T item) {
        items[nextLast] = item;
        if (nextLast != items.length - 1) {
            nextLast += 1;
        } else {
            nextLast = 0;
        }
        size += 1;
        //resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        int count = size;

        while (count > 0) {
            if (i > items.length - 1) {
                i = 0;
            }
            System.out.print(" " + items[i]);
            i += 1;
            count -= 1;
        }

    }

    public T removeLast() {
        int index = nextLast - 1;
        if (index == 0) {
            index = items.length - 1;
        }
        T temp = items[index];
        nextLast -= 1;
        size -= 1;
        //resize();
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
    public T[] arrayExtend(T[] items) {
        int newLength = items.length * 2;
        T[] newArr = (T[]) new Object[newLength];

        if (nextFirst > nextLast) {
            int newNextFirst = nextFirst + items.length;
            int newIndex = newNextFirst + 1;
            int originalIndex = nextFirst + 1;
            nextFirst = newNextFirst;
        }

        int newNextLast = nextLast + items.length;
        int newIndex = newNextLast - 1;
        int originalIndex = nextLast - 1;
        nextLast = newNextLast;

        int count = size;

        while (count > 0) {
            if (newIndex > newArr.length - 1) {
                newIndex = 0;
            }
            if (originalIndex > items.length - 1) {
                originalIndex = 0;
            }
            newArr[newIndex] = items[originalIndex];
            newIndex += 1;
            originalIndex += 1;
            count -= 1;
        }

        return newArr;
        //bug? return extendedArray?
    }


    public T[] arrayReduce(T[] items) {
        int newLength = items.length / 2;
        T[] newArr = (T[]) new Object[newLength];
        int newNextFirst = nextFirst - items.length / 2;
        int newIndex = newNextFirst + 1;
        int originalIndex = nextFirst + 1;
        int count = size;

        while (count > 0) {
            if (newIndex > newLength - 1) {
                newIndex = 0;
            }
            if (originalIndex > items.length - 1) {
                originalIndex = 0;
            }
            newArr[newIndex] = items[originalIndex];
            newIndex += 1;
            originalIndex += 1;
            count -= 1;
        }

        nextFirst = newNextFirst;
        return newArr;
    }

    public void resize() {
        if (items.length >= 16) {

            if (needExtend()) {
                items = arrayExtend(items);
            }
            if (needReduce()) {
                items = arrayReduce(items);
            }

        } else {
            if (needExtend()) {
                items = arrayExtend(items);
            }
        }

    }

}
