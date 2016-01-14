import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree<E> {
  TreeNode<E> root;

  BinaryTree() {
    this.root = null;
  }

  public void preOrder() {
    this.preOrder(root);
  }

  public void postOrder() {
    this.postOrder(root);
  }

  public void inOrder() {
    this.inOrder(root);
  }

  public void levelOrder() {
    Queue<TreeNode<E>> q = new LinkedList<TreeNode<E>>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode<E> curr = q.remove();
      if (curr != null) {
        curr.visit();
        q.add(curr.getLeftChild());
        q.add(curr.getRightChild());
      }
    }
  }

  private void preOrder(TreeNode<E> node) {
    if (node != null) {
      node.visit();
      preOrder(node.getLeftChild());
      preOrder(node.getRightChild());
    }
  }

  private void postOrder(TreeNode<E> node) {
    if (node != null) {
      preOrder(node.getLeftChild());
      preOrder(node.getRightChild());
      node.visit();
    }
  }

  private void inOrder(TreeNode<E> node) {
    if (node != null) {
      preOrder(node.getLeftChild());
      node.visit();
      preOrder(node.getRightChild());
    }
  }
}
