package base;

import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isEndOfWord;
    Word word;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

}
