package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODID
		
		if(t == null) throw new NullPointerException();
		
		return (get(t) == null) ? false : true;
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODID
		
		if(t == null) throw new NullPointerException();
		
		BSTNode<T> traverser = root;
		while(traverser != null) {
			if(traverser.getData().compareTo(t) > 0) {
				traverser = traverser.getLeft();
			}
			else if(traverser.getData().compareTo(t) < 0) {
				traverser = traverser.getRight();
			}
			else if(traverser.getData().equals(t)) {return traverser.getData();}
		}
		
		return null;
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODID
		
		if(root == null) return null;
		
		BSTNode<T> traverser = root;
		while(traverser.getLeft() != null) {
			traverser = traverser.getLeft();
		}
		
		return traverser.getData();
	}


	@Override
	public T getMaximum() {
		// TODID
		
		if(root == null) return null;
		
		BSTNode<T> traverser = root;
		while(traverser.getRight() != null) {
			traverser = traverser.getRight();
		}
		
		return traverser.getData();
	}


	@Override
	public int height() {
		// TODID
		
		if(root == null) return -1;
		
		return recursiveHeight(root);
	}
	
	private int recursiveHeight(BSTNode<T> node) {
		if(node == null) return -1;
		if((node.getLeft() == null) && (node.getRight() == null)) return 0;
		
		int left = recursiveHeight(node.getLeft());
		int right = recursiveHeight(node.getRight());
		return (left > right) ? left + 1 : right + 1;	
	}


	public Iterator<T> preorderIterator() {
		// TODID
		
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODID

		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}


	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODID
		
		if(other == null) throw new NullPointerException();
		
		BSTNode<T> traverserA = root;
		BSTNode<T> traverserB = other.getRoot();
		
		return recursiveEquals(traverserA, traverserB);
	}
	
	private boolean recursiveEquals(BSTNode<T> nodeA, BSTNode<T> nodeB) {
		if(nodeA == null && nodeB == null) return true;
		if(nodeA == null || nodeB == null) return false;
		
		if(!(nodeA.getData().equals(nodeB.getData()))) return false;
		
		return recursiveEquals(nodeA.getLeft(), nodeB.getLeft()) &&
				recursiveEquals(nodeA.getRight(), nodeB.getRight());
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODID
		
		if(other == null) throw new NullPointerException();
		
		Iterator<T> valuesA = inorderIterator();
		Iterator<T> valuesB = other.inorderIterator();
		
		while(valuesA.hasNext() && valuesB.hasNext()) {
			if(!(valuesA.next().equals(valuesB.next()))) return false;
		}
		
		if(valuesA.hasNext() || valuesB.hasNext()) return false;
		
		return true;
	}

	@Override
	public boolean isBalanced() {
		// TODID
		
		return (Math.pow(2, height()) <= size()) &&
				(size() <= Math.pow(2, height() + 1));
	}

	@Override
    @SuppressWarnings("unchecked")

	public void balance() {
		// TODID
		
		Iterator<T> treeArrayMaker = inorderIterator();
		
		T[] treeArray = (T[]) new Comparable[size()];
		
		int sz = size();
		for(int i = 0; i < sz; i++) {
			treeArray[i] = treeArrayMaker.next();
		}
		
		root = recursiveBalance(treeArray, 0, size() - 1);
	}

	private BSTNode<T> recursiveBalance(T[] treeArray, int lower, int upper) {
		if (lower > upper) return null;
		
		int mid = (lower + upper) / 2;
		
		BSTNode<T> node = new BSTNode<T>(
				treeArray[mid],
				recursiveBalance(treeArray, lower, mid - 1),
				recursiveBalance(treeArray, mid + 1, upper));
		
		return node;
	}

	public String visualizeTree(BSTNode<T> root) {
		
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		
		String visualization = "";
		
		for(T item : queue) {
			visualization += item + " ";
		}
		
		return visualization;
	}

	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}