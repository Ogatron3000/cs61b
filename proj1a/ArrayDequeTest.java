/** Performs some basic array tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out get checks. */
    public static boolean checkGet(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst("front");

        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3, ad1.size()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        // should be empty
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    /** Adds more items than array can originally take, thus making array resize. */
    public static void resizeTest() {

        System.out.println("Running resize test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(42);
        ad1.addFirst(3);
        ad1.addLast(22);
        ad1.addFirst(11);
        ad1.addFirst(2);
        ad1.addLast(44);
        ad1.addFirst(13);
        ad1.addLast(63);

        passed = checkSize(8, ad1.size()) && passed;

        ad1.addLast(7);

        passed = checkSize(9, ad1.size()) && passed;

        ad1.addFirst(77);

        System.out.println("Printing out deque: ");
        System.out.println("Expected:");
        System.out.println("77 13 2 11 3 42 22 44 63 7");
        ad1.printDeque();

        System.out.println("");
        System.out.println("");

        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeFirst();

        System.out.println("Printing out deque: ");
        System.out.println("Expected:");
        System.out.println("2 11 3 42 22 44");
        ad1.printDeque();

        printTestStatus(passed);
    }


    public static void getTest() {
        System.out.println("Running get test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        for (int i = 0; i < 8; i++) {
            ad1.addLast(i);
        }

        boolean passed = checkGet(0, ad1.get(0));
        passed = checkGet(3, ad1.get(3)) && passed;
        passed = checkGet(7, ad1.get(7)) && passed;

        printTestStatus(passed);
    }

    public static void desizeTest() {
        System.out.println("Running get test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        for (int i = 1; i <= 32; i++) {
            ad1.addFirst(i);
        }

        for (int i = 1; i <= 8; i++) {
            ad1.removeFirst();
        }

        for (int i = 1; i <= 8; i++) {
            ad1.removeLast();
        }

        boolean passed = checkGet(24, ad1.get(0));
        passed = checkGet(23, ad1.get(1)) && passed;
        passed = checkGet(22, ad1.get(2)) && passed;

//        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        resizeTest();
        getTest();
        desizeTest();
    }
}
