public class LinkedListDeque<T> {

    private LinkedList sentinel;
    private int size;

    public class LinkedList {
        private T item;
        private LinkedList next;
        private LinkedList prev;

        public LinkedList(T elem, LinkedList nextList, LinkedList prevList) {
            item = elem;
            next = nextList;
            prev = prevList;
        }
    }

    public LinkedListDeque() {
        sentinel = new LinkedList(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public T getRecursive(int index) {
        if (index == 0) {
            return sentinel.item;
        }

        return getRecursive(index - 1);
    }

    /** The Deque API */
    public void addFirst(T elem) {
        sentinel.next = new LinkedList(elem, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T elem) {
        sentinel.prev = new LinkedList(elem, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

        LinkedList pointer = sentinel.next;

        for (int i = 0; i < size; i++) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removedItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removedItem;
    }

    public T get(int index) {

        LinkedList pointer = sentinel.next;

        for (int i = 0; i <= size; i++) {
            if (i == index) {
                return pointer.item;
            }

            pointer = pointer.next;
        }

        return null;
    }
}
