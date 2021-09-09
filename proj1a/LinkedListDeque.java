public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node prevNode, T elem, Node nextNode) {
            prev = prevNode;
            item = elem;
            next = nextNode;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private T recursionHelper(int index, Node pointer) {
        if (index == 0) {
            return pointer.item;
        }

        return recursionHelper(index - 1, pointer.next);
    }

    public T getRecursive(int index) {
        Node pointer = sentinel.next;

        return recursionHelper(index, pointer);
    }

    /** The Deque API */
    public void addFirst(T elem) {
        sentinel.next = new Node(sentinel, elem, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T elem) {
        sentinel.prev = new Node(sentinel.prev, elem, sentinel);
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

        Node pointer = sentinel.next;

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

        Node pointer = sentinel.next;

        for (int i = 0; i <= size; i++) {
            if (i == index) {
                return pointer.item;
            }

            pointer = pointer.next;
        }

        return null;
    }
}
