package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
	
	protected StudentArrayHeap(Comparator<P> comparator) {super(comparator);}

	public int getLeftChildOf(int index) throws IndexOutOfBoundsException {
		if(index < 0) throw new IndexOutOfBoundsException();
		return index*2 + 1;
	}
	
	public int getRightChildOf(int index) throws IndexOutOfBoundsException {
		if(index < 0) throw new IndexOutOfBoundsException();
		return index*2 + 2;
	}
	
	public int getParentOf(int index) throws IndexOutOfBoundsException {
		if(index < 1) throw new IndexOutOfBoundsException();
		return (index-1) / 2;
	}
	
	protected void bubbleUp(int index) {
		if(index > 0 && 
				comparator.compare(heap.get(index).getPriority(), heap.get(getParentOf(index)).getPriority()) > 0) {
			
			Entry<P, V> temp = heap.get(index);
			heap.set(index, heap.get(getParentOf(index)));
			heap.set(getParentOf(index), temp);
			
			bubbleUp(getParentOf(index));
		}
		
	}
	
	protected void bubbleDown(int index) {
		int left = getLeftChildOf(index);
		int right = getRightChildOf(index);
		int bigger;
		if(left < heap.size() && right < heap.size()) {
			bigger = (comparator.compare(heap.get(left).getPriority(), heap.get(right).getPriority()) > 0) ? left : right;
		} else if(left < heap.size()) {
			bigger = left;
		} else if(right < heap.size()) {
			bigger = right;
		} else return;
		
		//compare parent to bigger child and make switch
		if(comparator.compare(heap.get(index).getPriority(), heap.get(bigger).getPriority()) < 0) {
			Entry<P, V> temp = heap.get(index);
			heap.set(index, heap.get(bigger));
			heap.set(bigger, temp);
			
			bubbleDown(bigger);
		}
		
	}
	
}

