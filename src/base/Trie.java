package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void addAll(ArrayList<Word> temp) {
        for (Word word : temp) {
            insert(word);
        }
        System.out.println(temp.size());
    }

    public void insert(Word word) {
        TrieNode node = root;
        for (char ch : word.getSearching().toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.setWord(word);
        node.isEndOfWord = true;
    }

    public ArrayList<Word> getAllWordsWithPrefix(String prefix) {
        ArrayList<Word> wordsWithPrefix = new ArrayList<>();
        TrieNode node = root;

        // Duyệt đến nút tương ứng với tiền tố
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                // Tiền tố không tồn tại trong trie
                return wordsWithPrefix;
            }
            node = node.children.get(ch);
        }

        // Thực hiện tìm kiếm theo chiều sâu để lấy tất cả các từ với tiền tố cho trước
        getAllWordsFromNodeWithPrefix(prefix, node, wordsWithPrefix);

        return wordsWithPrefix;
    }

    private void getAllWordsFromNodeWithPrefix(String currentWord, TrieNode node, ArrayList<Word> result) {
        // Nếu đến nút lá, thêm từ vào danh sách kết quả
        if (node.isEndOfWord) {
            // Assume Word class has a method getSearching() to check the searching property
            Word word = node.getWord(); // Replace with your actual method to get Word object
            if (word != null && word.getSearching().startsWith(currentWord)) {
                result.add(word);
            }
        }

        // Duyệt qua tất cả các nút con và thực hiện tìm kiếm đệ quy
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            char nextChar = entry.getKey();
            TrieNode nextNode = entry.getValue();
            getAllWordsFromNodeWithPrefix(currentWord + nextChar, nextNode, result);
        }
    }

}
