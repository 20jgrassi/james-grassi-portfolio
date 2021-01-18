package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {

  // TODID: define class variables here
	private LLNode<T> top = null;
	private int size = 0;

  /**
   * Remove and return the top element on this stack.
   * If stack is empty, return null (instead of throw exception)
   */
  public T pop() {
    // TODID
    if(isEmpty()) {return null;}
    LLNode<T> popTop = top;
    top = top.link; size--;
    return popTop.info;
  }

  /**
   * Return the top element of this stack (do not remove the top element).
   * If stack is empty, return null (instead of throw exception)
   */
  public T top() {
    // TODID
    if(isEmpty()) {return null;}
    return top.info;
  }

  /**
   * Return true if the stack is empty and false otherwise.
   */
  public boolean isEmpty() {
    // TODID
    return (top == null) ? true : false;
  }

  /**
   * Return the number of elements in this stack.
   */
  public int size() {
    // TODID
    return size;
  }

  /**
   * Pushes a new element to the top of this stack.
   */
  public void push(T elem) {
    // TODID
	  LLNode<T> newTop = new LLNode<T>(elem);
	  newTop.link = top;
	  top = newTop; size++;
  }

}
