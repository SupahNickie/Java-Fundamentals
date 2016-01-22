// Implemented for a Trie data structure to represent an English dictionary

import java.util.HashMap;
import java.util.Set;

class TrieNode {
  private boolean isWord;
  private String text;
  private HashMap<Character, TrieNode> children;

  public TrieNode() {
    this.text = "";
    this.isWord = false;
    this.children = new HashMap<Character,TrieNode>();
  }

  public TrieNode(String text) {
    this();
    this.text = text;
  }

  public TrieNode getChild(Character c) { return children.get(c); }
  public String getText() { return this.text; }
  public void setText(String word) { this.text = word; }

  public void setIsWordNode(boolean b) {
    this.isWord = b;
  }

  public boolean isWordNode() {
    return this.isWord;
  }

  public Set<Character> getValidNextChars() {
    return this.children.keySet();
  }

  public TrieNode insert(Character c) {
    if (children.containsKey(c)) {
      return null;
    }

    TrieNode next = new TrieNode(this.text + c.toString());
    children.put(c, next);
    return next;
  }
}

