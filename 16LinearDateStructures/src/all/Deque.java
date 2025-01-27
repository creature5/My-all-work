package all;

import java.util.ArrayList;
import java.util.List;


public class Deque<T> {
	
	private List<T> deque = new ArrayList<T>();
    
    public void insertFront(T item){
        //add element at the beginning of the queue
        System.out.println("adding at front: "+item);
        deque.add(0,item);
        System.out.println(deque);
    }
     
    public void insertRear(T item){
        //add element at the end of the queue
        System.out.println("adding at rear: "+item);
        deque.add(item);
        System.out.println(deque);
    }
     
    public void removeFront(){
        if(deque.isEmpty()){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        }
        //remove an item from the beginning of the queue
        T rem = deque.remove(0);
        System.out.println("removed from front: "+rem);
        System.out.println(deque);
    }
     
    public void removeRear(){
        if(deque.isEmpty()){
            System.out.println("Deque underflow!! unable to remove.");
            return;
        }
        //remove an item from the beginning of the queue
        T rem = deque.remove(deque.size()-1);
        System.out.println("removed from rear: "+rem);
        System.out.println(deque);
    }
     
    public T peakFront(){
        //gets the element from the front without removing it
        T item = deque.get(0);
        System.out.println("Element at first: "+item);
        return item;
    }
     
    public T peakRear(){
        //gets the element from the rear without removing it
        T item = deque.get(deque.size()-1);
        System.out.println("Element at rear: "+item);
        return item;
    }
     
    public static void main(String a[]){
         
        Deque<Integer> deq = new Deque<Integer>();
        deq.insertFront(34);
        deq.insertRear(45);
        deq.removeFront();
        deq.removeFront();
        deq.removeFront();
        deq.insertFront(21);
        deq.insertFront(98);
        deq.insertRear(5);
        deq.insertFront(43);
        deq.removeRear();
    }
}
