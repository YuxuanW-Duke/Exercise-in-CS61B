public class Palindrome {


    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }


    public boolean isPalindrome(String word) {
        Deque<Character> wordList = wordToDeque(word);
        if (wordList.size() == 1) {
            return true;
        }
        int halfIndex = 1;
        while (halfIndex <= wordList.size()/2) {
            if (wordList.get(halfIndex) != wordList.get(wordList.size() - halfIndex + 1)) {
                return false;
            }
            halfIndex++;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);
        if (wordList.size() == 1) {
            return true;
        }
        int halfIndex = 1;
        while (halfIndex <= wordList.size()/2) {
            if (!cc.equalChars(wordList.get(halfIndex), wordList.get(wordList.size() - halfIndex + 1))) {
                return false;
            }
            halfIndex++;
        }
        return true;
    }
}
