public class ArrayDeque<T> {

    private T[] items;
    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 1;

    public ArrayDeque() {
        items = (T []) new Object[8];
    }

    private void resize() {
        T[] newItemsArr = (T []) new Object[items.length * 2];
        System.arraycopy(items, (nextFirst + 1) % items.length, newItemsArr, 0, items.length - nextFirst - 1);
        System.arraycopy(items, 0, newItemsArr, items.length - nextFirst, (nextFirst + 1) % items.length);

        items = newItemsArr;
        nextFirst = items.length - 1;
        nextLast = items.length - size + 1;
    }

    private void desize() {
        T[] newItemsArr = (T []) new Object[items.length / 2];
        System.arraycopy(items, (nextFirst + 1) % items.length, newItemsArr, 0, Math.min(items.length - nextFirst, size));
        System.arraycopy(items, 0, newItemsArr, Math.min(items.length - nextFirst, size), nextLast);
        items = newItemsArr;
        nextFirst = items.length - 1;
        nextLast = items.length - size + 1;
    }

    /** The Deque API */
    public void addFirst(T elem) {
//        if (size == items.length) {
//            resize();
//        }

        items[nextFirst] = elem;
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size += 1;
    }

    public void addLast(T elem) {
//        if (size == items.length) {
//            resize();
//        }

        items[nextLast] = elem;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = (nextFirst + 1) % items.length;

        for (int i = start; i != nextFirst; i = (i + 1) % items.length) {
            if (items[i] == null) {
                continue;
            }
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T removedItem = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;

//        if (items.length > 16 && (float) size / items.length < 0.25) {
//            desize();
//        }

        return removedItem;
    }

    public T removeLast() {
        if (items.length == 0) {
            return null;
        }

        T removedItem = items[(nextLast + items.length - 1) % items.length];
        items[(nextLast + items.length - 1) % items.length] = null;
        nextLast = (nextLast + items.length - 1) % items.length;
        size -= 1;

//        if (items.length > 16 && (float) size / items.length < 0.25) {
//            desize();
//        }

        return removedItem;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}