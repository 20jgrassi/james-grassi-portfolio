package structures;

import comparators.IntegerComparator;
//import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
	
	private StudentArrayHeap<Integer, V> qheap = new StudentArrayHeap<Integer, V>(new IntegerComparator());
	
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		if(priority == null || value == null) throw new NullPointerException();
		qheap.add(priority, value);
		return this;
	}
	
	public V dequeue() {
		if(qheap.isEmpty()) throw new IllegalStateException();
		return qheap.remove();
	}
	
	public V peek() {
		if(qheap.isEmpty()) throw new IllegalStateException();
		return qheap.peek();
	}
	
	public Iterator<Entry<Integer, V>> iterator() {return qheap.asList().iterator();}
	
	public Comparator<Integer> getComparator() {return qheap.getComparator();}
	
	public int size() {return qheap.size();}
	
	public boolean isEmpty() {return qheap.isEmpty();}
	
}
