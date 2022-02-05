package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        first = last = fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;

        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }

        fillCount += 1;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T temp = rb[first];

        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }

        fillCount -= 1;
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return fillCount == 0;
    }

    @Override
    public boolean isFull() {
        return fillCount == capacity;
    }

    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Queue Is Empty");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator {
        int ptr;

        public QueueIterator() {
            ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr < capacity;
        }

        @Override
        public T next() {
            T temp = rb[ptr];
            ptr = ptr + 1;
            return temp;
        }
    }

}

