package project2;

import java.util.Iterator;

public class UWmailDB {
	  //TODO private member variables
	private int size;
	private ListADT<Conversation>inbox,trash;
	
	  public UWmailDB() {
	 inbox = new DoublyLinkedList<Conversation>();
	 trash = new DoublyLinkedList<Conversation>();
	 size = 0;
	  }

	  private Conversation findemail(Email e){
		  Iterator<Conversation> itr= inbox.iterator();
		  Conversation tmp = null;
		  Email firstemail = null;
		  while(itr.hasNext()){
			  tmp = itr.next();
			  firstemail = tmp.get(tmp.size());
			  Iterator <String> itr1 = firstemail.getReferences().iterator();
			  while(itr1.hasNext()){
			  if(itr1.next().equals(e.getMessageID()))
					  return tmp;
				}
		  }
		  
		  return null;
		  
		  
	  }
	  
	  
	  public int size() {
	   return size;
	  }

	  //Pre-condition: e will be added to a conversation for which it is the oldest email.
	  //    In other words, you can simply add it to the beginning of the list, if the list
	  //    is sorted in chronological order.
	  //    Also, the messageID of e is guaranteed to be included in the References field
	  //    of all emails in the conversation that it belongs in.
	  public void addEmail(Email e) {
		  	if(this.findemail(e)!=null)  this.findemail(e).add(e);
		  	else inbox.add(new Conversation(e));
	  }

	  public ListADT<Conversation> getInbox() {
	    return inbox;
	  }

	  public ListADT<Conversation> getTrash() {
	    return trash;
	    }

	  public void deleteConversation(int idx) {
	    if(idx>inbox.size()) throw new IllegalArgumentException();
	   Conversation tmp = inbox.remove(idx);
	   trash.add(tmp);
	  }

	}