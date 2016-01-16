public class TreeNode<E> {
  private E value;
  private TreeNode<E> parent;
  private TreeNode<E> leftChild;
  private TreeNode<E> rightChild;

  TreeNode(E value, TreeNode<E> parent) {
    this.value = value;
    this.parent = parent;
    this.leftChild = null;
    this.rightChild = null;
  }

  public E visit() {
    return this.value;
  }

  public TreeNode<E> addLeftChild(E val) {
    this.leftChild = new TreeNode<E>(val, this);
    return this.leftChild;
  }

  public TreeNode<E> addRightChild(E val) {
    this.rightChild = new TreeNode<E>(val, this);
    return this.rightChild;
  }

  public TreeNode<E> getParent() { return this.parent; }
  public TreeNode<E> getLeftChild() { return this.leftChild; }
  public TreeNode<E> getRightChild() { return this.rightChild; }
  public void setValue(E value) { this.value = value; }
  public void setParent(TreeNode<E> node) { this.parent = node; }
}
