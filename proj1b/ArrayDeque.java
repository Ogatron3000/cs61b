public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize() {
        T[] newItemsArr;
        if (size == items.length) {
            newItemsArr = (T []) new Object[items.length * 2];
        } else {
            newItemsArr = (T []) new Object[items.length / 2];
        }

        for (int i = 0, start = (nextFirst + 1) % items.length; i < size; i += 1, start = (start + 1) % items.length) {
            newItemsArr[i] = items[start];
        }

        items = newItemsArr;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** The Deque API */
    @Override
    public void addFirst(T elem) {
        if (size == items.length) {
            resize();
        }

        items[nextFirst] = elem;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T elem) {
        if (size == items.length) {
            resize();
        }

        items[nextLast] = elem;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = (nextFirst + 1) % items.length;

        for (int i = start; i != nextFirst; i = (i + 1) % items.length) {
            if (items[i] == null) {
                break;
            }
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextFirst = (nextFirst + 1) % items.length; // new next first
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize();
        }

        return removedItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nextLast = (nextLast + items.length - 1) % items.length; // new next last
        T removedItem = items[nextLast];
        items[nextLast] = null;

        size -= 1;

        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize();
        }

        return removedItem;
    }

    @Override
    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}
