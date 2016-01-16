public class BinarySearchTree<E extends Comparable<? super E>> {
  TreeNode<E> root;

  public TreeNode<E> search(E value) {
    return search(value, root);
  }

  public void insert(E value) {
    insert(value, root);
  }

  public void delete(E value) {
    TreeNode<E> found = search(value);
    if (found != null) {
      if (found.getLeftChild() == null && found.getRightChild() == null) {
        found.setValue(null);
        found.setParent(null);
      } else if (found.getLeftChild() == null) {
        found.setValue(found.getRightChild().visit());
        found.addRightChild(null);
      } else if (found.getRightChild() == null) {
        found.setValue(found.getLeftChild().visit());
        found.addLeftChild(null);
      } else { // Both children exist
        TreeNode<E> lowestNode = findLowest(found.getRightChild());
        found.setValue(lowestNode.visit());
        if (lowestNode.getRightChild() != null) {
          lowestNode.getParent().addLeftChild(lowestNode.getRightChild().visit());
          lowestNode.addRightChild(null);
        } else {
          lowestNode.getParent().addLeftChild(null);
        }
        lowestNode.setParent(null);
      }
    } else {
      System.out.println("Node with value " + value + " not found in tree!");
    }
  }

  public boolean contains(E value) {
    if (search(value) != null) {
      return true;
    } else {
      return false;
    }
  }

  private TreeNode<E> search(E value, TreeNode<E> node) {
    int comp = node.visit().compareTo(value);
    if (comp < 0) {
      return search(value, node.getRightChild());
    } else if (comp > 0) {
      return search(value, node.getLeftChild());
    } else {
      return node;
    }
  }

  private void insert(E value, TreeNode<E> node) {
    int comp = node.visit().compareTo(value);
    if (comp < 0) {
      if (node.getRightChild() == null) {
        TreeNode<E> newNode = new TreeNode<E>(value, node);
      } else {
        insert(value, node.getRightChild());
      }
    } else if (comp > 0) {
      if (node.getLeftChild() == null) {
        TreeNode<E> newNode = new TreeNode<E>(value, node);
      } else {
        insert(value, node.getLeftChild());
      }
    } else {
      System.out.println("Node with value " + value + " already exists in BST!");
    }
  }

  private TreeNode<E> findLowest(TreeNode<E> node) {
    if (node.getLeftChild() == null) {
      return node.getLeftChild();
    } else {
      return findLowest(node.getLeftChild());
    }
  }
}
