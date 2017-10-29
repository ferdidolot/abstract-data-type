import java.util.Arrays;
import java.util.Comparator;
class ArrayList<E> {
    private int size;
    private E ar[];
    private int last;
    public ArrayList(){
        size = 10;
        ar = (E[]) new Object[size];
    }
    
    public E[] getAr(){return ar;}
    
    public ArrayList(int n){
        //~ @SuppressWarnings("unchecked")
        last = 0;
        ar = (E[]) new Object[n];
        size = n;
    }
    
    public ArrayList(E[] ar){
        this.ar = (E[]) new Object[ar.length*2+1];
        size = ar.length*2+1;
        last = ar.length;
        for(int i = 0 ; i < size ; i++){
            this.ar[i] = ar[i];
        }
    }
    
    private void doubleArray(){
        E[] old = this.ar;
        last = this.ar.length;
        ar = (E[]) new Object[size*2+1];
        size = size*2+1;
        for(int i = 0 ; i < old.length ; i++){
            ar[i] = old[i];
        }        
    }
    
    public void add(E x){
        if(last == size-1){
            doubleArray();
        }
        ar[last] = x;
        System.out.println(x+" "+last+" "+ar[last]);
      
        last++;
    }
    
    public void print(){
        for(int i = 0 ;i < last ; i++){
            System.out.print(ar[i]+" ");    
        }
        System.out.println();
    }
    
    public int size(){
        return last;
    }
    
    public void sort(){
        //~ System.out.println(ar[0].compareTo(ar[1]));
        Arrays.sort(this.ar, 0, last);    
    }
    
}

class Main{
    public static void main(String args[]){
        ArrayList<Integer> list = new ArrayList<Integer>();    
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(3);
        
        list.print();
        list.sort();
        list.print();
    }    
}