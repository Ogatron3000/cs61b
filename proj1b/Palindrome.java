public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private String dequeToWord(Deque<Character> d) {
        String actual = "";
        for (int i = 0; i < d.size(); i++) {
            actual += d.get(i);
        }
        return actual;
    }

    public boolean isPalindrome(String word) {
        /** Non-deque solution. */
        /* for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true; */

        /** Deque iterative solution. */
        /* Deque d = wordToDeque(word);
        for (int i = 0; i < d.size() / 2; i++) {
            if (d.get(i) != d.get(d.size() - 1 - i)) {
                return false;
            }
        }
        return true; */

        /** Deque recursive solution. */
        Deque<Character> d = wordToDeque(word);
        if (d.size() < 2) {
            return true;
        } else if (d.removeFirst() != d.removeLast()) {
            return false;
        } else {
            return isPalindrome(dequeToWord(d));
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        if (d.size() < 2) {
            return true;
        } else if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
            return false;
        } else {
            return isPalindrome(dequeToWord(d), cc);
        }
    }
}
