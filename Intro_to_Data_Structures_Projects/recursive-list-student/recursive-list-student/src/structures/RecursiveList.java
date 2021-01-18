package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveList<T> implements ListInterface<T> {
	int size = 0;
	LLNode<T> head = null;
	
	public int size() {return size;}
	
	public ListInterface<T> insertFirst(T elem) throws NullPointerException {
		if(elem == null) {throw new NullPointerException();}
		head = new LLNode<T>(elem, head);
		size++;
		return this;
	}
	
	public ListInterface<T> insertLast(T elem) throws NullPointerException {
		if(elem == null) {throw new NullPointerException();}
		if(!isEmpty()) {
			getTail(head).link = new LLNode<T>(elem);
			size++;
		} else {insertFirst(elem);}
		return this;
	}
	
	public ListInterface<T> insertAt(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		if(elem == null) {throw new NullPointerException();}
		if(index == 0) {insertFirst(elem);}
		else {
			LLNode<T> prev = getNth(head, index - 1);
			LLNode<T> next = prev.link;
			prev.link = new LLNode<T>(elem, next);
			size++;
		}
		return this;
	}
	
	public T removeFirst() throws IllegalStateException {
		if(isEmpty()) {throw new IllegalStateException();}
		T toReturn = head.info;
		head = head.link;
		size--;
		return toReturn;
	}
	
	public T removeLast() throws IllegalStateException {
		if(isEmpty()) {throw new IllegalStateException();}
		if(size == 1) {return removeFirst();}
		LLNode<T> newEnd = getNth(head, size - 2);
		T toReturn = newEnd.link.info;
		newEnd.link = null;
		size--;
		return toReturn;
	}
	
	public T removeAt(int i) throws IndexOutOfBoundsException {
		if(isEmpty() || i >= size) {throw new IndexOutOfBoundsException();}
		if(i == 0) {return removeFirst();}
		if(i == size - 1) {return removeLast();}
		LLNode<T> prev = getNth(head, i - 1);
		LLNode<T> toRemove = prev.link;
		LLNode<T> next = toRemove.link;
		prev.link = next;
		size--;
		return toRemove.info;
	}
	
	public T getFirst() throws IllegalStateException {
		if(isEmpty()) {throw new IllegalStateException();}
		return head.info;
	}
	
	public T getLast() throws IllegalStateException {
		if(isEmpty()) {throw new IllegalStateException();}
		return getTail(head).info;
	}
	
	public T get(int i) throws IndexOutOfBoundsException {
		return getNth(head, i).info;
	}
	
	public boolean remove(T elem) throws NullPointerException {
		if(elem == null) {throw new NullPointerException();}
		int index = locateElem(head, 0, elem);
		if(index != -1) {removeAt(index); return true;}
		return false;
	}
	
	public int indexOf(T elem) throws NullPointerException {
		if(elem == null) {throw new NullPointerException();}
		return locateElem(head, 0, elem);
	}
	
	public boolean isEmpty() {return size == 0;}
	
	public Iterator<T> iterator() {return new NodeIterator<T>(head);}
	
	private LLNode<T> getTail(LLNode<T> node) {return (node != null && node.link != null) ? getTail(node.link) : node;}
	
	private LLNode<T> getNth(LLNode<T> node, int n) throws IndexOutOfBoundsException {
		if(node == null || n < 0) {throw new IndexOutOfBoundsException();}
		return (n == 0) ? node : getNth(node.link, n - 1);
	}
	
	private int locateElem(LLNode<T> node, int index, T elem) {
		if(node == null) {return -1;}
		return (node.info.equals(elem)) ? index : locateElem(node.link, index + 1, elem);
	}
}

class LLNode<T> {
	public T info;
	public LLNode<T> link;
	
	public LLNode(T someInfo, LLNode<T> someLink) {
		info = someInfo;
		link = someLink;
	}
	public LLNode(T someInfo) {this(someInfo, null);}
}

class NodeIterator<T> implements Iterator<T> {
	LLNode<T> next;
	
	public NodeIterator(LLNode<T> head) {
		next = head;
	}
	
	public boolean hasNext() {return next != null;}
	
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		T toReturn = next.info;
		next = next.link;
		return toReturn;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}