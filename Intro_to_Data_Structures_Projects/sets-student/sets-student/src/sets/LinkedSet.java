package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
	int count = 0;
    for(E item : this) {count++;}
    return count;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
    for(E item : this) {
    	if(item.equals(o)) {return true;}
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
    for(E item : this) {if(!that.contains(item)) return false;}
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    for(E item : that) {if(!this.contains(item)) return false;}
    return true;
  }

  @Override
  public Set<E> adjoin(E e) {
    return contains(e) ? new LinkedSet<E>(head) : new LinkedSet<E>(new LinkedNode<E>(e, head));
  }

  @Override
  public Set<E> union(Set<E> that) {
	LinkedNode<E> unionSetHead = null;
    for(E item : this) {unionSetHead = new LinkedNode<E>(item, unionSetHead);}
    for(E item : that) {if(!contains(item)) unionSetHead = new LinkedNode<E>(item, unionSetHead);}
    return new LinkedSet<E>(unionSetHead);
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	  LinkedNode<E> intersectSetHead = null;
	for(E item : that) {if(contains(item)) intersectSetHead = new LinkedNode<E>(item, intersectSetHead);}
    return new LinkedSet<E>(intersectSetHead);
  }

  @Override
  public Set<E> subtract(Set<E> that) {
	LinkedNode<E> subtractSetHead = null;
	boolean inThat;
    for(E item : this) {
    	inThat = false;
    	for(E itemmie : that) {if(item.equals(itemmie)) inThat = true;}
    	if(!inThat) {subtractSetHead = new LinkedNode<E>(item, subtractSetHead);}
    }
    return new LinkedSet<E>(subtractSetHead);
  }

  @Override
  public Set<E> remove(E e) {
	if(!contains(e)) {return new LinkedSet<E>(head);}
	LinkedNode<E> removeSetHead = null;
    for(E item : this) {if(!item.equals(e)) removeSetHead = new LinkedNode<E>(item, removeSetHead);}
    return new LinkedSet<E>(removeSetHead);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
