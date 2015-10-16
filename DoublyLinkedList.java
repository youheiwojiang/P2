package project2;

import java.util.Iterator;
//assume the pos are from 1 to numItems;
public class DoublyLinkedList<E> implements ListADT<E> {
		 
		  // *** fields ***
		  //  private E items; // the items in the List
		    private int numItems;   // the number of items in the List
		    private Listnode<E>head ;
		    private Listnode<E> getNode(int pos){
		    	Listnode<E> curr = head;
		    	for(int i=0;i<pos;i++)
		    		curr = curr.getNext();
		    		
		    	return curr;
		    	
		    	
		    }
		    
		  // *** constructor ***
		    public DoublyLinkedList() {
		    	head = new Listnode<E>( null,null,null);
		    	numItems = 0;
		    	head.setNext(head);
		    	head.setPrev(head);
		    	
		    }      
		    
		  //*** required ListADT methods ***
		    //iterator
		    public Iterator<E> iterator(){ return new DoublyLinkedListIterator<E>(this);}
		    // add items
		    public void add(E item) { 
		    	if(item == null) throw new IllegalArgumentException();
		    	
		    	Listnode<E>curr = this.getNode(numItems);
		    	curr.setNext(new Listnode<E>(item));
		    	numItems++;
		    	/*Iterator<E>itr = this.iterator();
		    	while(itr.hasNext()){
		    	}*/

		    	}
		    	
		    public void add(int pos, E item) {	    	
		    	if(item == null) throw new IllegalArgumentException();
		    	if(pos>numItems||pos<1) throw new IndexOutOfBoundsException();
		    	Listnode<E>curr = this.getNode(pos-1);
		    	Listnode<E>newnode = new Listnode<E>(item);
		    	newnode.setNext(curr.getNext());
		    	curr.setNext(newnode);
		    	numItems++;
		    }   
		    
		    // remove items
		    public E remove(int pos) {  
		    	if(pos>numItems||pos<1) throw new IndexOutOfBoundsException();
		    	Listnode<E>prev = this.getNode(pos-1);
		    	Listnode<E>curr = prev.getNext();
		    	prev.setNext(curr.getNext());
		    	numItems--;
		    	return  curr.getData();
		    	
		    	
		    }  
		 
		    // get items
		    public E get (int pos) { 
		    	
		    	if(pos>numItems||pos<1) throw new IndexOutOfBoundsException();
		    	Listnode<E>curr = this.getNode(pos);
		    	return curr.getData();
		    	
		    	
		    }  
		 
		    // other methods
		    public boolean contains (E item) { return true; }
		    public int size() { return numItems; }      
		    public boolean isEmpty() { return true; }  
		}  
