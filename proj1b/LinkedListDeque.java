public class LinkedListDeque<T> implements Deque<T> {
    private DoublyLinkedList sentinel;
    private int size = 0;

    public LinkedListDeque() {
        sentinel = new DoublyLinkedList(null, null, null);
    }

    private class DoublyLinkedList {
        DoublyLinkedList prev;
        T item;
        DoublyLinkedList next;

        DoublyLinkedList(DoublyLinkedList prev,
                         T item,
                         DoublyLinkedList next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }

    }

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel || sentinel.next == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        System.out.println("Print the Deque:");
        if (isEmpty()) {
            System.out.println("The Deque is empty");
        } else {
            DoublyLinkedList p = sentinel.next;
            for (int i = 0; i < size; i++) {
                System.out.print(" " + p.item);
                p = p.next;
            }
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("removeFirst failed, the Deque is empty");
            size = 0;
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        (sentinel.next).prev = sentinel;
        size -= 1;
        return temp;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("removeLast failed, the Deque is empty");
            size = 0;
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev.next = null;
        sentinel.prev = sentinel.prev.prev;
        (sentinel.prev).next = sentinel;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            System.out.println("Index is greater than size,"
                    + "please enter a suitable index");
            return null;
        }

        DoublyLinkedList p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;

    }

    private T getRecursiveHelper(int index, DoublyLinkedList p) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(--index, p.next);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            System.out.println("Please enter non-negative number");
            return null;
        }
        if (index == 0) {
            return getRecursiveHelper(index, sentinel.next);
        } else {
            return getRecursiveHelper(index, sentinel.next);
        }
    }

}
