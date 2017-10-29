import java.util.Arrays;
class Sort{
	int[] ar;
	Sort(){}
	Sort(int ar[]){
		this.ar = ar;
	}
	
	public int[] sort(int ar[]){
		if(ar.length == 1) return ar;
		int k = 0;
		int a[] = new int[ar.length/2];
		int b[] = new int[ar.length - ar.length/2];
		for(int i = 0 ; i < a.length ; k++, i++) a[i] = ar[k];
		for(int i = 0 ; i < b.length ; k++, i++) b[i] = ar[k];
		//~ System.out.println(Arrays.toString(b));
		a = sort(a);
		b = sort(b);
		return merge(a,b);
	}
	
	public int[] merge(int a[], int b[]){
		int c[] = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		
		for(k = 0 ; k < c.length ; k++){
			if( i == a.length ) {
				c[k] = b[j];
				j++;
				continue;
			}
			if( j == b.length ){
				c[k] = a[i];
				i++;
				continue;
			}
			if(a[i] < b[j]) {
				c[k] = a[i];
				i++;
			}
			else {
				c[k] = b[j];
				j++;
			}
		}
		
		return c;
	}
	
	public void quickSort(int ar[], int p, int r){
		if(p < r){
			int m = partition(ar, p, r);
			quickSort(ar, p, m-1);
			quickSort(ar, m+1, r);
		}
	}
	
	private int partition(int ar[], int p, int r){
		int x = ar[r];
		int i = p -1;
		for(int j = p ; j < r ; j++){
			if(ar[j] < x){
				i++;
				int tmp = ar[i];
				ar[i] = ar[j];
				ar[j] = tmp;
			}
		}
		int tmp = ar[i+1];
		ar[i+1] = ar[r];
		ar[r] = tmp;
		return i+1;
	}
}

class Main{
	public static void main(String args[]){
		Sort merge = new Sort();
		int ar[] = {2 , 4 , 1, -100, 29, 33, 16, 13, 12, 90, -200, 19, 10, 1} ;
		
		merge.quickSort(ar, 0, ar.length-1);
		System.out.println(Arrays.toString(ar));
		
	}
}