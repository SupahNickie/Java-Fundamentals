// Implemented for a Trie data structure to represent an English dictionary

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Trie {
  private TrieNode root;

  Trie() {
    this.root = new TrieNode();
  }

  public int size() {
    return size(root, 0);
  }

  public int size(TrieNode curr, int total) {
    TrieNode next = null;
    for (Character c : curr.getValidNextChars()) {
      next = curr.getChild(c);
      if (next.isWordNode()) {
        total += 1;
      }
      total = size(next, total);
    }
    return total;
  }

  public boolean isWord(String word) {
    if (word.equals("")) { return false; }
    if (search(word) == null) { return false; }
    return (search(word).isWordNode());
  }

  public TrieNode search(String word) {
    TrieNode curr = this.root;
    for (Character c : word.toLowerCase().toCharArray()) {
      if (curr.getChild(c) == null) {
        return null;
      } else {
        curr = curr.getChild(c);
      }
    }
    return curr;
  }

  public void insert(String word) {
    TrieNode curr = this.root;
    for (Character c : word.toLowerCase().toCharArray()) {
      if (curr.getChild(c) == null) {
        curr.insert(c);
        curr = curr.getChild(c);
        curr.setIsWordNode(false);
      } else {
        curr = curr.getChild(c);
      }
    }
    curr.setIsWordNode(true);
    curr.setText(word.toLowerCase());
  }

  public List<String> autoComplete(String stem, int numCompletions) {
    List<String> predictions = new LinkedList<String>();
    TrieNode found = search(stem.toLowerCase());

    if (found == null) {
      return predictions;
    }

    Queue<TrieNode> queue = new LinkedList<TrieNode>();
    queue.add(found);
    while (!queue.isEmpty() && predictions.size() < numCompletions) {
      TrieNode curr = queue.remove();
      if (curr.isWordNode()) {
        predictions.add(curr.getText());
    }
      for (Character c : curr.getValidNextChars()) {
        queue.add(curr.getChild(c));
      }
    }
    return predictions;
  }

  public void printTree() {
    printNode(root);
  }

  public void printNode(TrieNode curr) {
    if (curr == null)
      return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextChars()) {
      next = curr.getChild(c);
      printNode(next);
    }
  }
}
