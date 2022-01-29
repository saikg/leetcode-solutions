package io.github.saikg.leetcode.s211;

public class WordDictionary {
    Trie trie;

    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    static class Trie {

        Node root;

        Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                int offset = ch - 'a';
                if (curr.children[offset] == null) {
                    curr.children[offset] = new Node();
                }
                curr = curr.children[offset];
            }
            curr.isTerminal = true;
        }

        boolean search(String word) {
            return search(root, word, 0);
        }

        boolean search(Node node, String word, int idx) {
            if (node == null) {
                return false;
            }

            if (idx == word.length()) {
                return node.isTerminal;
            }

            char ch = word.charAt(idx);
            if (ch != '.') {
                int offset = ch - 'a';
                return search(node.children[offset], word, idx+1);
            }

            for (int i = 0; i < 26; i++) {
                if (search(node.children[i], word, idx+1)) {
                    return true;
                }
            }
            return false;
        }

        static class Node {
            boolean isTerminal;
            Node[] children;

            Node() {
                isTerminal = false;
                children = new Node[26];
            }
        }
    }
}