public class MyLinkedList<E> {
  private ListNode<E> head;
  private ListNode<E> tail;
  private int size;

  public MyLinkedList( ) {
    this.size = 0;
    this.head = new ListNode<E>(null);
    this.tail = new ListNode<E>(null);
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  public int size() { return this.size; }

  public ListNode<E> get(int ind) throws NullPointerException {
    if (ind < 0 || size() < 1) {
      throw new NullPointerException("MyLinkedList 'get' index must be > -1");
    }

    ListNode<E> current = null;
    if (ind <= (size() / 2)) {
      current = this.head;
      for (int i = 0; i <= ind; i++) {
        current = current.next;
      }
    } else {
      current = this.tail;
      for (int i = 0; i < (size() - ind); i++) {
        current = current.prev;
      }
    }

    return current;
  }

  public void set(int ind, E data) {
    ListNode<E> current = get(ind);
    current.data = data;
  }

  public void add(int ind, E data) {
    ListNode<E> current = get(ind);
    ListNode<E> newNode = new ListNode<E>(data);
    newNode.prev = current.prev;
    newNode.next = current;
    current.prev.next = newNode;
    current.prev = newNode;
    this.size++;
  }

  public void add(E data) {
    ListNode<E> current = new ListNode<E>(data);
    current.prev = this.tail.prev;
    current.next = this.tail;
    this.tail.prev.next = current;
    this.tail.prev = current;
    this.size++;
  }

  public ListNode remove(int ind) throws NullPointerException {
    if (ind < 0 || size() < 1) {
      throw new NullPointerException("MyLinkedList 'remove' index must be > -1");
    }

    ListNode<E> current = get(ind);
    current.prev.next = current.next;
    current.next.prev = current.prev;
    current.prev = null;
    current.next = null;
    this.size--;
    return current;
  }

}
