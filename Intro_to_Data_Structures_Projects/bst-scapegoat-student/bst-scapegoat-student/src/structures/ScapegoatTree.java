package structures;

import java.util.ArrayList;
import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound = 0;


	@Override
	public void add(T element) {
		// TODID
		
		super.add(element);
		
		upperBound++;
		
		if(height() > (Math.log(upperBound) / Math.log(3.0/2.0))) {
			
			ArrayList<BSTNode<T>> pathArray = new ArrayList<BSTNode<T>>();
			
			BSTNode<T> traverser = root;
			
			boolean foundElement = false;
			while(!foundElement) {
				pathArray.add(0, traverser);
				
				if(traverser.getData().compareTo(element) > 0) {traverser = traverser.getLeft();}
				else if(traverser.getData().compareTo(element) < 0) {traverser = traverser.getRight();}
				else if(traverser.getData().equals(element)) {foundElement = true;}
			}
			
			BSTNode<T> scapegoat = null;
			BSTNode<T> goatParent = null;
			
			BSTNode<T> nodeChild = null;
			for(BSTNode<T> node : pathArray) {
				if(scapegoat != null && scapegoat.equals(nodeChild)) {
					goatParent = node;
				}
				
				if(nodeChild != null && scapegoat == null) {
					if(subtreeSize(nodeChild) / (1.0 * subtreeSize(node)) > (2.0/3.0)) {
						scapegoat = node;
					}
				}
				
				nodeChild = node;
			}
			
			BinarySearchTree<T> goatSubtree = new BinarySearchTree<T>();
			goatSubtree.root = scapegoat;
			
			goatSubtree.balance();
			
			if(goatParent == null) {root = goatSubtree.root;}
			else if(goatParent.getLeft() == null) {goatParent.setRight(goatSubtree.getRoot());}
			else if(goatParent.getRight() == null) {goatParent.setLeft(goatSubtree.getRoot());}
			else if(goatParent.getLeft() == scapegoat) {goatParent.setLeft(goatSubtree.getRoot());}
			else if(goatParent.getRight() == scapegoat) {goatParent.setRight(goatSubtree.getRoot());}
		}
	}

	@Override
	public boolean remove(T element) {
		// TODID
		
		boolean toReturn = super.remove(element);
		
		if(toReturn && upperBound > 2 * size()) {balance(); upperBound = size();}
		
		return toReturn;
	}
}
