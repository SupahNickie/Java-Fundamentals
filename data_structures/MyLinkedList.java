import java.util.AbstractList;

public class MyLinkedList<E> {
  private LLNode<E> head;
  private LLNode<E> tail;
  private int size;

  public MyLinkedList( ) {
    this.size = 0;
    this.head = new LLNode<E>(null);
    this.tail = new LLNode<E>(null);
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  public int size() {
    return this.size;
  }

  public E get(int ind) throws IndexOutOfBoundsException {
    return getElement(ind).data;
  }

  public LLNode<E> getElement(int ind) throws IndexOutOfBoundsException {
    if (ind < 0 || (ind > size() - 1)) {
      throw new IndexOutOfBoundsException("MyLinkedList 'get' index must be > -1 and less than the size of the list");
    }

    LLNode<E> current = null;
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

  public boolean add(E data) throws NullPointerException {
    if (data != null) {
      LLNode<E> current = new LLNode<E>(data);
      current.prev = this.tail.prev;
      current.next = this.tail;
      this.tail.prev.next = current;
      this.tail.prev = current;
      this.size++;
      return true;
    } else {
      throw new NullPointerException("Can't add null to the List");
    }
  }

  public void add(int ind, E data) throws IndexOutOfBoundsException, NullPointerException {
    if (data != null) {
      if (size() == 0) {
        add(data);
      } else {
        if (ind < 0 || (ind > size() - 1)) {
          throw new IndexOutOfBoundsException("MyLinkedList 'get' index must be > -1 and less than the size of the list");
        }

        if (size() > 0) {
          LLNode<E> newNode = new LLNode<E>(data);
          LLNode<E> current = getElement(ind);
          newNode.prev = current.prev;
          newNode.next = current;
          current.prev.next = newNode;
          current.prev = newNode;
          this.size++;
        }
      }
    } else {
      throw new NullPointerException("Can't add null elements to the List");
    }
  }

  public E remove(int ind) throws IndexOutOfBoundsException {
    if (ind < 0 || size() < 1 || (ind > size() - 1)) {
      throw new IndexOutOfBoundsException("MyLinkedList 'get' index must be > -1 and less than the size of the list");
    }

    LLNode<E> current = getElement(ind);
    current.prev.next = current.next;
    current.next.prev = current.prev;
    current.prev = null;
    current.next = null;
    this.size--;
    return current.data;
  }

  public E set(int ind, E data) throws IndexOutOfBoundsException, NullPointerException
  {
    if (data != null) {
      if (ind < 0 || size() < 1 || (ind > size() - 1)) {
        throw new IndexOutOfBoundsException("MyLinkedList 'get' index must be > -1 and less than the size of the list");
      }

      LLNode<E> current = getElement(ind);
      current.data = data;
      return data;
    } else {
      throw new NullPointerException("Can't set anything to null");
    }
  }
}
