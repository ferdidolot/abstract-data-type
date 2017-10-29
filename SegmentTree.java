import java.util.*;
import java.io.*;

public class SegmentTree{
	int[] ar;
	int[] st;
	int n;
	int stlen;
	SegmentTree(int[] ar){
		this.ar = ar;
		this.n = ar.length;
		stlen =  2 * (int)Math.pow(2, Math.floor(Math.log(n) / Math.log(2)) + 1);
		//~ System.out.println(stlen);
		st = new int[stlen];
		Arrays.fill(st, 0);
		build(1, 0, n-1);
	}
	
	int left(int idx) { return ( idx << 1 ); }
	int right(int idx){ return ( idx << 1 ) + 1; }
	
	void build(int node, int left, int right){
		//~ System.out.println(node+" "+left+" "+right+" "+n);
		if(left == right) { st[node] = left; }
		else{
			build(left(node), left, (left+right)/2); //build left
			build(right(node), (left+right)/2 + 1, right); //build right
			int node1 = st[left(node)]; 
			int node2 = st[right(node)];
			st[node] = (ar[node1] <= ar[node2]) ? node1 : node2; //set min as parent
		}
	}
	
	private int rmq(int node, int left, int right, int i, int j){
		if(i > right || j < left) { return -1; }// out of range
		if( left >= i && right <=j ) { return st[node]; }//in range
		
		int node1 = rmq(left(node), left, (left+right)/2, i, j); //search in left
		int node2 = rmq(right(node), (left+right)/2 + 1, right, i, j); //search in right
		
		if(node1 == -1) return node2; //not found in left, return right
		if(node2 == -1) return node1;//not found in right, return left
		
		return ( ar[node1] <= ar[node2] ) ? node1 : node2; //return min between left and right
	}
	
	private int update(int node, int left, int right, int idx, int value){
		int i = idx;
		int j = idx;
		
		//current value does not intersect the update interval
		if( i > right || j < left ) return st[node];
		
		//current interval is in update range
		if( i == left && j == right ){
			ar[i] = value;
			return st[node] = left;
		}
		
		int node1 = update(left(node), left, (left+right)/2, idx, value); //rec left
		int node2 = update(right(node), (left+right)/2+1, right, idx, value); //rec right
		
		//set min as parent
		return st[node] = (ar[node1] <= ar[node2]) ? node1 : node2; //set node to min between left and right
	}
	
	public void update(int idx, int value){
		update(1, 0, n-1, idx, value);
		//~ ar[idx] = value;
		//~ build(1, 0, n-1);
	}
	
	public int rmq(int i, int j){
		return rmq(1, 0, n-1, i, j);
	}
	
	public static void main(String args[]){
					//idx0   1    2  3   4   5   6
		int[] A = new int[] { 8, 7, 3, 9, 10, 10, 10 }; // the original array
		SegmentTree st = new SegmentTree(A);
		System.out.println(Arrays.toString(st.ar));
		System.out.println("RMQ(0,6) = " + st.rmq(0,6));
		st.update(5, 100);
		System.out.println(Arrays.toString(st.ar));
		System.out.println("RMQ(0,6) = " + st.rmq(0,6));
	}
}