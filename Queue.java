import java.util.Arrays;

class Queue<E>{
	 E[] ar;
	private int arraysize;
	int front;
	int back;
	private int currentsize;
	Queue(){}
		
	Queue(int n){
		ar = (E[]) new Object[n];
		arraysize = n;
		front = 0;
		back = -1;
	}
	
	public int size(){
		return currentsize;
	}
	
	public E top(){
		return ar[front];
	}
	
	public E last(){
		return ar[back];
	}
	
	public E pop(){
		E tmp = ar[front];
		ar[front] = null;
		front++;
		currentsize--;
		front %= arraysize;
		return tmp;
	}
	
	public void add(E x){
		back++;
		if(currentsize == arraysize){
			doubleArray();
		}
		back %= arraysize;		
		currentsize++;
		ar[back] = x;
	}
	
	private void doubleArray(){
		E old[] = ar;
		arraysize *= 2;
		ar = (E[]) new Object[arraysize];
		for(int i = 0 ; i <= back ; i++) {
			ar[i] = old[i];
		}
		
		for(int i = arraysize -1,  k = 0 ; k < old.length - front ; i--, k++){
			ar[i] = old[old.length- k - 1];
		}
		front = arraysize - (old.length - front);
	}
}

class Main{
	public static void main(String args[]){
		Queue<Integer> queue = new Queue<Integer>(3);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue.pop());
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		queue.add(4);
		queue.add(5);
		
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		queue.add(6);
		
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		
		System.out.println(queue.pop());
		
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		
		System.out.println(queue.pop());
		
		System.out.println(queue.pop());
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		queue.add(7);
		queue.add(8);
		queue.add(9);
		queue.add(10);
		
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		queue.add(11);
		
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		
		System.out.println(queue.pop());
		System.out.println(queue.front+" "+queue.back+" "+Arrays.toString(queue.ar));
		
	}
}