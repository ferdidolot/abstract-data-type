

class Stack<E>{
	private int size;
	private E ar[];
	private int pointer;
	public Stack(){}
		
	public Stack(int n){
		pointer = -1;
		ar = (E[]) new Object[n];
		size = n;
	}
	
	private void doubleArray(){
		E[] old = ar;
		size = (size << 1);
		ar = (E[]) new Object[size];
		for(int i = 0 ; i < old.length ; i++){
			ar[i] = old[i];
		}
	}
	
	public E top(){
		return ar[pointer];
	}
	
	public E pop(){
		E x = ar[pointer];
		pointer--;
		return x;
	}
	
	public void push(E x){
		if(pointer == size -1) doubleArray();
		pointer++;
		ar[pointer] = x;
	}
	
	public boolean isEmpty(){
		return pointer == -1;
	}
	
	
}

class Main{
	public static void main(String args[]){
		Stack<Integer> stack = new Stack<Integer>(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.top());
		stack.pop();
		System.out.println(stack.top());
		
		stack.push(6);
		System.out.println(stack.top());
		
		stack.push(7);
		System.out.println(stack.top());
	}
}