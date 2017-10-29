import java.math.*;

class AVLNode<E>{ 
	AVLNode<E> left; 
	AVLNode<E> right; 
	E x; 
	int height;
      
	AVLNode(){} 
      
	AVLNode(E x, AVLNode left, AVLNode right){ 
		this.x = x; 
		this.left = left; 
		this.right = right; 
		this.height = 1;
	} 
	
	void prettyPrint(String prevIndent, String indent, boolean isRoot)
	{
		if(isRoot)
			System.out.println(prevIndent+" "+x+" "+height);
		else
			System.out.println(prevIndent+"|\n"+prevIndent+"+----"+x+" "+height);
		
		if(left!=null)
			left.prettyPrint(indent,indent+"|    ",false);
		else
			System.out.println(indent+"|\n"+indent+"+----[NULL]");
		
		if(right!=null)
				right.prettyPrint(indent,indent+"     ",false);
		else 
			System.out.println(indent+"|\n"+indent+"+----[NULL]");
	}
} 

class AVLTree<E extends Comparable<E>>{
	AVLNode<E> root;
	AVLTree(){}
		
	AVLTree(AVLNode<E> root){
		this.root = root;
	}
	
	AVLNode<E> insert(E x, AVLNode<E> node){
		if(node == null){
			node = new AVLNode(x, null, null);
		}
		else if(x.compareTo(node.x) < 0){
			node.left = insert(x, node.left);
			System.out.println("val " +Math.abs(((node.left ==null) ? 0 : node.left.height) - ((node.right == null) ? 0 : node.right.height)));
			if(Math.abs(((node.left ==null) ? 0 : node.left.height) - ((node.right == null) ? 0 : node.right.height)) >= 2 ){
				if(x.compareTo(node.left.x) < 0){
					//~ System.out.println("sini");
					node = singleRotationWithLeftChild(node);					
					//~ System.out.println(node.x + " "+node.left.x+" "+node.right.x);
				}
				else{
					System.out.println("double rotation");
					node = doubleRotationWithLeftChild(node);
				}
			}
		}
		else{
			node.right = insert(x, node.right);
			if(Math.abs(((node.left ==null) ? 0 : node.left.height) - ((node.right == null) ? 0 : node.right.height)) >= 2){
				if(x.compareTo(node.right.x) > 0){
					node = singleRotationWithRightChild(node);
				}
				else{
					node = doubleRotationWithRightChild(node);
				}
			}
		}
		
		if(node.left == null && node.right == null) node.height = 1;
		else node.height = Math.max((node.left ==null) ? 0 : node.left.height, (node.right == null) ? 0 : node.right.height) + 1;
		return node;
	}
	
	int updateHeight(AVLNode<E> node){
		if(node.left == null && node.right == null) return 1;
		return node.height = Math.max((node.left == null) ? 0 : node.left.height, (node.right == null) ? 0 : node.right.height) + 1;
	}
	
	void clearHeight(AVLNode<E> node){
		if(node == null) return;
		node.height = 0;
		//~ System.out.println("tinggi "+node.x+" "+node.height);
		clearHeight(node.left);
		clearHeight(node.right);
		return;
	}
	
	AVLNode<E> singleRotationWithLeftChild(AVLNode<E> node){
		AVLNode<E> k1 = node.left; //10
		node.left = k1.right; //null
		k1.right = node; //100
		clearHeight(k1);
		System.out.println("height "+k1.right.height);
		updateHeight(k1);
		
		System.out.println("height "+k1.height);
		return k1;
	}
	
	AVLNode<E> singleRotationWithRightChild(AVLNode<E> node){
		AVLNode<E> k1 = node.right;
		node.right = k1.left;
		k1.left = node;
		clearHeight(k1);
		updateHeight(k1);
		return k1;
	}
	
	AVLNode<E> doubleRotationWithLeftChild(AVLNode<E> node){
		node.left = singleRotationWithRightChild(node.left);
		return singleRotationWithLeftChild(node);
	}
	
	AVLNode<E> doubleRotationWithRightChild(AVLNode<E> node){
		node.right = singleRotationWithLeftChild(node.right);
		return singleRotationWithRightChild(node);
	}	
}

class Main{
	public static void main(String args[]){
		AVLTree<Integer> avltree = new AVLTree<Integer>(new AVLNode<Integer>(100, null, null));
		
		avltree.root.prettyPrint("","",true);
		avltree.root = avltree.insert(200, avltree.root);
		
		avltree.root.prettyPrint("","",true);
		avltree.root = avltree.insert(150, avltree.root);
		//~ avltree.insert(101, avltree.root);
		
		//~ avltree.root.prettyPrint("","",true);
		//~ avltree.root = avltree.insert(1, avltree.root);
		
		avltree.root.prettyPrint("","",true);
		
	}
}