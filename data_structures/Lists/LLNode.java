class LLNode<E> {
  LLNode<E> next;
  LLNode<E> prev;
  E data;

  public LLNode(E data) {
    this.data = data;
  }
}
