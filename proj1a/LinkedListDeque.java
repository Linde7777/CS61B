public class LinkedListDeque<T> {
    DoublyLinkedList sentinel;
    int size = 0;

    public LinkedListDeque() {
        sentinel = new DoublyLinkedList(null, null, null);
    }

    class DoublyLinkedList {
        DoublyLinkedList prev;
        T item;
        DoublyLinkedList next;

        public DoublyLinkedList(DoublyLinkedList prev,
                                T item,
                                DoublyLinkedList next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }

    }

    public void addFirst(T item) {
        var list = new DoublyLinkedList(null, item, null);
        if (sentinel.next != null) {
            list.next = sentinel.next;
            (sentinel.next).prev = list;
            sentinel.next = list;
            list.prev = sentinel;
            size += 1;
        } else {
            sentinel.next = list;
            list.prev = sentinel;
            list.next = sentinel;
            sentinel.prev = list;

            size += 1;
        }
    }

    public void addLast(T item) {
        var list = new DoublyLinkedList(null, item, null);
        if (sentinel.next != null) {
            list.prev = sentinel.prev;
            sentinel.prev.next = list;
            sentinel.prev = list;
            list.next = sentinel;
            size += 1;
        } else {
            sentinel.next = list;
            list.prev = sentinel;
            list.next = sentinel;
            sentinel.prev = list;

            size += 1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("The deque is empty");
        } else {
            DoublyLinkedList p = sentinel.next;
            for (int i = 0; i < size; i++) {
                System.out.print(" " + p.item);
                p = p.next;
            }
        }
    }

    public T removeFirst() {
        if (sentinel.next != null) {
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            (sentinel.next).prev = sentinel;
            size -= 1;
            return temp;
        } else {
            System.out.println("The deque is empty, removeFirst() failed");
            return null;
        }

    }

    public T removeLast() {
        if (sentinel.next != null) {
            T temp = sentinel.prev.item;
            sentinel.prev.next = null;
            sentinel.prev = sentinel.prev.prev;
            (sentinel.prev).next = sentinel;
            size -= 1;
            return temp;
        } else {
            System.out.println("The deque is empty, removeLast() failed");
            return null;
        }
    }

    public T get(int index) {
        if (index > size) {
            System.out.println("Index is greater than size," +
                    "please enter a suitable index");
            return null;
        }

        DoublyLinkedList p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;

    }

    //TO DO: need to be implemented
    public T getRecursive(int index) {
        if (index == 0) {
            return sentinel.next.item;
        } else {

            index -= 1;
            return getRecursive(index);
        }
    }

}
