public class LinkedListDeque<T> {
    DoublyLinkedList sentinal;
    int size = 0;

    public LinkedListDeque(){
        sentinal=new DoublyLinkedList(null,null,null);
    }
    public LinkedListDeque(T item) {
        sentinal = new DoublyLinkedList(null, item, null);
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
        if (sentinal.next != null) {
            list.next = sentinal.next;
            (sentinal.next).prev = list;
            sentinal.next = list;
            list.prev = sentinal;
            size += 1;
        } else {
            sentinal.next = list;
            list.prev = sentinal;
            list.next=sentinal;
            sentinal.prev=list;

            size += 1;
        }
    }

    public void addLast(T item) {
        var list = new DoublyLinkedList(null, item, null);
        if (sentinal.next != null) {
            list.prev=sentinal.prev;
            sentinal.prev.next=list;
            sentinal.prev=list;
            list.next=sentinal;
            size += 1;
        } else {
            sentinal.next = list;
            list.prev = sentinal;
            list.next=sentinal;
            sentinal.prev=list;

            size += 1;
        }
    }

    public boolean isEmpty() {
        return sentinal.next == null;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("The deque is empty");
        } else {
            DoublyLinkedList p = sentinal.next;
            for (int i = 0; i < size; i++) {
                System.out.print(" " + p.item);
                p = p.next;
            }
        }
    }

    public T removeFirst() {
        if (sentinal.next != null) {
            T temp = sentinal.next.item;
            sentinal.next = sentinal.next.next;
            (sentinal.next).prev = sentinal;
            size -= 1;
            return temp;
        } else {
            System.out.println("The deque is empty, removeFirst() failed");
            return null;
        }

    }

    public T removeLast() {
        if (sentinal.next != null) {
            T temp = sentinal.prev.item;
            sentinal.prev.next=null;
            sentinal.prev=sentinal.prev.prev;
            (sentinal.prev).next=sentinal;
            size-=1;
            return temp;
        } else {
            System.out.println("The deque is empty, removeLast() failed");
            return null;
        }
    }

    public T get(int index){
        if(index>size){
            System.out.println("Index is greater than size," +
                    "please enter a suitable index");
            return null;
        }

        DoublyLinkedList p=sentinal.next;
        for(int i=0;i<index;i++){
            p=p.next;
        }
        return p.item;

    }

}
