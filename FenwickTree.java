import java.util.*;

public class FenwickTree{
	ArrayList<Integer> list;
	
	int LSOne(int x){ return (x & (-x));  }
	FenwickTree(){}
	FenwickTree(int n){
		list = new ArrayList<Integer>(n+1);
		for(int i = 0 ; i < n+1 ; i++){ list.add(0); }
	}
	
	int rsq(int b){ //rsq(1 , b)
		int sum = 0;
		for(; b > 0 ; b -= LSOne(b)) sum += list.get(b);
		return sum;
	}
	
	int rsq(int a, int b){ // rsq(a,b)
		return (a == 1) ? rsq(b) : rsq(b) - rsq(a - 1);
	}
	
	void add(int i, int n){ //add ith element by n
		for(; i < list.size(); i += LSOne(i)) list.set(i, list.get(i) + n);
	}
	
	public static void main(String args[]){
		FenwickTree ft = new FenwickTree(8);
		ft.add(2, 1);
		ft.add(4, 1);
		ft.add(5, 2);
		ft.add(6, 3);
		ft.add(7, 2);
		ft.add(8, 1);
		//~ System.out.println(ft.list.size());
		//~ for(int i = 0 ; i < ft.list.size() ; i++){ 
			//~ System.out.print(ft.list.get(i)+ " a"); 
		//~ }
		//~ System.out.println(Collections.toString(ft.list));
		System.out.println(ft.rsq(1,1));
		System.out.println(ft.rsq(1,2));
		System.out.println(ft.rsq(2));
		System.out.println(ft.rsq(1,6));
		System.out.println(ft.rsq(1,8));
		System.out.println(ft.rsq(8));
		
		System.out.println(ft.rsq(3,6));
		System.out.println(ft.rsq(6,6));
	}
}