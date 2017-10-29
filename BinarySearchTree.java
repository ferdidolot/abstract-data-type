class BinaryNode<E>{
	BinaryNode left;
	BinaryNode right;
	E x;
	
	BinaryNode(){}
	
	BinaryNode(E x, BinaryNode left, BinaryNode right){
		this.x = x;
		this.left = left;
		this.right = right;
	}
	
	
}

class BinarySearchTree<E extends Comparable<E>>{
	BinaryNode<E> root;
	static int idx = 0;
	BinarySearchTree(){}
		
	BinarySearchTree(BinaryNode<E> root){
		this.root = root;
	}
	
	BinaryNode<E> add(E x){
		return add(root, x);
	}
	
	BinaryNode<E> add(BinaryNode<E> pos, E x){
		if(pos == null){
			return new BinaryNode<E>(x, null, null);
		}
		else if(x.compareTo(pos.x) < 0){
			pos.left = add(pos.left, x);
		}
		else if(x.compareTo(pos.x) > 0 ){
			pos.right = add(pos.right, x);
		}
		return pos;
	}
	
	
	E findMin(){
		return findMin(root).x;
	}
	
	BinaryNode<E> findMin(BinaryNode<E> node){
		if(node.left == null) return node;
		return findMin(node.left);		
	}
	
	BinaryNode<E> remove(BinaryNode<E> node, E x){
		if(node.x.compareTo(x) < 0){
			node.left = remove(node.left, x);
		}
		else if(node.x.compareTo(x) > 0){
			node.right = remove(node.right, x);
		}
		else if(node.left == null && node.right != null){
			node = node.right;
		}
		else if(node.right == null && node.left != null){
			node = node.left;
		}
		else{
			node.x = findMin(node.right).x;
			node.right = removeMin(node.right);
		}
		return node;
	}
	
	BinaryNode<E> removeMin(BinaryNode<E> node){
		if(node.left != null){
			node.left = removeMin(node.left);
		}
		else{
			node = node.right;
		}
		return node;
	} 
	
	static BinaryNode<Integer> constructPreOrder(int[] ar, int min, int key, int max){
		if(idx >= ar.length) return null;
		//~ System.out.println(idx+" "+ar[idx]);
		BinaryNode<Integer> root = null;
		if(key > min && key < max ){
			root = new BinaryNode<Integer>(key, null, null);
			if(idx + 1 < ar.length){
				idx = idx + 1;
				root.left = constructPreOrder(ar, min, ar[idx], key );
				root.right = constructPreOrder(ar, key, ar[idx], max);
			}
		}
			
		return root;
	}
	
	public void printPreOrder(BinaryNode<Integer> node){
		//~ if(node == null) return;
		if(node != null){
			System.out.println(node.x);
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}
}

class Main{
	public static void main(String args[]){
		//~ BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(new BinaryNode<Integer>(11, null, null));
		//~ System.out.println(bst.root.left);
		//~ System.out.println(bst.root.right);
		//~ System.out.println(bst.root.x);
		//~ System.out.println();
		//~ bst.add(9);
		//~ bst.add(8);
		//~ bst.add(10);
		//~ bst.add(14);
		//~ bst.add(12);
		//~ bst.add(13);
		//~ System.out.println(bst.root.left.x);
		//~ System.out.println(bst.root.left.left.x);
		//~ System.out.println(bst.root.left.right.x);
		//~ System.out.println(bst.root.right.x);
		//~ System.out.println(bst.root.x);
		
		//~ System.out.println(bst.findMin());
		//~ bst.remove(bst.root, 11);
		//~ System.out.println(bst.root.x);
		//~ System.out.println(bst.root.right.left.x);
		//~ System.out.println(bst.root.x);
		//~ System.out.println(bst.root.left.x);
		//~ System.out.println(bst.root.right.x);
		//~ System.out.println(bst.root.right.left.x);
		
		int[] preorder = {4, 3, 2, 1, 5, 6, 7,8};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(new BinarySearchTree<Integer>().constructPreOrder(preorder, -1000, preorder[0], 1000));
		bst.printPreOrder(bst.root);
		//~ bst.construct();
	}
}