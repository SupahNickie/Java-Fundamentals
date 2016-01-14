public class BinarySearchTree<E extends Comparable<? super E>> {
  TreeNode<E> root;

  public TreeNode<E> search(E value) {
    TreeNode<E> curr = root;
    int comp;
    while (curr != null) {
      comp = curr.visit().compareTo(value);
      if (comp < 0) {
        curr = curr.getRightChild();
      } else if (comp > 0) {
        curr = curr.getLeftChild();
      } else {
        return curr;
      }
    }
    return null;
  }

  public boolean contains(E value) {
    if (search(value) != null) {
      return true;
    } else {
      return false;
    }
  }
}
