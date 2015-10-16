package project2;
//import java.awt.HeadlessException;
//import java.util.Currency;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class DoublyLinkedListIterator<E> implements Iterator<E> {


    // *** fields ***
    private Listnode<E> curr;  // the list we're iterating over
    //private int curPos;            // the position of the next item
     
    // *** constructor ***
    public DoublyLinkedListIterator(Listnode<E> head) {
       // this.list = list;                                                                                                                                                                                                
      //  curPos = 0;
    	curr = head;
    	
    	
    	
    }
 
    // *** methods ***
 
    public boolean hasNext() {
        return curr.getNext()!=null;   
    }
 
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E result = curr.getData();
        //curPos++;
        curr = curr.getNext();
        return result;
    }
 
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
