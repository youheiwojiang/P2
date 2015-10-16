package project2;

import java.util.Iterator;

public class Conversation implements Iterable<Email> {
 private ListADT<Email> conversation;
 private Listnode<Email> curr;
 private int currpos;
 
  public Conversation(Email e) {
	 Listnode<Email> newnode = new Listnode<Email>(e);
    conversation = new DoublyLinkedList<Email>();
    conversation.add(e);
    curr = newnode;
    currpos = conversation.size()-1;
    
  }

  public int getCurrent() {
    return currpos;
  }

  public void moveCurrentBack() {
//	  if(curr.next==curr) 
	  curr = curr.getPrev();
	  currpos--;
  }

  public void moveCurrentForward() {
//	  if(curr.next==curr) 
	  curr = curr.getNext();
	  currpos++;
  }

  public int size() {
	   return conversation.size();
  }

  public Email get(int n) {
	  
	  return conversation.get(n);
  }

  public void add(Email e) {
   conversation.add(1,e);
  }

  public Iterator<Email> iterator() {
   return new DoublyLinkedListIterator<Email>((DoublyLinkedList<Email>)conversation);
  }

}